package com.xice7.image.kit;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.xice7.image.filter.HSBAdjustFilter;

/**
 * @author mdc
 * @date 2018年6月7日
 */
public class HSBAdjustFilterTest {

	File file;
	
	@Before
	public void readImage() throws URISyntaxException {
		file = new File(HSBAdjustFilterTest.class.getResource("/1.jpg").toURI());
	}
	
	@Test
	public  void filterTest() {
		//增强亮度
		HSBAdjustFilter filter = new HSBAdjustFilter(0, 0, 0.2);
		ImageKit.read(file).doFilter(filter).transferTo("d:/test.jpg");
	}
}
