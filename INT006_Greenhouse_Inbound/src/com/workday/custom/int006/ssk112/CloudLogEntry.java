package com.workday.custom.int006.ssk112;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.workday.custom.int006.SSKUtils;

@XmlRootElement(name = "lm")
public class CloudLogEntry implements CloudLogAdapter {
	
	@XmlElement(name = "l") private CloudLogLevel level;	
	@XmlElement(name = "m") private String message;
	@XmlElement(name = "d") private String details;
	@XmlElement(name = "r") private String referenceId;
	@XmlElement(name = "n") private String recordNumber;
	@XmlElement(name = "t") private String localIn;
	@XmlElement(name = "c") private String errorCode;
	@XmlElement(name = "a") private String supportData;
	
	public CloudLogEntry() {
		super();
	}

	public CloudLogEntry(String message, String details, CloudLogLevel level, String referenceId, String recordNumber, String localIn, String errorCode, String supportData) {
		super();
		this.level = level;
		this.message = message;
		this.details = details;
		this.referenceId = referenceId;
		this.recordNumber = recordNumber;
		this.localIn = localIn;
		this.errorCode = errorCode;
		this.supportData = supportData;
	}
	
	public static CloudLogEntry getCloudLogEntry(String serializedString) {
		CloudLogEntry returnValue = null;
		try {
			StringReader reader = new StringReader(serializedString);
			returnValue = (CloudLogEntry)SSKUtils.UNMARSHALLER.unmarshal(reader);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return returnValue;
	}
	
	public static String createMessage(String message) {
		CloudLogEntry cle = new CloudLogEntry(message, null, CloudLogLevel.info, null, null, null, null, null);
		return cle.toString();
	}

	public static String createMessage(String level, String message) {
		CloudLogEntry cle = new CloudLogEntry(message, null, CloudLogLevel.parse(level), null, null, null, null, null);
		return cle.toString();
	}

	public static String createMessage(String level, String message, String details) {
		CloudLogEntry cle = new CloudLogEntry(message, details, CloudLogLevel.parse(level), null, null, null, null, null);
		return cle.toString();
	}

	public static String createMessage(String level, String message, String details, String referenceId) {
		CloudLogEntry cle = new CloudLogEntry(message, details, CloudLogLevel.parse(level), referenceId, null, null, null, null);
		return cle.toString();
	}

	public static String createMessage(String level, String message, String details, String referenceId, String recordNumber, String localIn, String errorCode, String supportData) {
		CloudLogEntry cle = new CloudLogEntry(message, details, CloudLogLevel.parse(level), referenceId, recordNumber, localIn, errorCode, supportData);
		return cle.toString();
	}

	@Override
	public String toString() {
		String returnValue = null;
		try {
			StringWriter writer = new StringWriter();
			SSKUtils.MARSHALLER.marshal(this, writer);
			returnValue = writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return returnValue;
	}

	@Override
	@XmlTransient
	public String getMessage() {
		return message;
	}

	@Override
	@XmlTransient
	public CloudLogLevel getLevel() {
		return level == null ? CloudLogAdapter.super.getLevel() : level;
	}

	@Override
	@XmlTransient
	public String getDetails() {
		return details;
	}

	@Override
	@XmlTransient
	public String getReferenceId() {
		return referenceId;
	}

	@Override
	@XmlTransient
	public String getLocalIn() {
		return localIn;
	}

	@Override
	@XmlTransient
	public String getRecordNumber() {
		return recordNumber;
	}

	@Override
	@XmlTransient
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	@XmlTransient
	public String getSupportData() {
		return supportData;
	}

	public void setLevel(CloudLogLevel level) {
		this.level = level;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public void setLocalIn(String localIn) {
		this.localIn = localIn;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setSupportData(String supportData) {
		this.supportData = supportData;
	}
}
