<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:cc="http://www.capeclear.com/assembly/10"
    xmlns:spring="http://www.springframework.org/schema/beans"
    xmlns:functx="localhost"
    exclude-result-prefixes="xsd cc functx"
    version="3.0">
    
    <xsl:mode streamable="no" on-no-match="shallow-skip"/>    
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    
    <xsl:param name="localSubAssemblyList" as="xsd:string"/>
    <xsl:param name="localExemptionList" as="xsd:string"/>
    
    <xsl:template match="/">
        <xsl:variable name="matchList" select="tokenize($localSubAssemblyList, ',')"/>
        <xsl:variable name="exemptList" select="tokenize($localExemptionList, ',')"/>
        
        <DeadCode>
            <xsl:apply-templates select="/spring:beans/cc:assembly/cc:local-in">
                <xsl:with-param name="matchList" select="$matchList"/>
                <xsl:with-param name="exemptList" select="$exemptList"/>
                <xsl:with-param name="root" select="."/>
            </xsl:apply-templates>
        </DeadCode>
    </xsl:template>
    
    <xsl:template match="/spring:beans/cc:assembly/cc:local-in">
        <xsl:param name="matchList"/>
        <xsl:param name="exemptList"/>
        <xsl:param name="root"/>

        <xsl:if test="not(empty(index-of($matchList, ./@id))) and empty(index-of($exemptList, ./@id)) and empty(index-of($root/spring:beans/cc:assembly/cc:local-out/functx:substring-after-last(@endpoint, '/'), ./@id))">
            <LocalIn><xsl:value-of select="./@id"/></LocalIn>
        </xsl:if>
    </xsl:template>

    <xsl:function name="functx:substring-after-last" as="xsd:string">
        <xsl:param name="arg" as="xsd:string?"/>
        <xsl:param name="delim" as="xsd:string"/>
        
        <xsl:sequence select="replace ($arg, concat('^.*',functx:escape-for-regex($delim)), '')"/>
    </xsl:function>
    
    <xsl:function name="functx:escape-for-regex" as="xsd:string">
        <xsl:param name="arg" as="xsd:string?"/>
        
        <xsl:sequence select="replace($arg, '(\.|\[|\]|\\|\||\-|\^|\$|\?|\*|\+|\{|\}|\(|\))','\\$1')"/>        
    </xsl:function>
</xsl:stylesheet>
