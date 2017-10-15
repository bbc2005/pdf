package com.lzg.test.test;

import java.io.FileOutputStream;

import org.xml.sax.InputSource;

/**
 * @author lzg
 * @desc
 * @version 0.0.1
 * @time 创建时间：2017年10月15日 下午5:12:34
 */
public class FopTest {

	public static void main(String[] args) {
		try {
			Driver driver = new Driver();

			// 设置要转换的fo文件名
			// driver.setInputSource(new InputSource (args[0]));
			driver.setInputSource(new InputSource(
					"f://tomcat5//webapps//myxml//xmldata//simple.fo"));

			// 设置输出文件名
			// driver.setOutputStream(new FileOutputStream(args[1]));
			driver.setOutputStream(new FileOutputStream(
					"f://tomcat5//webapps//myxml//xmldata//simple1.pdf"));

			// 设置转换类型
			// 还可以为RENDER_PCL,RENDER_PS,RENDER_TXT,RENDER_MIF
			driver.setRenderer(Driver.RENDER_PDF);
			// 开始转换
			driver.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
