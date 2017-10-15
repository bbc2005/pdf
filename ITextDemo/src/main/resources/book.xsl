<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>使用XML+XSLT生成的HTML文件</title>
            </head>
            <body>
                <xsl:apply-templates select="book"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="chapter">
        <br/>
        <br/>
        <xsl:value-of select="./title"/>
        <xsl:apply-templates select="./section"/>
    </xsl:template>
    <xsl:template match="chapter/section">
        <br/>
        <br/>
        <xsl:text>    </xsl:text>
        <!--<xsl:number format="1. " level="multiple"/>-->
        <xsl:number format="1. " level="multiple" count="chapter | section" from="book"/>
        <xsl:value-of select="./title"/>
        <xsl:apply-templates select="./section"/>
    </xsl:template>
    <xsl:template match="chapter/section/section">
        <br/>
        <br/>
        <xsl:text>        </xsl:text>
        <!--<xsl:number format="1. " level="multiple"/>-->
        <xsl:number format="1. " level="multiple" count="chapter | section" from="book"/>
        <xsl:value-of select="./title"/>
        <xsl:number value="123456789" grouping-separator="," grouping-size="3"/>
    </xsl:template>
</xsl:stylesheet>
