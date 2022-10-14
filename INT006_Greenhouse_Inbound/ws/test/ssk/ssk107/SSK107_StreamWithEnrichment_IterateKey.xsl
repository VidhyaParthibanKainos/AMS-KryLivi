<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:pd="urn:com.workday.report/CR_int006_Greenhouse_Inbound_Payload_Data"
    xmlns:ed="urn:com.workday.report/CR_int006_Greenhouse_Inbound_Enrichment_Data"
    xmlns:wd="urn:com.workday.report/CR_int006_Greenhouse_Inbound_Payload_Data"
    xmlns:functx="http://www.functx.com"
    exclude-result-prefixes="xsd pd ed functx"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>

    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    
    <xsl:key name="lookupKey" match="ed:Report_Entry" use="ed:Worker/ed:ID[@ed:type = 'Employee_ID']"/>
    
    <xsl:template match="Results">
        <xsl:iterate select="*">
            <xsl:param name="lookup" as="item()?"/>
            
            <xsl:choose>
                <xsl:when test="exists($lookup)">
                    <xsl:apply-templates select=".">
                        <xsl:with-param name="lookup" select="$lookup"/>
                    </xsl:apply-templates>

                    <xsl:next-iteration>
                        <xsl:with-param name="lookup" select="$lookup"/>
                    </xsl:next-iteration>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:next-iteration>
                        <xsl:with-param name="lookup" select="snapshot()"/>
                    </xsl:next-iteration>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:iterate>        
    </xsl:template>
    
    <xsl:template match="pd:Report_Data">
        <xsl:param name="lookup" as="item()?"/>

        <wd:Root>	
            <xsl:for-each select="pd:Report_Entry/copy-of()">
                <wd:Report_Entry>
                    <wd:RedundantID><xsl:value-of select="key('lookupKey', pd:Worker/pd:ID[@pd:type = 'Employee_ID'], $lookup)/ed:RedundantID"/></wd:RedundantID>

					<xsl:apply-templates select="./*"/>
                </wd:Report_Entry>
            </xsl:for-each>
        </wd:Root>
    </xsl:template>
    
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>	
</xsl:stylesheet>
