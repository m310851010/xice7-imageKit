package com.xice7.image.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import com.xice7.image.type.ImageType;

/**
 * @author mdc
 * @date 2016年8月14日
 */
public class ImageKitReadTest {
	static String jpgPath = "/1.jpg";
	static String pngPath = "/1.png";

	private void print(ImageKit kit){
		System.out.println("info: width=" + kit.getWidth() + ", height="+ kit.getHeight() + ", size=" + kit.getSize() + ", Format=" + kit.getFormat());
	}
	
	@Test
	public void read_BufferedImage(){
		try {
			File file = new File(ImageKitReadTest.class.getResource(jpgPath).toURI());
			ImageKit kit = ImageKit.read(ImageIO.read(file), ImageType.JPG);
			print(kit);
			
			
			file = new File(ImageKitReadTest.class.getResource(pngPath).toURI());
			kit = ImageKit.read(ImageIO.read(file), ImageType.PNG);
			print(kit);
		}  catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		}
	}
	
	@Test
	public void read_File(){
		try {
			File file = new File(ImageKitReadTest.class.getResource(jpgPath).toURI());
			ImageKit kit = ImageKit.read(file);
			print(kit);
			
			file = new File(ImageKitReadTest.class.getResource(pngPath).toURI());
			kit = ImageKit.read(file);
			print(kit);
		} catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		}
	}
	
	@Test
	public void read_Bytes(){
		InputStream asStream = null;
		
		try {
			File file = new File(ImageKitReadTest.class.getResource(jpgPath).toURI());
			asStream = new FileInputStream(file);
			byte [] bytes = new byte[asStream.available()];
			asStream.read(bytes);
			
			ImageKit kit = ImageKit.read(bytes);
			print(kit);
		} catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		} finally {
			IOKit.closeQuietly(asStream);
		}
	}
	
	@Test
	public void read_InputStream(){
		InputStream asStream = null;
		try {
			File file = new File(ImageKitReadTest.class.getResource(jpgPath).toURI());
			asStream = new FileInputStream(file);
			ImageKit kit = ImageKit.read(asStream);
			print(kit);
		} catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		} finally {
			IOKit.closeQuietly(asStream);
		}
	}
	
	@Test
	public void read_Base64(){
		InputStream asStream = null;
		InputStream fileStream = null;
		
		try {
			fileStream = new FileInputStream(ImageKitReadTest.class.getResource("/base64.txt").getFile());
			byte[] base64Bytes = new byte[fileStream.available()];
			fileStream.read(base64Bytes);
			
			String base64 = StringKit.newStringUtf8(base64Bytes);
			ImageKit kit = ImageKit.read(base64);
			print(kit);
		} catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		} finally {
			IOKit.closeQuietly(fileStream);
			IOKit.closeQuietly(asStream);
		}
	}
	
	
	@Test
	public void read_URL(){
		InputStream asStream = null;
		try {
			URL url = new URL("http://5.66825.com/download/pic/000/330/7ab6bda65d3355ac6b53eab8eb3210c1.jpg");
			ImageKit kit = ImageKit.read(url, ImageType.JPG);
			print(kit);
			
			//写入文件
			kit.transferTo("d:/test.jpg");
		} catch (Exception e) {
			Assert.fail("读取文件发生错误:" + e.getMessage() + ", 异常:" + e.getClass().getName());
		} finally {
			IOKit.closeQuietly(asStream);
		}
	}
}
