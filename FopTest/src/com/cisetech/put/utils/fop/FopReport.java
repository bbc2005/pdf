package com.cisetech.put.utils.fop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.avalon.framework.logger.Logger;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 * FopReport
 * @author bin.yin 2012/12/23
 */
public class FopReport {
	private static Logger log = new ConsoleLogger(ConsoleLogger.LEVEL_DEBUG);
	// Step 1: Construct a FopFactory
	private static final FopFactory fopFactory = FopFactory.newInstance();

	/**
	 * 根据xsl模板及xml数据文件生成pdf
	 * @param xsltFile xsl模板
	 * @param xmlFile xml数据文件
	 * @return ReportData
	 * @throws Exception
	 * @author bin.yin 2012/12/25
	 */
	public static ReportData createReport(String xsltFile, String xmlFile) throws Exception {
		ReportData reportData = new ReportData();
		reportData.setContentType("application/pdf");
		fopFactory.setUserConfig("conf/fop.xml");

		// Step 2: Set up output stream.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			// Step 3: Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Step 4: Setup XSLT using identity transformer
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltFile)));

			// Step 5: Setup input and output for XSLT transformation
			Source src = new StreamSource(new File(xmlFile));
			// Source src = new StreamSource(new StringReader(myString));

			// Step 6: Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Step 7: Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			reportData.setData(out.toByteArray());
		} catch(Exception e) {
			throw e;
		} finally {
			out.close();
		}
		return reportData;
	}

	/**
	 * 根据xsl模板及xml字节数组生成pdf
	 * @param xsltFile xsl模板
	 * @param bXmlData xml字节数组 eg. StringBuffer buf = new StringBuffer(); buf.getBytes("UTF-8");
	 * @return ReportData
	 * @throws Exception
	 * @author bin.yin 2012/12/25
	 */
	public static ReportData createReport(String xsltFile, byte[] bXmlData) throws Exception {
		ReportData reportData = new ReportData();
		try {
			// convert xml bytes to a temp file
			File xmlFile = File.createTempFile("FOP", ".tmp");
			FileOutputStream fos = new FileOutputStream(xmlFile);
			fos.write(bXmlData);
			fos.close();
			
			reportData = createReport(xsltFile, xmlFile.getAbsolutePath());
			// delete temp file
			xmlFile.delete();
		} catch (Exception e) {
			throw e;
		}
		return reportData;
	}

	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buf.append("<ItemListReport>");
			buf.append("	<ReportHeader>");
			buf.append("		<Title>附加条款</Title>");
			buf.append("		<PartyA>上海信息技术有限公司B</PartyA>");
			buf.append("		<PartyB>上海信息技术有限公司B</PartyB>");
			buf.append("	</ReportHeader>");
			buf.append("	<ReportBody>");
			buf.append("		<Table>");
			buf.append("			<TableRow>");
			buf.append("				<ItemName>附加条款1</ItemName>");
			buf.append("				<ItemTime>2012-12-23 09:03</ItemTime>");
			buf.append("			</TableRow>");
			buf.append("			<TableRow>");
			buf.append("				<ItemName>上海信息技术有限公司附加条款1</ItemName>");
			buf.append("				<ItemTime>2012-12-23 09:03</ItemTime>");
			buf.append("			</TableRow>");
			buf.append("		</Table>");
			buf.append("	</ReportBody>");
			buf.append("	<ReportFooter>");
			buf.append("		<PrintDate>2012-12-12</PrintDate>");
			buf.append("		<ReportNo>010123456789</ReportNo>");
			buf.append("	</ReportFooter>");
			buf.append("</ItemListReport>");
			
			long t = System.currentTimeMillis();
			ReportData data = FopReport.createReport("report\\sample\\Sample.xsl", buf.toString().getBytes("UTF-8"));
			//data = FopReport.createReport("report\\sample\\Sample.xsl", "report\\sample\\Sample.xml");
			long t1 = System.currentTimeMillis();
			log.debug("time:" + (t1 - t));
			FileOutputStream fos = new FileOutputStream("D:/sample.pdf");
			fos.write(data.getData());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("use time:" + (System.currentTimeMillis() - t0));
	}
}
