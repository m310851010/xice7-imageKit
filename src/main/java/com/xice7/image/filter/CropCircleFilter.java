package com.xice7.image.filter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * 剪切为圆形的滤镜
 */
public class CropCircleFilter extends AbstractBufferedImageOp {

	// x 坐标
	private int x;
	// y坐标
	private int y;
	private int width;//选区的宽度
	private int height;//选区高度
	// 弧度半径
	private int radius;

	/**
	 * Construct a CropFilter.
	 */
	public CropCircleFilter() {
	}

	/**
	 * Construct a CropCircleFilter.
	 * @param x the left edge of the crop rectangle
	 * @param y the top edge of the crop rectangle
	 * @param radius 半径
	 */
	public CropCircleFilter(int x, int y, int width, int height, int radius) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.radius = radius;
	}

	/**
	 * Set the left edge of the crop rectangle.
	 * @param x the left edge of the crop rectangle
	 * @see #getX
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the left edge of the crop rectangle.
	 * @return the left edge of the crop rectangle
	 * @see #setX
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the top edge of the crop rectangle.
	 * @param y the top edge of the crop rectangle
	 * @see #getY
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the top edge of the crop rectangle.
	 * @return the top edge of the crop rectangle
	 * @see #setY
	 */
	public int getY() {
		return y;
	}

	
	/**
	 * @return 返回{@linkplain #width}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param 设定{@linkplain #width}
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return 返回{@linkplain #height}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param 设定{@linkplain #height}
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return 获取{@link #radius}
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius 设置radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dst) {
		if (dst == null) {
			dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}

		Graphics2D g2 = dst.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Double(0, 0, width, height, radius*2, radius*2));
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawRenderedImage(src, AffineTransform.getTranslateInstance(-x, -y));
		g2.dispose();
		return dst;
	}

	public String toString() {
		return "mdc/CropCircle";
	}
}
