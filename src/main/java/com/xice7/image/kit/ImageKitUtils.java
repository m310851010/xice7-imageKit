package com.xice7.image.kit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.xice7.image.ex.ImageParseException;
import com.xice7.image.type.ImageType;

/**
 * Image处理的辅助工具
 * @author mdc
 * @date 2018年6月7日
 */
public class ImageKitUtils {

	/**
	 * 获取图片的类型。如果是 gif、jpg、png、bmp
	 * 
	 * @param input an Object to be used as an input source, such as a File, readable RandomAccessFile, or InputStream.
	 * @return 图片类型
	 */
	public static ImageAndFormat getImageAndFormat(Object input) throws ImageParseException {
		ImageInputStream imageInput = null;
		
		try {
			imageInput = ImageIO.createImageInputStream(input);
			ImageAndFormat andImage = new ImageAndFormat();
			andImage.format = getFormat((ImageInputStream)imageInput);
			andImage.image = ImageIO.read(imageInput);

			return andImage;
		} catch (IOException e) {
			IOKit.closeQuietly(imageInput);
			throw new ImageParseException(e.getMessage(), e);
		}
	}

	/**
	 * 获取图片的格式
	 * @author mdc
	 * @date 2016年8月14日
	 * @param input an Object to be used as an input source, such as a File, readable RandomAccessFile, or InputStream.
	 * @return
	 * @throws IOException
	 */
	public static ImageType getFormat(Object input) throws IOException {
		ImageInputStream imageInput = ImageIO.createImageInputStream(input);
		return getFormat((ImageInputStream)imageInput);
	}
	
	/**
	 * 获取图片的格式
	 * @author mdc
	 * @date 2016年8月14日
	 * @param imageInput
	 * @return
	 * @throws IOException
	 */
	public static ImageType getFormat(ImageInputStream imageInput) throws IOException {
		Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInput);
		String type = null;

		for (; iterator.hasNext();) {
			ImageReader reader = iterator.next();
			String format = reader.getFormatName();

			if (format != null && format.trim().length() > 0) {
				type = format.toLowerCase();
				break;
			}
		}
		
		if (type == null) {
			throw new ImageParseException("不支持的图片格式");
		}
		
		if("jpg".equals(type)) return ImageType.JPG;

		if("jpeg".equals(type)) return ImageType.JPEG;

		if("bmp".equals(type)) return ImageType.BMP;

		if("wbmp".equals(type)) return ImageType.WBMP;

		if("gif".equals(type)) return ImageType.GIF;

		if("png".equals(type)) return ImageType.PNG;

		return ImageType.JPEG;
	}

	/**
	 * 图片和格式
	 * @author mdc
	 * @date 2016年8月14日
	 */
	public static class ImageAndFormat {
		public BufferedImage image;
		public ImageType format;
	}
	
	/**
	 * 获取等比缩放后的宽度和高度
	 * @author mdc
	 * @date 2016年5月14日
	 * @param width 缩放的宽度
	 * @param height 缩放的高度
	 * @param proportion 是否等比缩放
	 * @return
	 */
	public static int[] getProportion(BufferedImage image, int width, int height, boolean proportion) {
		int newW;
		int newH;

		if (proportion) { // 判断是否是等比缩放
			newW = image.getWidth();
			newH = image.getHeight();

			// 为等比缩放计算输出的图片宽度及高度
			double rate1 = ((double) newW) / (double) width;
			double rate2 = ((double) newH) / (double) height;
			double rate = rate1 > rate2 ? rate1 : rate2;

			newW = (int) (((double) newW) / rate);
			newH = (int) (((double) newH) / rate);
		} else {
			newW = width;
			newH = height;
		}

		return new int[]{newW, newH};
	}

	/**
	 * 把图片转换为png类型
	 * @author mdc
	 * @date 2016年5月14日
	 * @param src 源图片
	 * @return
	 */
	public static BufferedImage convertPng(BufferedImage src) {
		if (src.getTransparency() == Transparency.TRANSLUCENT) {
			return src;
		}

		Image image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		InputStream input = null;
		ByteArrayOutputStream out = null;

		try {
			out = new ByteArrayOutputStream();
			ImageIO.write(tag, ImageType.PNG.getType(), out);

			input = new ByteArrayInputStream(out.toByteArray());
			return ImageIO.read(input);
			
		} catch (IOException e) {
			IOKit.closeQuietly(input);
			throw new ImageParseException(e);
			
		} finally {
			IOKit.closeQuietly(out);
		}
	}
}
