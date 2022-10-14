<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:output method="text"/>
    
    <!-- Obtains the name of the root node of the input XML document -->
    <xsl:template match="/*">
        <xsl:value-of select="local-name()"/>
    </xsl:template>
</xsl:stylesheet>
