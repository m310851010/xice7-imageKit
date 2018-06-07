package com.xice7.image.filter;

/**
 * 此滤镜可更改图像的亮度和对比度。
 * 值为1表示图片初始值, 最小值为0
 */
public class ContrastFilter extends TransferFilter {

	/**
	 * 亮度,初始为1
	 */
	private double brightness = 1.0f;
	/**
	 * 对比度,初始为1
	 */
	private double contrast = 1.0f;
	
	public ContrastFilter() {
	}

	/**
	 * @param brightness 亮度,初始为1
	 */
	public ContrastFilter(double brightness) {
		this.brightness = brightness;
	}

	/**
	 * @param brightness 亮度,初始为1
	 * @param contrast 对比度,初始为1
	 */
	public ContrastFilter(double brightness, double contrast) {
		this.brightness = brightness;
		this.contrast = contrast;
	}

	@Override
	protected float transferFunction(float f) {
		f = (float)(f * brightness);
		f = (float)((f - 0.5f) * contrast + 0.5f);
		return f;
	}

	/**
	 * 设置滤镜亮度
	 * 
	 * @param brightness 亮度值,1为当前图片亮度
	 * @min-value 0
	 * @see #getBrightness
	 */
	public void setBrightness(double brightness) {
		this.brightness = brightness;
		initialized = false;
	}

	/**
	 * 获取滤镜亮度
	 * 
	 * @return 亮度值
	 * @see #setBrightness
	 */
	public double getBrightness() {
		return brightness;
	}

	/**
	 * 设置滤镜对比度
	 * @param contrast 范围在0到1之间
	 * @min-value 0
	 * @see #getContrast
	 */
	public void setContrast(double contrast) {
		this.contrast = contrast;
		initialized = false;
	}

	/**
	 * 获取滤镜对比度
	 * @return 对比度值范围在0到1之间
	 * @see #setContrast
	 */
	public double getContrast() {
		return contrast;
	}

	public String toString() {
		return "Colors/Contrast...";
	}

}
