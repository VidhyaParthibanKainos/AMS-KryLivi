<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet version="3.0"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:bsvc="urn:com.workday/bsvc">

    <xsl:output method="xml" indent="no"/>

    <xsl:param name="inApiVersion" as="xs:string"/>

    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bsvc="urn:com.workday/bsvc">
           <soapenv:Header>
              <bsvc:Workday_Common_Header>
                 <bsvc:Include_Reference_Descriptors_In_Response>false</bsvc:Include_Reference_Descriptors_In_Response>
              </bsvc:Workday_Common_Header>
           </soapenv:Header>
           <soapenv:Body>
              <bsvc:Import_Eligible_Earnings_Override_Request bsvc:version="{$inApiVersion}">
				 <bsvc:Eligible_Earnings_Override_Period_Reference>
					<bsvc:ID bsvc:type="Eligible_Earnings_Period_ID">EARN_PD_2019</bsvc:ID>
				 </bsvc:Eligible_Earnings_Override_Period_Reference>

					<xsl:apply-templates select="Map/Entry"/> 

              </bsvc:Import_Eligible_Earnings_Override_Request>
           </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template> 
    
    <xsl:template match="/Map/Entry">
	    <bsvc:Eligible_Earnings>
	        <bsvc:Eligible_Earnings_Data>
	            <bsvc:Eligible_Earnings_ID>31001_EARN_PD_2019</bsvc:Eligible_Earnings_ID>
	            <bsvc:Employee_Reference><bsvc:ID bsvc:type="{./Var[Key eq 'WorkerReferenceIdType']/Value}"><xsl:value-of select="./Var[Key eq 'WorkerReferenceId']/Value"/></bsvc:ID></bsvc:Employee_Reference>
	            <bsvc:Apply_to_All_Bonus_Plans>true</bsvc:Apply_to_All_Bonus_Plans>
	            <bsvc:Position_Reference><bsvc:ID bsvc:type="Position_ID"><xsl:value-of select="concat('POS_000', ./Var[Key eq 'WorkerReferenceId']/Value)"/></bsvc:ID></bsvc:Position_Reference>
	            <bsvc:Amount>1001.00</bsvc:Amount>
	            <bsvc:Currency_Reference><bsvc:ID bsvc:type="Currency_ID">USD</bsvc:ID></bsvc:Currency_Reference>
	        </bsvc:Eligible_Earnings_Data>
	    </bsvc:Eligible_Earnings>
    </xsl:template>
</xsl:stylesheet>
