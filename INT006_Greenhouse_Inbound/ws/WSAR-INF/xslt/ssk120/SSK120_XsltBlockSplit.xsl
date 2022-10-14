<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" 
    exclude-result-prefixes="xs" version="3.0">
    
    <xsl:mode streamable="yes"/>
    <xsl:param name="localBlockSize"/>
    
    <xsl:template match="/*">
        <xsl:variable name="rootCopy">
            <xsl:copy copy-namespaces="yes"/>
        </xsl:variable>
        
        <Blocks>
            <xsl:for-each-group _select="*" group-adjacent="floor((position() -1) div $localBlockSize)">
                <xsl:element namespace="{namespace-uri($rootCopy/*)}" name="{name($rootCopy/*)}">
                    <xsl:copy-of select="current-group()" />
                </xsl:element>
            </xsl:for-each-group>
        </Blocks>
    </xsl:template>
    
</xsl:stylesheet>
