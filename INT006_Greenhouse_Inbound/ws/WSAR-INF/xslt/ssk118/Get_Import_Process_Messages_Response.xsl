<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    exclude-result-prefixes="xs env">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="yes" on-no-match="deep-copy" name="replicate"/>
    
    <xsl:template match="/">
        <SoapResults xmlns:wd="urn:com.workday/bsvc">
        	<xsl:apply-templates select="env:Envelope/env:Body/wd:Get_Import_Process_Messages_Response/wd:Response_Data/wd:Import_Process_Message" mode="replicate"/>
        </SoapResults>
    </xsl:template>
</xsl:stylesheet>
