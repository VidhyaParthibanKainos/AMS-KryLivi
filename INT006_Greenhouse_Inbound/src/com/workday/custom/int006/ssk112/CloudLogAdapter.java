package com.workday.custom.int006.ssk112;

/**
 * An interface by which an object can provide message information for cloud-log
 * @author Doug Lee
 */

public interface CloudLogAdapter {

	/**
	 * Returns the log level for the message.
	 * @return
	 */
	
	default public CloudLogLevel getLevel() {
		return CloudLogLevel.info;
	}
	
	/**
	 * The message text.  This will always be non-null and should be non-empty
	 * @return
	 */
	
	public String getMessage();
	
	/**
	 * @return The details text for the message.  An empty string indicates that no details text is available
	 */
	
	default public String getDetails() {
		return "";
	}
	
	/**
	 * 
	 * @return The reference ID for this message.  An empty string indicates that no reference ID is available
	 */
	
	default public String getReferenceId() {
		return "";
	}
	
	/**
	 * 
	 * @return The related local-in for this message.  An empty string indicates that no local-in is identified
	 */
	
	default public String getLocalIn() {
		return "";
	}
	
	/**
	 * 
	 * @return The relative record to which this message applies.  An empty string indicates that no record number is identified
	 */
	
	default public String getRecordNumber() {
		return "";
	}

	/**
	 * 
	 * @return The associated error code for this message.  An empty string indicates that no error code is available, or this is not an error message
	 */
	
	default public String getErrorCode() {
		return "";
	}

	/**
	 * 
	 * @return The support data for this message.  An empty string indicates that no additional information is available
	 */
	
	default public String getSupportData() {
		return "";
	}
}
