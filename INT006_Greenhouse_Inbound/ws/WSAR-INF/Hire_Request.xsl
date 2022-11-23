<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:bsvc="urn:com.workday/bsvc">

	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	<xsl:param name="applicantID"/>
	<xsl:param name="hireReason"/>
	<xsl:param name="fullOrPartTime"/>
	<xsl:param name="costCenter"/>	
	<xsl:param name="marketOrgID"/>
	<xsl:param name="DepartmentOrgID"/>
	<xsl:param name="BusinessUnitOrgID"/>	
	<xsl:param name="ProductOrgID"/>
	<xsl:param name="areaOrgID"/>
	<xsl:param name="teamOrgID"/>
	<xsl:param name="functionOrgID"/>	
	<xsl:param name="compensationGrade"/>
	<xsl:param name="compensationPackage"/>
	<xsl:param name="compGradeProfileId"/>
	<xsl:param name="jobRequisitionID"/>	
	<xsl:param name="isLocationChange"/>
	<xsl:param name="scheduledWeeklyHours"/>
	<xsl:param name="employeeType"/>	
	<xsl:param name="jobProfileId"/>
	<xsl:param name="jobPostingTitle"/>
	<xsl:param name="workShift"/>	
	<xsl:param name="supOrg"/>
	<xsl:param name="hireDate"/>
	<xsl:param name="locationId"/>
	<xsl:param name="endDate"/>
	<xsl:param name="jobReqScheduledWeeklyHours"/>
	<xsl:param name="hireReasonId"/>
	<xsl:param name="jobReqFreq"/>
	<xsl:param name="attachedFileName"/>
	<xsl:param name="company"/>
	<xsl:param name="positionID"/>
	<xsl:param name="p.ContentType"/>
	<xsl:param name="p.EncodedFile"/>
	<xsl:param name="jobReqSalaryCompPlan"/>
	<xsl:param name="jobReqHourlyCompPlan"/>
	<xsl:param name="worker_sub_type_GH"/>
	<xsl:param name="time_type_GH"/>
	<xsl:param name="employment_type_GH"/>
	<xsl:param name="isRehire"/>
	<xsl:param name="department_GH"/>
	<xsl:param name="defaultJobTitle"/>
	<xsl:param name="pay_rate_type_GH"/>
	<xsl:param name="Social_Security_Number"/>
	<xsl:param name="forskrivarkod_ID"/>
	<xsl:param name="countryId"/>
	
	
	<xsl:template match="root">
		<xsl:if test="$employment_type_GH = 'Employee'">
			<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				xmlns:bsvc="urn:com.workday/bsvc">
				<soapenv:Body>
					<bsvc:Hire_Employee_Request bsvc:version="v38.0">
					
					    <xsl:if test="$p.EncodedFile != ''">
						<!--  Attach offer letter to the hire -->
							 <bsvc:Business_Process_Parameters>			          
					            <bsvc:Auto_Complete>1</bsvc:Auto_Complete>
					            <bsvc:Business_Process_Attachment_Data>	
							 		<bsvc:File_Name>
		               					<xsl:value-of select="$attachedFileName"></xsl:value-of>
		               				</bsvc:File_Name>	                          	
		                           <bsvc:File>
		                           	<xsl:value-of select="$p.EncodedFile"></xsl:value-of>
		                           </bsvc:File>
		                           <bsvc:Content_Type><xsl:value-of select="$p.ContentType"></xsl:value-of></bsvc:Content_Type>
		          			  </bsvc:Business_Process_Attachment_Data>	
					         </bsvc:Business_Process_Parameters>
				         </xsl:if>			
						<bsvc:Hire_Employee_Data>
							<xsl:apply-templates select="data/payload/application"/>
							<xsl:apply-templates select="data/payload/application/offer/custom_fields"/>
						</bsvc:Hire_Employee_Data>
					</bsvc:Hire_Employee_Request>
				</soapenv:Body>
			</soapenv:Envelope>
		</xsl:if>
	</xsl:template>

	<xsl:template match="data/payload/application">
	
	 <!--Optional:-->
        <bsvc:Organization_Reference>               
               <bsvc:ID bsvc:type="Organization_Reference_ID">
               		<xsl:value-of select="$supOrg"/>
               </bsvc:ID>
        </bsvc:Organization_Reference>
                    <bsvc:Position_Reference>
               <!--Zero or more repetitions:-->
               <bsvc:ID bsvc:type="Position_ID">
               		<xsl:value-of select="$positionID"/>
               </bsvc:ID>
            </bsvc:Position_Reference>
        
        
		<bsvc:Applicant_Reference>
			<bsvc:ID bsvc:type="Applicant_ID">
				<xsl:value-of select="$applicantID"/>
			</bsvc:ID>
		</bsvc:Applicant_Reference>
		<!-- <bsvc:Job_Requisition_Reference>
			<bsvc:ID bsvc:type="Job_Requisition_ID">
				<xsl:value-of select="$jobRequisitionID"/>
			</bsvc:ID>
		</bsvc:Job_Requisition_Reference> -->
		
		
		<bsvc:Hire_Date>
			<xsl:choose>
				<xsl:when test="offer/starts_at &lt; $hireDate">
					<xsl:value-of select="$hireDate"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="offer/starts_at"/>
				</xsl:otherwise>				
			</xsl:choose>			
		</bsvc:Hire_Date>
		<bsvc:Hire_Employee_Event_Data>
			<bsvc:Employee_Type_Reference>
				<bsvc:ID bsvc:type="Employee_Type_ID">
					<xsl:value-of select="$worker_sub_type_GH"/>
				</bsvc:ID>
			</bsvc:Employee_Type_Reference>
			<bsvc:Hire_Reason_Reference>                 
                  <bsvc:ID bsvc:type="Event_Classification_Subcategory_ID">
                  		<xsl:value-of select="$hireReasonId"/>
                  </bsvc:ID>
              </bsvc:Hire_Reason_Reference>		
			
			<xsl:if
				test="$worker_sub_type_GH != 'Regular'">
				<bsvc:Employment_End_Date>
					<xsl:value-of select="offer/custom_fields/end_date/value"/>
				</bsvc:Employment_End_Date>
			</xsl:if>
		
			<bsvc:Position_Details>
				<bsvc:Job_Profile_Reference>
					<bsvc:ID bsvc:type="Job_Profile_ID">
						<xsl:value-of select="$jobProfileId"/>
					</bsvc:ID>
				</bsvc:Job_Profile_Reference>
				<bsvc:Position_Title>
					<xsl:value-of select="$defaultJobTitle"/>
				</bsvc:Position_Title>
				<bsvc:Business_Title>
						<xsl:value-of select="$defaultJobTitle"/>
				</bsvc:Business_Title>				
				<bsvc:Location_Reference>
					<bsvc:ID bsvc:type="Location_ID">
						<xsl:value-of select="$locationId"/>
					</bsvc:ID>
				</bsvc:Location_Reference>
				<bsvc:Position_Time_Type_Reference>
					<bsvc:ID bsvc:type="Position_Time_Type_ID">
						<xsl:value-of select="$time_type_GH"/>
					</bsvc:ID>
				</bsvc:Position_Time_Type_Reference>
				<xsl:choose>
					<xsl:when test="$isLocationChange = 'true' and $time_type_GH = 'Full_time'">
						<bsvc:Scheduled_Hours>
							<xsl:value-of select="$scheduledWeeklyHours"/>
						</bsvc:Scheduled_Hours>
					</xsl:when>
					<xsl:otherwise>
						<bsvc:Scheduled_Hours>
								<xsl:value-of select="$jobReqScheduledWeeklyHours"/>
							</bsvc:Scheduled_Hours>
					</xsl:otherwise>
				</xsl:choose>			
					
				<bsvc:Pay_Rate_Type_Reference>
					<bsvc:ID bsvc:type="Pay_Rate_Type_ID">
					<!--  Case:CS0038060 Mapping to value in GH instead of getting it from JobReq -->
						<!-- <xsl:choose>
							<xsl:when test="$jobReqSalaryCompPlan != '' or $jobReqHourlyCompPlan != ''">
							    <xsl:choose>
							        <xsl:when test="$jobReqSalaryCompPlan != ''">
							            <xsl:value-of select="'Salary'"/>
							        </xsl:when>
							        <xsl:otherwise>
							            <xsl:value-of select="'Hourly'"/>
							        </xsl:otherwise>
							    </xsl:choose>								
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="'Hourly'"/>
							</xsl:otherwise>
						</xsl:choose> -->
							<xsl:value-of select="$pay_rate_type_GH"/>
					</bsvc:ID>
				</bsvc:Pay_Rate_Type_Reference>
				<xsl:if test="$isRehire = 'false' and $countryId = 'SWE'">
					 <bsvc:Additional_Job_Classifications_Reference>                   
	                     <bsvc:ID bsvc:type="Job_Classification_Reference_ID">
	                     	<xsl:value-of select="'001_-_001_(Work_ID-Sweden)'"/>
	                     </bsvc:ID>
	                 </bsvc:Additional_Job_Classifications_Reference>
                 </xsl:if>
                 <!-- <xsl:if test="$department_GH = 'Clinicians'">
					 <bsvc:Additional_Job_Classifications_Reference>                   
	                     <bsvc:ID bsvc:type="Job_Classification_Group_ID">
	                     	<xsl:value-of select="'Speciality'"/>
	                     </bsvc:ID>
	                 </bsvc:Additional_Job_Classifications_Reference>
                 </xsl:if> -->                  								
			</bsvc:Position_Details>
		</bsvc:Hire_Employee_Event_Data>
	</xsl:template>

	<xsl:template match="data/payload/application/offer/custom_fields">
		<bsvc:Propose_Compensation_for_Hire_Sub_Process>
		
			<bsvc:Business_Sub_Process_Parameters>
				<bsvc:Auto_Complete>false</bsvc:Auto_Complete>
			</bsvc:Business_Sub_Process_Parameters>
			
			<bsvc:Propose_Compensation_for_Hire_Data>
				<bsvc:Primary_Compensation_Basis>
					<xsl:value-of select="base_pay_amount/value/amount"/>
				</bsvc:Primary_Compensation_Basis>
				<bsvc:Compensation_Guidelines_Data>
					<bsvc:Compensation_Package_Reference>
						<bsvc:ID bsvc:type="Compensation_Package_ID">
							<xsl:value-of select="$compensationPackage"/>
						</bsvc:ID>
					</bsvc:Compensation_Package_Reference>
					<bsvc:Compensation_Grade_Reference>
						<bsvc:ID bsvc:type="Compensation_Grade_ID">
							<xsl:value-of select="$compensationGrade"/>
						</bsvc:ID>
					</bsvc:Compensation_Grade_Reference>
					<bsvc:Compensation_Grade_Profile_Reference>
						<bsvc:ID bsvc:type="Compensation_Grade_Profile_ID">
							<xsl:value-of select="$compGradeProfileId"/>
						</bsvc:ID>
					</bsvc:Compensation_Grade_Profile_Reference>
				</bsvc:Compensation_Guidelines_Data>
				<bsvc:Pay_Plan_Data>
					<bsvc:Pay_Plan_Sub_Data>
						<bsvc:Pay_Plan_Reference bsvc:Descriptor="Compensation_Plan_ID">
                           	<bsvc:ID bsvc:type="Compensation_Plan_ID">
							    <xsl:choose>
							        <xsl:when test="$jobReqSalaryCompPlan != ''">
							            <xsl:value-of select="$jobReqSalaryCompPlan"/>
							        </xsl:when>
							        <xsl:otherwise>
							            <xsl:value-of select="'Hourly_Plan'"/>
							        </xsl:otherwise>
							    </xsl:choose>                           		
                           	</bsvc:ID>
                        </bsvc:Pay_Plan_Reference>
						<bsvc:Amount>
							<xsl:value-of select="base_pay_amount/value/amount"/>
						</bsvc:Amount>
						<bsvc:Currency_Reference>
							<bsvc:ID bsvc:type="Currency_ID">
								<xsl:value-of select="base_pay_amount/value/unit"/>
							</bsvc:ID>
						</bsvc:Currency_Reference>
						<bsvc:Frequency_Reference>
							<bsvc:ID bsvc:type="Frequency_ID">
								<xsl:value-of select="$jobReqFreq"/>
							</bsvc:ID>							
						</bsvc:Frequency_Reference>
					</bsvc:Pay_Plan_Sub_Data>
				</bsvc:Pay_Plan_Data>
			</bsvc:Propose_Compensation_for_Hire_Data>
		</bsvc:Propose_Compensation_for_Hire_Sub_Process>

		<bsvc:Edit_Assign_Organization_Sub_Process>
			<bsvc:Business_Sub_Process_Parameters>
				<bsvc:Auto_Complete>true</bsvc:Auto_Complete>
			</bsvc:Business_Sub_Process_Parameters>
			<bsvc:Position_Organization_Assignments_Data>
				<bsvc:Company_Assignments_Reference>
					<bsvc:ID bsvc:type="Company_Reference_ID">
						<xsl:value-of select="$company"/>
					</bsvc:ID>
				</bsvc:Company_Assignments_Reference>
				<bsvc:Cost_Center_Assignments_Reference>
					<bsvc:ID bsvc:type="Cost_Center_Reference_ID">
						<xsl:value-of select="$costCenter"/>
					</bsvc:ID>
				</bsvc:Cost_Center_Assignments_Reference>

			  <xsl:if test="$BusinessUnitOrgID != ''">
					<bsvc:Custom_Organization_Assignment_Data>
						<bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="DNU">
							<bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
							    <xsl:value-of select="$BusinessUnitOrgID"/>
							</bsvc:ID>							
						</bsvc:Custom_Organization_Assignment_Reference>
					</bsvc:Custom_Organization_Assignment_Data>
				</xsl:if>	
			   <xsl:if test="$DepartmentOrgID != ''">
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="Department">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$DepartmentOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			    </xsl:if>
			 
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="Product">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$ProductOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			  
			   <xsl:if test="$areaOrgID != ''">
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="Area_R&amp;D">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$areaOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			    </xsl:if>
			    <xsl:if test="$functionOrgID != ''">
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="Function_R&amp;D">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$functionOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			    </xsl:if>
			    <xsl:if test="$marketOrgID != ''">
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="Market">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$marketOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			    </xsl:if>
			    <xsl:if test="$teamOrgID != ''">
			        <bsvc:Custom_Organization_Assignment_Data>
			            <bsvc:Custom_Organization_Assignment_Reference bsvc:Descriptor="TEAM_RD">
			                <bsvc:ID bsvc:type="Custom_Organization_Reference_ID">
			                    <xsl:value-of select="$teamOrgID"/>
			                </bsvc:ID>
			            </bsvc:Custom_Organization_Assignment_Reference>
			        </bsvc:Custom_Organization_Assignment_Data>
			    </xsl:if>
			</bsvc:Position_Organization_Assignments_Data>
		</bsvc:Edit_Assign_Organization_Sub_Process>
		
		  <!--  CaseCS0038060 Identifiers--> 
	 <bsvc:Edit_Government_IDs_Sub_Process>
	    
	        <bsvc:Business_Sub_Process_Parameters>	           
	            <bsvc:Auto_Complete>true</bsvc:Auto_Complete>	            
	        </bsvc:Business_Sub_Process_Parameters>
	    
	        <bsvc:Government_Identification_Data>	          
	           <!-- <bsvc:National_ID>	               
	                <bsvc:National_ID_Reference bsvc:Descriptor="?">	                 
	                    <bsvc:ID bsvc:type="?">?</bsvc:ID>
	                </bsvc:National_ID_Reference>	               
	                <bsvc:National_ID_Data>	                    
	                    <bsvc:ID>?</bsvc:ID>	                 
	                    <bsvc:ID_Type_Reference bsvc:Descriptor="?">	                     
	                        <bsvc:ID bsvc:type="?">?</bsvc:ID>
	                    </bsvc:ID_Type_Reference>	                  
	                </bsvc:National_ID_Data>	
	            </bsvc:National_ID>-->	
	            <xsl:if test="$countryId = 'SWE'">      
		            <bsvc:Government_ID>
		                <bsvc:Government_ID_Data>	                  
		                  <bsvc:ID>
	                           	<xsl:value-of select="$forskrivarkod_ID"/>
	                     </bsvc:ID>                        
	                      <bsvc:ID_Type_Reference>                           
	                         <bsvc:ID bsvc:type="Government_ID_Type_ID">SWE_Förskrivarkod</bsvc:ID>
	                      </bsvc:ID_Type_Reference>   
	                        <bsvc:Country_Reference>                          
		                        <bsvc:ID bsvc:type="ISO_3166-1_Alpha-3_Code">
		                            <xsl:value-of select="$countryId"/>
		                        </bsvc:ID>
		                    </bsvc:Country_Reference> 
		                </bsvc:Government_ID_Data>	              
		            </bsvc:Government_ID>
	            </xsl:if>
	        </bsvc:Government_Identification_Data>
	    </bsvc:Edit_Government_IDs_Sub_Process>
	</xsl:template>
</xsl:stylesheet>