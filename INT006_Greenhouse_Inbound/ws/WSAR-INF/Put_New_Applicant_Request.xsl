<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:bsvc="urn:com.workday/bsvc">

	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	<xsl:param name="applicantID"/>
	<xsl:param name="emailPersonalType"/>
	<xsl:param name="emailWorkType"/>
	<xsl:param name="applicantCountryId"/>
	<xsl:param name="sourceId"/>
	<xsl:param name="sourceIdMapped"/>
	<xsl:param name="phone"/>

	<xsl:template match="root">
		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
			xmlns:bsvc="urn:com.workday/bsvc">
			<soapenv:Body>
				<bsvc:Put_Applicant_Request bsvc:Add_Only="true" bsvc:version="v36.0">
					<xsl:apply-templates select="data/payload/application"/>
				</bsvc:Put_Applicant_Request>
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>

	<xsl:template match="data/payload/application">
		<bsvc:Applicant_Data>
			<!--Optional:-->
			<!--  <bsvc:Applicant_ID>
				<xsl:value-of select="$applicantID"/>
			</bsvc:Applicant_ID>-->
			<!--Optional:-->
			<bsvc:Personal_Data>
				<bsvc:Name_Data>
					<!--Optional:-->
					<bsvc:Legal_Name_Data>
						<bsvc:Name_Detail_Data>
							<bsvc:Country_Reference>
								<bsvc:ID bsvc:type="ISO_3166-1_Alpha-3_Code">
									<xsl:value-of select="$applicantCountryId"/>
								</bsvc:ID>
							</bsvc:Country_Reference>
							<bsvc:First_Name>
								<xsl:value-of select="candidate/first_name"/>
							</bsvc:First_Name>
							<bsvc:Last_Name>
								<xsl:value-of select="candidate/last_name"/>
							</bsvc:Last_Name>
						</bsvc:Name_Detail_Data>
					</bsvc:Legal_Name_Data>
				</bsvc:Name_Data>
				<bsvc:Contact_Data>
					<xsl:if test="candidate/email_addresses/data/type = 'personal'">
						<bsvc:Email_Address_Data>
							<bsvc:Email_Address>
								<xsl:value-of select="candidate/email_addresses/data/value"/>
							</bsvc:Email_Address>
							<bsvc:Usage_Data bsvc:Public="false">
								<bsvc:Type_Data bsvc:Primary="true">
									<bsvc:Type_Reference>
										<bsvc:ID bsvc:type="Communication_Usage_Type_ID">
											<xsl:value-of select="$emailPersonalType"/>
										</bsvc:ID>
									</bsvc:Type_Reference>
								</bsvc:Type_Data>
							</bsvc:Usage_Data>
						</bsvc:Email_Address_Data>
					</xsl:if>
					<xsl:if test="offer/custom_fields/on_work_email/value != 'null'">
						<bsvc:Email_Address_Data>
							<bsvc:Email_Address>
								<xsl:value-of select="offer/custom_fields/on_work_email/value"/>
							</bsvc:Email_Address>
							<bsvc:Usage_Data bsvc:Public="true">
								<bsvc:Type_Data bsvc:Primary="true">
									<bsvc:Type_Reference>
										<bsvc:ID bsvc:type="Communication_Usage_Type_ID">
											<xsl:value-of select="$emailWorkType"/>
										</bsvc:ID>
									</bsvc:Type_Reference>
								</bsvc:Type_Data>
							</bsvc:Usage_Data>
						</bsvc:Email_Address_Data>
					</xsl:if>
				</bsvc:Contact_Data>
			</bsvc:Personal_Data>		
			<xsl:if test="$sourceIdMapped = 'true'">
				<bsvc:Recruiting_Data>
					<bsvc:Applicant_Source_Reference>
						<bsvc:ID bsvc:type="Applicant_Source_ID">
							<xsl:value-of select="$sourceId"/>
						</bsvc:ID>
					</bsvc:Applicant_Source_Reference>
				</bsvc:Recruiting_Data>
			</xsl:if>
		</bsvc:Applicant_Data>
	</xsl:template>
</xsl:stylesheet>
