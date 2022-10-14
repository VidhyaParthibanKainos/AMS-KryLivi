<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:cl="java:com.workday.custom.int006.ssk112.CloudLogEntry" 
    exclude-result-prefixes="xs cl"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-copy"/>
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <xsl:message><xsl:value-of select="cl:createMessage('info', 'Summary Message', 'Message details')"/></xsl:message>
        <xsl:apply-templates/>
    </xsl:template>
</xsl:stylesheet>
