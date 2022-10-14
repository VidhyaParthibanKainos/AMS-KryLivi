package com.workday.custom.int006.ssk142;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * An iterator over messages produced by an XSLT+ step
 * @author doug.lee
 *
 */
public class CloudLogMessageIterator implements Iterator<CloudLogAdapter> {
	
	/**
	 * The minimum severity level that will be output.  By default all information will be output
	 */
	
	Iterator<CloudLogMessage> messageIterator;
	CloudLogAdapter next;

	private CloudLogMessageIterator(ConcurrentLinkedQueue<CloudLogMessage> messages) {
		messageIterator = messages.iterator();
		moveToNext();
	}
	/**
	 * 
	 * @param messages Note that we are forced to specify the raw type here as the MVEL use of reflection is unable to find the method if
	 * we use a generic
	 * @param min_level The minimum level for which messages will be output.  Must be one of "debug", "info", "warn", "error", "critical"
	 * @return
	 * @throws IllegalArgumentException If an invalid minimum level is specified
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<CloudLogAdapter> getIterator(@SuppressWarnings("rawtypes") ConcurrentLinkedQueue messages) throws IllegalArgumentException {
		return new CloudLogMessageIterator((ConcurrentLinkedQueue<CloudLogMessage>)messages);
	}
	
	private void moveToNext() {
		do {
			next = messageIterator.hasNext() ? messageIterator.next() : null;
		} while (next != null);
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
		
		CloudLogAdapter nextValue = next;
		
		moveToNext();
		
		return nextValue;
	}
}
