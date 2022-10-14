<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    exclude-result-prefixes="env">

    <xsl:output method="xml" indent="no"/>

    <xsl:mode streamable="yes" on-no-match="shallow-copy"/>

	<xsl:template match="/">
		<xsl:apply-templates select="/env:Envelope/env:Body/*"/>
	</xsl:template>
</xsl:stylesheet>
