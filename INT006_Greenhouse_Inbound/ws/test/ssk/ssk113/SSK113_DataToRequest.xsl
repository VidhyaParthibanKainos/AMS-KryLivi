<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="xsd"
    version="3.0">
    
    <xsl:param name="inApiVersion" as="xsd:string"/>
    
	<xsl:mode streamable="yes" on-no-match="deep-copy"/>

    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/">
    	<xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="wd:Batch">
		<wd:Import_Eligible_Earnings_Override_Request xmlns:wd="urn:com.workday/bsvc" wd:version="{$inApiVersion}">
			<wd:Eligible_Earnings_Override_Period_Reference>
				<wd:ID wd:type="Eligible_Earnings_Period_ID">EARN_PD_2019</wd:ID>
			</wd:Eligible_Earnings_Override_Period_Reference>

			<xsl:apply-templates/>

		</wd:Import_Eligible_Earnings_Override_Request>
    </xsl:template>
</xsl:stylesheet>
