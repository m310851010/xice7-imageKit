package com.xice7.image.kit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mdc
 * @date 2016年8月14日
 */
public class ImageKitOpTest {
	static String jpgPath = "/1.jpg";
	static String pngPath = "/1.png";

	ImageKit kit = null;

	@Before
	public void readImage() throws URISyntaxException {
		File file;
			file = new File(ImageKitOpTest.class.getResource(jpgPath).toURI());
			kit = ImageKit.read(file);
	}

	// 黑白
	@Test
	public void gray() {
		kit.gray().transferTo("d:/gray." + kit.getFormat());
	}

	// 图片剪切
	@Test
	public void crop() {
		kit.crop(0, 60, kit.getWidth() / 2, kit.getHeight() / 2).transferTo("d:/crop." + kit.getFormat());
	}

	// 图片剪切-圆形
	@Test
	public void cropCircle() {
		kit.cropCircle().transferTo("d:/cropCircle." + kit.getFormat());
	}

	// 图片剪切-圆形
	@Test
	public void cropCircle2() {
		kit.cropCircle(10, 50).transferTo("d:/cropCircle2." + kit.getFormat());
	}

	// 图片剪切-圆形
	@Test
	public void cropCircle3() {
		kit.cropCircle(20, 50, 50).transferTo("d:/cropCircle3." + kit.getFormat());
	}

	// 灰度处理
	@Test
	public void grayscale() {
		kit.grayscale().transferTo("d:/grayscale." + kit.getFormat());
	}

	// 像素
	@Test
	public void pixellate() {
		kit.pixellate().transferTo("d:/pixellate." + kit.getFormat());
	}
	
	// 旋转
	@Test
	public void rotate() {
		kit.rotate().transferTo("d:/rotate." + kit.getFormat());
	}
	
	// 旋转
	@Test
	public void rotate2() {
		kit.rotate(90).transferTo("d:/rotate90." + kit.getFormat());
	}
	
	// 老照片
	@Test
	public void sepiaTone() {
		kit.sepiaTone().transferTo("d:/sepiaTone." + kit.getFormat());
	}
	
	//  图片等比缩放
	@Test
	public void scale() {
		kit.scale(100, 100).transferTo("d:/scale." + kit.getFormat());
	}
	
	//  图片不等比缩放
	@Test
	public void scale2() {
		kit.scale(100, 150, false).transferTo("d:/scale2." + kit.getFormat());
	}
	
	//  压缩图片,使用0.5压缩比率
	@Test
	public void zip() {
		System.out.println("原图大小:" + kit.getSize()/1000 + "kb");
		kit.zip().transferTo("d:/zip." + kit.getFormat());
		System.out.println("压缩后大小:" + kit.getSize()/1000 + "kb");
	}
	
	//  Base64
	@Test
	public void toBase64() {
		System.out.println("转换为base64:" + kit.toBase64());
	}
	

	//  web Base64, 可直接作<img src="base64"> 或background:url(base64)
	@Test
	public void webBase64() {
		System.out.println("转换为web base64:" + kit.getFormat().getB64Prefix() +  kit.toBase64());
	}
	
	//  写入,文件,流
	@Test
	public void transferTo() {
		try {
			kit.transferTo(new FileOutputStream("d:/transferToOutputStream." + kit.getFormat()));
			kit.transferTo(new File("d:/transferToFile." + kit.getFormat()));
		} catch (FileNotFoundException e) {
			Assert.fail("写入文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		}
	}
}
