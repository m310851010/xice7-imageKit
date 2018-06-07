package com.xice7.image.filter;

import java.awt.Color;

/**
 * 此滤镜可从图像的色调，饱和度和亮度通道的每一个中增加或减少给定量。例如，您可以使用它来着色图像或使颜色更饱和。
 * @author mdc
 * @date 2018年6月7日
 */
public class HSBAdjustFilter extends PointFilter {
	
	/**
	 * 色相值, 最大为1
	 */
	private double hFactor;
	/**
	 * 饱和度值, 最大为1
	 */
	private double sFactor;
	/**
	 * 亮度值, 最大为1
	 */
	private double bFactor;
	
	private float[] hsb = new float[3];
	
	public HSBAdjustFilter() {
		this(0, 0, 0);
	}

	/**
	 * 构造器
	 * @param r 色相值, 最大为1
	 * @param g 饱和度值, 最大为1
	 * @param b 亮度值, 最大为1
	 */
	public HSBAdjustFilter(double r, double g, double b) {
		hFactor = r;
		sFactor = g;
		bFactor = b;
		canFilterIndexColorModel = true;
	}

	public void setHFactor( double hFactor ) {
		this.hFactor = hFactor;
	}
	
	public double getHFactor() {
		return hFactor;
	}
	
	public void setSFactor( double sFactor ) {
		this.sFactor = sFactor;
	}
	
	public double getSFactor() {
		return sFactor;
	}
	
	public void setBFactor( double bFactor ) {
		this.bFactor = bFactor;
	}
	
	public double getBFactor() {
		return bFactor;
	}
	
	@Override
	public int filterRGB(int x, int y, int rgb) {
		int a = rgb & 0xff000000;
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		Color.RGBtoHSB(r, g, b, hsb);
		hsb[0] += hFactor;
		while (hsb[0] < 0)
			hsb[0] += Math.PI * 2;
		hsb[1] += sFactor;
		if (hsb[1] < 0)
			hsb[1] = 0;
		else if (hsb[1] > 1.0)
			hsb[1] = 1.0f;
		hsb[2] += bFactor;
		if (hsb[2] < 0)
			hsb[2] = 0;
		else if (hsb[2] > 1.0)
			hsb[2] = 1.0f;
		rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		return a | (rgb & 0xffffff);
	}

	public String toString() {
		return "Colors/Adjust HSB...";
	}
}

