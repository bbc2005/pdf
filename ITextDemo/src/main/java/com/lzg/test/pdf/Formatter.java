package com.lzg.test.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author lzg
 * @desc
 * @version 0.0.1
 * @time 创建时间：2017年10月15日 上午10:51:20
 */
public class Formatter {

	/**
	 * iText我想大概不少人都有所耳闻，JasperReports默认的PDF支持就来自这个软件包，
	 * 它处理速度快，支持很多PDF"高级"特性，如：Annotations、AcroForms、
	 * 数字签名、加密等，支持对已有PDF的处理，通过iTextAsian.jar和iTextAsianCmaps.jar，
	 * 它对中文的支持也不错。缺点是较为依赖Java代码，需要学习不少的专有API，
	 * 当输入/输出格式有变化时，需要修改代码（除非手工写一些wrapper）， 不够灵活。目前的版本是2.1.3。具体代码：
	 * 中文支持有默认的STSong-Light等字体， 但为了优化输出效果，这里使用了文泉驿正黑字体。如果不指定中文字体，默认情况下中文字符不会显示。
	 * 
	 * @param args
	 * @throws Exception
	 *             2017年10月15日 上午10:55:53
	 */
	public static void main(String[] args) {
		Document document = null;
		try {
			System.out.print("Generating PDF");
			//1.创建Document对象 
			document = new Document(PageSize.A4);
	        String filePath = "test.pdf";
			FileOutputStream fos = new FileOutputStream(filePath); 
	        //2.创建一个PdfWriter实例 
	        PdfWriter.getInstance(document, fos); 
	        //3.打开文档 
	        document.open(); 
	        
	        //4.写入内容
			// iText自带的中文字体
			BaseFont bf1 = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			// 自定义字体
			 BaseFont bf2 = BaseFont.createFont("SIMFANG.TTF",
			 BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font1 = new Font(bf1, 12, Font.NORMAL);
			Font font2 = new Font(bf2, 12, Font.NORMAL);
			Paragraph p1 = new Paragraph("测试abc中文123", font1);
			Paragraph p2 = new Paragraph("测试abc中文123", font2);
			document.add(p1);
			document.add(p2);
			System.out.println("Done.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//5.关闭文档 
			document.close();
		}
	}

	// 产生PDF字体
	public static Font setChineseFont() {
		BaseFont bf = null;
		Font fontChinese = null;
		try {
			bf = BaseFont.createFont("SIMFANG.TTF",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			fontChinese = new Font(bf, 12, Font.NORMAL);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fontChinese;
	}

}
