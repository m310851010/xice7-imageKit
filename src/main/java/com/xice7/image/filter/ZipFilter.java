package com.xice7.image.filter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.xice7.image.ex.ImageParseException;
import com.xice7.image.kit.IOKit;
import com.xice7.image.type.ImageType;



/**
 * 压缩图片滤镜(gif图片会自动使jpeg方式压缩)
 */
public class ZipFilter extends AbstractBufferedImageOp {

	private int width;
	private int height;
	private double quality;
	private ImageType format;

	/**
	 * Construct a CropFilter.
	 * @param width the width of the crop rectangle
	 * @param height the height of the crop rectangle
	 * @param quality 压缩质量
	 */
	public ZipFilter(int width, int height, double quality, ImageType format) {
		this.width = width;
		this.height = height;
		this.quality = quality;
		this.format = format;
	}

	/**
	 * Set the width of the crop rectangle.
	 * @param width the width of the crop rectangle
	 * @see #getWidth
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get the width of the crop rectangle.
	 * @return the width of the crop rectangle
	 * @see #setWidth
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the height of the crop rectangle.
	 * @param height the height of the crop rectangle
	 * @see #getHeight
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the height of the crop rectangle.
	 * @return the height of the crop rectangle
	 * @see #setHeight
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return 获取{@link #quality}
	 */
	public double getQuality() {
		return quality;
	}

	/**
	 * @param quality 设置quality
	 */
	public void setQuality(double quality) {
		this.quality = quality;
	}

	/**
	 * @return 获取{@link #format}
	 */
	public ImageType getFormat() {
		return format;
	}

	/**
	 * @param format 设置format
	 */
	public void setFormat(ImageType format) {
		this.format = format;
	}

	@Override
	public BufferedImage filter(BufferedImage image, BufferedImage dst) {
		if(width != image.getWidth() || height != image.getHeight()) {
			image = new ScaleFilter(width, height).filter(image, dst);
		}

		InputStream stream = null;
		ByteArrayOutputStream bout = null;
		ImageOutputStream out = null;
		
		try {
			
			bout = new ByteArrayOutputStream(); 
			out = ImageIO.createImageOutputStream(bout);
			
			if (format == ImageType.GIF) { // gif图片处理
				format = ImageType.JPEG;
			}

			// 取得合适的 ImageWriter。
			Iterator<?> writers = ImageIO.getImageWritersByFormatName(format.getType());
			if (writers == null || !writers.hasNext()) {
				throw new IllegalStateException("No " + format.getType() + " writers!");
			}

			ImageWriter writer = (ImageWriter) writers.next();
			ImageWriteParam param = writer.getDefaultWriteParam();

			if (param.canWriteCompressed()) { // 是否支持压缩
				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				param.setCompressionType(getCompressionType(format));
				param.setCompressionQuality((float) quality);
			}

			if (param.canWriteProgressive()) {
				param.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
			}

			param.setDestinationType(new ImageTypeSpecifier(image.getColorModel(), image.getSampleModel()));

			writer.reset();
			writer.setOutput(out);
			writer.write(null, new IIOImage(image, null, null), param);
			writer.dispose();

			stream = new ByteArrayInputStream(bout.toByteArray());
			return ImageIO.read(stream);

		} catch (IOException e) {
			throw new ImageParseException(e);
		} finally {
			IOKit.closeQuietly(stream);
			IOKit.closeQuietly(bout);
			IOKit.closeQuietly(out);
		}
	}

	private static final String[] compressionTypeNames = { "BI_RGB", "BI_RLE8", "BI_RLE4", "BI_BITFIELDS", "BI_JPEG", "BI_PNG" };

	/**
	 * 获取压缩类型
	 * @author mdc
	 * @date 2016年5月15日
	 * @param format 图片格式
	 * @return
	 */
	private String getCompressionType(ImageType format) {
		switch (format) {
		case BMP:
			return compressionTypeNames[5];
		case JPEG:
			return "JPEG";
		case GIF:
			return "LZW";
		default:
			return "JPEG";
		}
	}

	public String toString() {
		return "mdc/Zip";
	}
}
