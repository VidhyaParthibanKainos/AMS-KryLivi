<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:pd="urn:com.workday.report/CR_INT_Payload_Data"
    xmlns:ed="urn:com.workday.report/CR_INT_Enrichment_Data"
    xmlns:wd="urn:com.workday.report/CR_INT_Payload_Data"
    xmlns:map="http://www.w3.org/2005/xpath-functions/map"
    xmlns:this="localhost"
    exclude-result-prefixes="xsd pd ed map this"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip" use-accumulators="EmployeeID OtherID PostalCode LookupMap"/>
    
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:accumulator name="EmployeeID" as="xsd:string" initial-value="''" streamable="yes">
        <xsl:accumulator-rule match="ed:Worker/ed:ID[@ed:type = 'Employee_ID']/text()" select="."/>
    </xsl:accumulator>
    
    <xsl:accumulator name="OtherID" as="xsd:string" initial-value="''" streamable="yes">
        <xsl:accumulator-rule match="ed:OtherID/text()" select="."/>    
    </xsl:accumulator>
    
    <xsl:accumulator name="PostalCode" as="xsd:string" initial-value="''" streamable="yes">
        <xsl:accumulator-rule match="ed:OtherAddress/ed:Postal_Code/text()" select="."/>    
    </xsl:accumulator>

    <xsl:accumulator name="LookupMap" as="map(xsd:string, item())" initial-value="map{}" streamable="yes">
        <xsl:accumulator-rule match="ed:Security/ed:Temporary_Passcode/text()" select="map:put($value, accumulator-before('EmployeeID'), this:buildLookupFragment(accumulator-before('OtherID'), accumulator-before('PostalCode'), xsd:string(.)))"/>    
    </xsl:accumulator>
    
    <xsl:template match="pd:Report_Data">
        <wd:Root>	
            <xsl:apply-templates/>
        </wd:Root>
    </xsl:template>
    
    <xsl:template match="pd:Report_Entry/pd:Worker">
        <xsl:variable name="inMemoryNode" select="copy-of()"/>
        <wd:Report_Entry>
            <xsl:variable name="employeeId" as="xsd:string" select="$inMemoryNode/pd:ID[@pd:type = 'Employee_ID']/text()"/>
            <xsl:variable name="lookupFragment" as="item()?" select="accumulator-before('LookupMap')($employeeId)"/>

            <wd:EmployeeID><xsl:value-of select="$employeeId"/></wd:EmployeeID>
            <wd:WID><xsl:value-of select="$inMemoryNode/pd:ID[@pd:type = 'WID']"/></wd:WID>
            <wd:OtherID><xsl:value-of select="$lookupFragment/OtherID"/></wd:OtherID>
            <wd:Postal_Code><xsl:value-of select="$lookupFragment/PostalCode"/></wd:Postal_Code>
            <wd:Temporary_Passcode><xsl:value-of select="$lookupFragment/TemporaryPasscode"/></wd:Temporary_Passcode>
        </wd:Report_Entry>
    </xsl:template>
    
    <xsl:function name="this:buildLookupFragment" as="item()">
        <xsl:param name="otherId" as="xsd:string"/>
        <xsl:param name="postalCode" as="xsd:string"/>
        <xsl:param name="passCode" as="xsd:string"/>
        
        <LookupFragment><OtherID><xsl:value-of select="$otherId"/></OtherID><PostalCode><xsl:value-of select="$postalCode"/></PostalCode><TemporaryPasscode><xsl:value-of select="$passCode"/></TemporaryPasscode></LookupFragment>
    </xsl:function>
</xsl:stylesheet>
