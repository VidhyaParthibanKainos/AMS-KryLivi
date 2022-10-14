<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" 
    xmlns:this="localhost"
    exclude-result-prefixes="xs this" 
    version="3.0">

    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="no" on-no-match="shallow-skip" name="in-memory"/>
    
    <xsl:output method="text" indent="no" encoding="UTF-8"/>
    
    <xsl:param name="localRecordCount" as="xs:integer"/>
    <xsl:variable name="outputAsArray" as="xs:boolean" select="$localRecordCount > 1"/>
    
    <xsl:template match="/*">
        <xsl:if test="$outputAsArray">
            <xsl:value-of select="'['"/>
        </xsl:if>
        <xsl:iterate select="*">
            <xsl:param name="counter" as="xs:integer" select="1"/>

            <xsl:apply-templates select="./copy-of()" mode="in-memory"/>
            <xsl:if test="$outputAsArray and $counter lt $localRecordCount">
                <xsl:value-of select="','"/>                
            </xsl:if>
            
            <xsl:next-iteration>
                <xsl:with-param name="counter" select="$counter + 1"/>
            </xsl:next-iteration>
        </xsl:iterate>
        <xsl:if test="$outputAsArray">
            <xsl:value-of select="']'"/>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="*:Report_Entry" mode="in-memory">
        <xsl:value-of select="'{'"/>
        <xsl:value-of select="this:jsonString('label', *:Size)"/><xsl:sequence select="','"/>
        <xsl:value-of select="this:jsonObject('worker', *:Worker)"/>
        <xsl:value-of select="'}'"/>
    </xsl:template>
    
    <xsl:function name="this:jsonString">
        <xsl:param name="key" as="xs:string"/>
        <xsl:param name="value" as="xs:string?"/>
        
        <xsl:value-of select="'&quot;'"/>
        <xsl:value-of select="$key"/>
        <xsl:value-of select="'&quot;: &quot;'"/>
        <xsl:value-of select="$value"/>
        <xsl:value-of select="'&quot;'"/>
    </xsl:function>

    <xsl:function name="this:jsonObject">
        <xsl:param name="key" as="xs:string"/>
        <xsl:param name="value" as="node()?"/>
        
        <xsl:value-of select="'&quot;'"/>
        <xsl:value-of select="$key"/>
        <xsl:value-of select="'&quot;: {'"/>
        <xsl:value-of select="this:jsonString('id', concat('Employee_ID=', $value/*:ID[@*:type='Employee_ID']))"/>
        <xsl:value-of select="'}'"/>
    </xsl:function>
</xsl:stylesheet>
