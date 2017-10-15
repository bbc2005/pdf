package com.lzg.test.xml2html;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author lzg
 * @desc
 * @version 0.0.1
 * @time 创建时间：2017年10月15日 下午11:02:34
 */
public class BookTest {

	public static void main(String[] args) {
		//classpath可以通过如下代码获取
		String classpath = UserMessageTest.class.getClassLoader().getResource("").getPath();
		System.out.println("classpath:" + classpath);
		String xmlFileName = classpath + "/book.xml";
		String xslFileName = classpath + "/book.xsl";
		String htmlFileName = classpath + "/book.html";
		BookTest.Transform(xmlFileName, xslFileName, htmlFileName);
	}

	public static void Transform(String xmlFileName, String xslFileName,
			String htmlFileName) {
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);
			File xmlFile = new File(xmlFileName);
			File htmlFile = new File(htmlFileName);
			Source source = new StreamSource(xmlFile);
			Result result = new StreamResult(htmlFile);
			System.out.println(result.toString());
			t.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
