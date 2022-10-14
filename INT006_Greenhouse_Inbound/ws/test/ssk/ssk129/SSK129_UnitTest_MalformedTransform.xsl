<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-copy"/>
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/*[SomeFilter = 'NotAllowedInStreamingMatch']">
        <xsl:apply-templates/>
    </xsl:template>
</xsl:stylesheet>
