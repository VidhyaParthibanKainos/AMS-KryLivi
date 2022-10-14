package com.workday.custom.int006.ssk142;

public class CloudLogMessage implements CloudLogAdapter {

	private String summary = "";
	private String detail = "";
	private String referenceId = "";
	private String level = "";
	private String localIn = "";
	private String recordNumber = "";
	private String supportData = "";
	private String errorCode = "";
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLocalIn() {
		return localIn;
	}
	public void setLocalIn(String localIn) {
		this.localIn = localIn;
	}
	public String getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getSupportData() {
		return supportData;
	}
	public void setSupportData(String supportData) {
		this.supportData = supportData;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}	
}
