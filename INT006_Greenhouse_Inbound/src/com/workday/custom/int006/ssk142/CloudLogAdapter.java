package com.workday.custom.int006.ssk142;

/**
 * An interface by which an object can provide message information for cloud-log
 * @author Doug Lee
 */

public interface CloudLogAdapter {

	/**
	 * Returns the log level for the message.
	 * @return
	 */
	
	public String getLevel();
	
	/**
	 * The message text.  This will always be non-null and should be non-empty
	 * @return
	 */
	
	public String getSummary();
	
	/**
	 * @return The details text for the message.  An empty string indicates that no details text is available
	 */
	
	public String getDetail();
	
	/**
	 * 
	 * @return The reference ID for this message.  An empty string indicates that no reference ID is available
	 */
	
	public String getReferenceId();
	
	public String getLocalIn();
	public String getRecordNumber();
	public String getSupportData();
	public String getErrorCode();
}
