package com.workday.custom.int006.ssk112;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.workday.mediation.impl.mediators.etv.ETVInfo;
import com.workday.mediation.impl.mediators.etv.ETVInfoCollection;

/**
 * An iterator over ETV messages
 * @author doug.lee
 *
 */
public class CloudLogETVIterator implements Iterator<CloudLogAdapter> {
	
	/**
	 * The minimum severity level that will be output.  By default all information will be output
	 */
	
	ETVInfo.MessageSeverity	minSeverity;
	Iterator<ETVInfo>		etvIterator;
	ETVInfo					next;

	private CloudLogETVIterator(ETVInfoCollection etv_messages, ETVInfo.MessageSeverity min_severity) {
		minSeverity = min_severity;
		//
		// Get an iterator over all messages
		//
		etvIterator = etv_messages.iterator(true);
		//
		// Find the first ETVInfo message which meets our minimum severity criteria
		//
		moveToNext();
	}
	
	public static Iterator<CloudLogAdapter> getIterator(ETVInfoCollection etv_messages, String min_level) throws IllegalArgumentException {
		return new CloudLogETVIterator(etv_messages, ETVInfo.MessageSeverity.valueOf( min_level.trim().toUpperCase() ) );
	}
	
	public static Iterator<CloudLogAdapter> getIterator(ETVInfoCollection etv_messages) throws IllegalArgumentException {
		return new CloudLogETVIterator(etv_messages, ETVInfo.MessageSeverity.INFO );
	}
	
	private void moveToNext() {
		do {
			next = etvIterator.hasNext() ? etvIterator.next() : null;

		} while ( ( next != null ) && (next.getSeverity().compareTo( minSeverity) > 0) );
	}
	
	//
	// Interface Iterator<CloudLogAdaptor>
	//

	@Override
	public boolean hasNext() {
		return next != null;
	}

	/**
	 * Obtain the a CloudLogAdapter instance wrapping the next message
	 * 
	 * @throws NoSuchElementException if no messages are available
	 */
	
	@Override
	public CloudLogAdapter next() {
		if ( next == null ) {
			throw new NoSuchElementException("No more ETV messages are available");
		}
		
		CloudLogAdapter next_value = new CloudLogAdapter() {
			ETVInfo info = next;
			
			public CloudLogLevel getLevel() {
				return LevelMap.get(info.getSeverity() );
			}
			
			public String getMessage() {
				return info.getSummary();
			}
			
			public String getReferenceId() {
				String ref_id = info.getTargetID();
				return ( ref_id != null ) ? ref_id : "";
			}
		};
		
		moveToNext();
		
		return next_value;
	}
	
	//
	// Provide a map from ETV severity to cloud-log severity
	//
	static Map<ETVInfo.MessageSeverity, CloudLogLevel>	LevelMap;
	
	static {
		LevelMap = new HashMap<ETVInfo.MessageSeverity, CloudLogLevel>();
		LevelMap.put( ETVInfo.MessageSeverity.INFO, CloudLogLevel.info);
		LevelMap.put( ETVInfo.MessageSeverity.WARNING, CloudLogLevel.warn);
		LevelMap.put( ETVInfo.MessageSeverity.ERROR, CloudLogLevel.error);
		LevelMap.put( ETVInfo.MessageSeverity.CRITICAL, CloudLogLevel.fatal);
	}
}
