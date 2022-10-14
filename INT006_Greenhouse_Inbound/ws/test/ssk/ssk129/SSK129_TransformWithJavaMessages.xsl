<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:err="http://www.w3.org/2005/xqt-errors" 
    xmlns:wd="urn:com.workday.report/INT_CR_Basic_Demographics"
	xmlns:cl="java:com.workday.custom.int006.ssk112.CloudLogEntry" 
    exclude-result-prefixes="xs cl err"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-copy"/>
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/">
    	<wd:root>
    	<xsl:for-each select="wd:Report_Data/wd:Report_Entry/copy-of()">
    		<wd:Report_Entry>
	    		<xsl:apply-templates select="wd:Worker"/>
	
	   			<wd:DayAfterDOB>
	    		<xsl:try>
	    			<xsl:value-of select="xs:date(wd:DOB) + xs:dayTimeDuration('P1D')"/>
	    			
		    		<xsl:catch>
				        <xsl:message><xsl:value-of select="cl:createMessage('warn', 'Error occurred during date calculation', $err:description, wd:Worker/wd:ID[@wd:type = 'Employee_ID'], '', xs:string(static-base-uri()), xs:string($err:code), wd:DOB)"/></xsl:message>
		    		</xsl:catch>
	    		</xsl:try>
	   			</wd:DayAfterDOB>
			</wd:Report_Entry>
    	</xsl:for-each>
		</wd:root>
    </xsl:template>
</xsl:stylesheet>
