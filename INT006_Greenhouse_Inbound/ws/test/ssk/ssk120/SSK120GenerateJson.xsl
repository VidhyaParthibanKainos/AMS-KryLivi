<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="text" indent="no"/>

    <xsl:template match="/root">
	    	<xsl:for-each select="sample">
			{
			    "worker": {"id": "<xsl:value-of select='worker/id'/>"},
			    "grantDate": "<xsl:value-of select='grantDate'/>",
			    "grantName": "<xsl:value-of select='grantName'/>",
			    "numberOfShares" : <xsl:value-of select='numberOfShares'/>,
			    "typeOfShares" : "<xsl:value-of select='typeOfShares'/>"
			}
			<xsl:if test="position() != last()">,</xsl:if>
	    	</xsl:for-each>
	    <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="sample">
		{
		    "worker": {"id": "<xsl:value-of select='worker/id'/>"},
		    "grantDate": "<xsl:value-of select='grantDate'/>",
		    "grantName": "<xsl:value-of select='grantName'/>",
		    "numberOfShares" : <xsl:value-of select='numberOfShares'/>,
		    "typeOfShares" : "<xsl:value-of select='typeOfShares'/>"
		}
    </xsl:template>
</xsl:stylesheet>
