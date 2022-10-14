<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:cc="http://www.capeclear.com/assembly/10"
    xmlns:spring="http://www.springframework.org/schema/beans"
    exclude-result-prefixes="xsd cc spring"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>    
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    
    <xsl:key name="lookupKey" match="spring:beans/cc:assembly/cc:local-in" use="@id"/>
    
    <xsl:template match="Root">
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
    
    <xsl:template match="SubAssemblies">
        <xsl:param name="lookup" as="item()?"/>
        
        <Root>	
            <xsl:for-each select="SubAssembly/copy-of()">
                <xsl:if test="not(exists(key('lookupKey', ./text(), $lookup)/@id))">
                    <MissingSubAssembly><xsl:value-of select="./text()"/></MissingSubAssembly>
                </xsl:if>
            </xsl:for-each>
        </Root>
    </xsl:template>
    
</xsl:stylesheet>
