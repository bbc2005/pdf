package com.lzg.test.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年10月15日 上午10:44:02 
 */
public class Formatter {

	/**
	 * FOP出自Apache，在各大Java网站、论坛出现相对较低，我也是从DocBook这条线摸进来的，DocBook主要提供了一个现成的、符合一般技术书籍要求的数据结构，而展现效果（如PDF），则是通过预定义好的XSL-FO来实现的。XSL-FO是W3C的标准，正式的名称是XSL，是XSL相关的三大组件/语言中的一个，另外两个是XSLT和XPath。Apache的FOP是处理FO的众多proecessor之一，相比iText，支持的输出格式更多，对W3C相关标准支持度高，格式定义可以完全脱离具体的Java代码，十分灵活，且控制力很强。缺点是大数据量时性能较差，默认中文支持不好。目前的版本是0.95。具体代码：
	 * @param args
	 * @throws Exception
	 * 2017年10月15日 下午12:01:16
	 */
	public static void main(String[] args) throws Exception {
		//获取文件路径
		String path = Formatter.class.getClassLoader().getResource("test.xml").getPath();
		System.out.println(path);
		//classpath可以通过如下代码获取
		String base = Formatter.class.getClassLoader().getResource("").getPath();
		System.out.println(base);
		File source = new File(Formatter.class.getClassLoader().getResource("test.xml").getPath());
		File specs = new File(Formatter.class.getClassLoader().getResource("test.xsl").getPath());
		File target = new File("test.pdf");
		File config = new File(Formatter.class.getClassLoader().getResource("fop-config.xml").getPath());
		System.out.println(config.exists());
		FopFactory fopFactory = FopFactory.newInstance(config);// 读取自定义配置
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		OutputStream out = new FileOutputStream(target);
		out = new java.io.BufferedOutputStream(out);
		try {
			System.out.print("Generating PDF");
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent,
					out);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(
					specs));
			Source src = new StreamSource(source);
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(src, res);
			System.out.println("Done.");
		} finally {
			out.close();
		}
	}
	/**
	 * FOP的中文支持（其实是自定义字体支持），在0.94版本之前，十分有限，对每一个需要使用的TrueType字体，都需要生成一个metrics文件，在0.94和之后的版本，则没有这个要求，且可以自动扫描系统字体和指定文件夹中的TTF字体。如果不配置中文字体，默认情况下，中文字符在PDF中将被处理成"#"。

上面的示例代码虽然简单，但展示了FOP真正强大的地方，那就是控制力。这里篇幅有限，不可能全部特性都一一涉及，这个简单的例子至少可以让我们看到从原始的XML格式的数据，通过XSLT按照自定义的规则转换成XSL-FO，最后输出到PDF的过程，每一步都可以在Java代码之外进行严格控制。
	 */

}
