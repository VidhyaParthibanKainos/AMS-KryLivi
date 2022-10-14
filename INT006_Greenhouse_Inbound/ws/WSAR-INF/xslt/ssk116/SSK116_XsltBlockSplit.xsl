<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" 
	xmlns:ctx="java:com.capeclear.mediation.MediationContext"
	xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
	xmlns:jt="http://saxon.sf.net/java-type" 
	xmlns:mch="urn:com.workday.ps/MediationContextHelper"
	exclude-result-prefixes="xs ctx tube jt mch" version="3.0">

	<xsl:mode streamable="yes" />

	<!-- 
		This XSLT is used to split up an incoming XML file into a number of 
		blocks. The block size and the XPath query to select the records to be included 
		in the output are specified using the inBlockSize and inItemXPath context 
		properties, respectively. 
		
		NOTE: The XPath expression must be streamable. 
		
		It also should be noted that the stylesheet needs to directly retrieve these 
		properties from the MediationContext since the xslt+ is currently unable 
		to automatically populate static parameters with context properties. 
	-->
	<xsl:param name="MediationContext" select="tube:getCurrentMediationContext()" static="yes" />
	<xsl:param name="inItemXPath" as="xs:string" select="let $value:=ctx:getProperty($MediationContext,'inItemXPath') return if ($value) then $value else '/*/*'" static="yes" />
	<xsl:param name="inBlockSize" as="xs:integer" />

	<xsl:template match="/">
		<Blocks>
			<xsl:for-each-group _select="{$inItemXPath}" group-adjacent="floor((position() -1) div $inBlockSize)">
				<Block>
					<xsl:copy-of select="current-group()" />
				</Block>
			</xsl:for-each-group>
		</Blocks>
	</xsl:template>

</xsl:stylesheet>
