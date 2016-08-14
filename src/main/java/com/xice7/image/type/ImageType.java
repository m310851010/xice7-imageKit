package com.xice7.image.type;

/**
 * 图片类型
 * @author mdc
 * @date 2015年2月27日
 */
public enum ImageType {

	/**
	 * png图片
	 */
	PNG("image/png", "png", "data:image/png;base64,"),
	/**
	 * jpg图片
	 */
	JPG("image/jpg", "jpg", "data:image/jpeg;base64,"),
	/**
	 * jpg图片
	 */
	JPEG("image/jpeg", "jpeg", "data:image/jpeg;base64,"),
	/**
	 * bmp图片
	 */
	BMP("image/bmp", "bmp", "data:image/bmp;base64,"),
	/**
	 * wbmp图片
	 */
	WBMP("image/vnd.wap.wbmp", "wbmp", "data:image/wbmp;base64,"),
	/**
	 * gif图片
	 */
	GIF("image/gif", "gif", "data:image/gif;base64,");
	
	private String type;
	private String media;
	private String b64Prefix; //base64编码前缀
	
	ImageType(String media, String type, String b64Prefix){
		this.media = media;
		this.type = type;
		this.b64Prefix = b64Prefix;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
	/**
	 * 获取图片类型
	 * @author mdc
	 * @date 2015年2月27日
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * 获取图片的Media
	 * @author mdc
	 * @date 2015年6月10日
	 * @return
	 */
	public String getMedia(){
		return this.media;
	}

	/**
	 * @return 获取{@link #b64Prefix}
	 */
	public String getB64Prefix() {
		return b64Prefix;
	}
}
