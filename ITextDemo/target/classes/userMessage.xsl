<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="1.0" encoding="UTF-8"
		standalone="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<title>使用XML+XSLT生成的HTML文件</title>
			</head>
			<body>
				<h2>我的第一个xslt 转换 html</h2>
				<table border="1">
					<tr bgcolor="#99FF66">
						<th align="center">编号</th>
						<th align="center">姓名</th>
						<th align="center">年龄</th>
						<th align="center">性别</th>
						<th align="center">联系电话</th>
					</tr>
					<!-- 循环遍历 user元素下的message 的信息 -->
					<xsl:for-each select="user/message">
						<!-- 要排序的指段 -->
						<xsl:sort select="userAge"></xsl:sort>
						<tr bgcolor="#99CCFF">
							<td>
								<xsl:value-of select="userId"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="userName"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="userAge"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="userSex"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="userTel"></xsl:value-of>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>