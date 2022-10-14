<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ssk="urn:com.workday.custom.ssk.common" 
    exclude-result-prefixes="xs ssk"
    version="3.0">
    
    <xsl:function name="ssk:createMessage">
		<xsl:param name="message" as="xs:string"/>
		
		<xsl:message>
			<xsl:value-of select="concat(
				'&lt;lm&gt;',
				'&lt;l&gt;info&lt;/l&gt;', 
				'&lt;m&gt;', $message, '&lt;/m&gt;', 
				'&lt;/lm&gt;'
			)"/>
		</xsl:message>
    </xsl:function>
    
    <xsl:function name="ssk:createMessage">
		<xsl:param name="level" as="xs:string"/>
		<xsl:param name="message" as="xs:string"/>
		
		<xsl:message>
			<xsl:value-of select="concat(
				'&lt;lm&gt;',
				'&lt;l&gt;', $level, '&lt;/l&gt;', 
				'&lt;m&gt;', $message, '&lt;/m&gt;',
				'&lt;/lm&gt;'
			)"/>
		</xsl:message>
    </xsl:function>
    
    <xsl:function name="ssk:createMessage">
		<xsl:param name="level" as="xs:string"/>
		<xsl:param name="message" as="xs:string"/>
		<xsl:param name="details" as="xs:string?"/>
		
		<xsl:message>
			<xsl:value-of select="concat(
				'&lt;lm&gt;',
				'&lt;l&gt;', $level, '&lt;/l&gt;', 
				'&lt;m&gt;', $message, '&lt;/m&gt;', 
				'&lt;d&gt;', $details, '&lt;/d&gt;',
				'&lt;/lm&gt;'
			)"/>
		</xsl:message>
    </xsl:function>
    
    <xsl:function name="ssk:createMessage">
		<xsl:param name="level" as="xs:string"/>
		<xsl:param name="message" as="xs:string"/>
		<xsl:param name="details" as="xs:string?"/>
		<xsl:param name="referenceId" as="xs:string"/>
		
		<xsl:message>
			<xsl:value-of select="concat(
				'&lt;lm&gt;',
				'&lt;l&gt;', $level, '&lt;/l&gt;', 
				'&lt;m&gt;', $message, '&lt;/m&gt;', 
				'&lt;d&gt;', $details, '&lt;/d&gt;', 
				'&lt;r&gt;', $referenceId, '&lt;/r&gt;',
				'&lt;/lm&gt;'
			)"/>
		</xsl:message>
    </xsl:function>
    
    <xsl:function name="ssk:createMessage">
		<xsl:param name="level" as="xs:string"/>
		<xsl:param name="message" as="xs:string"/>
		<xsl:param name="details" as="xs:string?"/>
		<xsl:param name="referenceId" as="xs:string"/>
		<xsl:param name="recordNumber" as="xs:string"/>
		<xsl:param name="localIn" as="xs:string"/>
		<xsl:param name="errorCode" as="xs:string"/>
		<xsl:param name="supportData" as="xs:string"/>
		
		<xsl:message>
			<xsl:value-of select="concat(
				'&lt;lm&gt;',
				'&lt;l&gt;', $level, '&lt;/l&gt;', 
				'&lt;m&gt;', $message, '&lt;/m&gt;', 
				'&lt;d&gt;', $details, '&lt;/d&gt;', 
				'&lt;r&gt;', $referenceId, '&lt;/r&gt;', 
				'&lt;n&gt;', $recordNumber,'&lt;/n&gt;', 
				'&lt;t&gt;', $localIn, '&lt;/t&gt;', 
				'&lt;c&gt;', $errorCode, '&lt;/c&gt;', 
				'&lt;a&gt;', $supportData, '&lt;/a&gt;',
				'&lt;/lm&gt;'
			)"/>
		</xsl:message>
    </xsl:function>
</xsl:stylesheet>
