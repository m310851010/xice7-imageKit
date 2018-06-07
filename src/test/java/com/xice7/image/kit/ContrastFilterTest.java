package com.xice7.image.kit;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.xice7.image.filter.ContrastFilter;

/**
 * @author mdc
 * @date 2018年6月7日
 */
public class ContrastFilterTest {
	File file;
	
	@Before
	public void readImage() throws URISyntaxException {
		file = new File(HSBAdjustFilterTest.class.getResource("/1.jpg").toURI());
	}
	
	@Test
	public  void filterTest() {
		//亮度增强
		ContrastFilter filter2 = new ContrastFilter(1.2);
		ImageKit.read(file).doFilter(filter2).transferTo("d:/test2.jpg");
	}
}
