package com.xice7.image.kit;

import java.io.File;

/**
 * @author mdc
 * @date 2016年8月16日
 */
public class MyHeader {
	public static void main(String[] args) {
		ImageKit.read(new File("c:/avatar.png")).scale(120, 120).cropCircle().transferTo("c:/avatar2.png");
	}
}
