package com.xice7.image.ex;

/**
 * @author mdc
 * @date 2016年4月2日
 */
public class ImageParseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ImageParseException(String message) {
		super(message);
	}
	
	public ImageParseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ImageParseException(Throwable cause) {
		super(cause);
	}
}
