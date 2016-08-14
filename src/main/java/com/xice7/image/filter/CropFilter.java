package com.xice7.image.filter;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 * A filter which crops an image to a given rectangle.
 */
public class CropFilter extends AbstractBufferedImageOp {

	private int x;
	private int y;
	private int width;
	private int height;

    /**
     * Construct a CropFilter.
     */
	public CropFilter() {
		this(0, 0, 32, 32);
	}

    /**
     * Construct a CropFilter.
     * @param x the left edge of the crop rectangle
     * @param y the top edge of the crop rectangle
     * @param width the width of the crop rectangle
     * @param height the height of the crop rectangle
     */
	public CropFilter(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	@Override
    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        if ( dst == null ) {
            ColorModel dstCM = src.getColorModel();
			dst = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(width, height), dstCM.isAlphaPremultiplied(), null);
		}

		Graphics2D g = dst.createGraphics();
		g.drawRenderedImage( src, AffineTransform.getTranslateInstance(-x, -y) );
		g.dispose();

        return dst;
    }

	public String toString() {
		return "mdc/Crop";
	}
}
