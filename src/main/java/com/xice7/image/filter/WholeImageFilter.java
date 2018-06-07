package com.xice7.image.filter;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 * A filter which acts as a superclass for filters which need to have the whole image in memory
 * to do their stuff.
 */
public abstract class WholeImageFilter extends AbstractBufferedImageOp {

	/**
     * The output image bounds.
     */
    protected Rectangle transformedSpace;

	/**
     * The input image bounds.
     */
	protected Rectangle originalSpace;
	
	/**
	 * Construct a WholeImageFilter.
	 */
	public WholeImageFilter() {
	}

    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        int width = src.getWidth();
        int height = src.getHeight();

		originalSpace = new Rectangle(0, 0, width, height);
		transformedSpace = new Rectangle(0, 0, width, height);
		transformSpace(transformedSpace);

        if ( dst == null ) {
            ColorModel dstCM = src.getColorModel();
			dst = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(transformedSpace.width, transformedSpace.height), dstCM.isAlphaPremultiplied(), null);
		}

		int[] inPixels = getRGB( src, 0, 0, width, height, null );
		inPixels = filterPixels( width, height, inPixels, transformedSpace );
		setRGB( dst, 0, 0, transformedSpace.width, transformedSpace.height, inPixels );

        return dst;
    }

	/**
     * Calculate output bounds for given input bounds.
     * @param rect input and output rectangle
     */
	protected void transformSpace(Rectangle rect) {
	}
	
	/**
     * Actually filter the pixels.
     * @param width the image width
     * @param height the image height
     * @param inPixels the image pixels
     * @param transformedSpace the output bounds
     * @return the output pixels
     */
	protected abstract int[] filterPixels( int width, int height, int[] inPixels, Rectangle transformedSpace );
}

