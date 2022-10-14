<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="xsd"
    version="3.0">
    
    <xsl:template match="wd:Supplier_Item_Price_Update_Request">
        <xsl:param name="lookup" as="item()?"/>
        
        <wd:Messages>
            <xsl:variable name="messageRecord" as="item()*" select="key('lookupKey', '0' , $lookup)"/>
            <xsl:if test="exists($messageRecord)">
                <xsl:apply-templates select="$messageRecord" mode="in-memory"/>                
            </xsl:if>
            <xsl:iterate select="*">
                <xsl:param name="p" as="xsd:integer?"/>
                
                <xsl:choose>
                    <xsl:when test="exists($p)">
                        <xsl:variable name="messageRecord" as="item()*" select="key('lookupKey', xsd:string($p), $lookup)"/>
                        
                        <xsl:if test="exists($messageRecord)">
                            <xsl:apply-templates select=".">
                                <xsl:with-param name="p" select="$p"/>
                                <xsl:with-param name="messageRecord" select="$messageRecord"/>
                            </xsl:apply-templates>
                        </xsl:if>
                        
                        <xsl:next-iteration>
                            <xsl:with-param name="p" select="$p + 1"/>
                        </xsl:next-iteration>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:next-iteration>
                            <xsl:with-param name="p" select="1"/>
                        </xsl:next-iteration>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:iterate>
        </wd:Messages>
    </xsl:template>    
    
    <xsl:template match="wd:Supplier_Item_Data">
        <xsl:param name="p" as="xsd:integer"/>
        <xsl:param name="messageRecord" as="item()*"/>
        
        <xsl:variable name="requestRecord" as="item()?" select="copy-of(.)"/>
        
        <xsl:for-each select="$messageRecord">
            <xsl:apply-templates select="." mode="in-memory">
                <xsl:with-param name="requestRecord" select="$requestRecord"/>
            </xsl:apply-templates>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="wd:Import_Process_Message" mode="in-memory">
        <xsl:param name="requestRecord" as="item()?"/>

        <wd:Message>
            <wd:Timestamp><xsl:value-of select="./wd:Import_Process_Message_Data/wd:Timestamp"/></wd:Timestamp>
            <wd:Severity><xsl:value-of select="./wd:Import_Process_Message_Data/wd:Severity"/></wd:Severity>
            <wd:Message_Summary><xsl:value-of select="./wd:Import_Process_Message_Data/wd:Message_Summary"/></wd:Message_Summary>
            <wd:Line_Number><xsl:value-of select="./wd:Import_Process_Message_Data/wd:Line_Number"/></wd:Line_Number>
            <wd:Request_Data>
                <xsl:copy-of select="$requestRecord"/>
            </wd:Request_Data>
        </wd:Message>
    </xsl:template>
</xsl:stylesheet>
