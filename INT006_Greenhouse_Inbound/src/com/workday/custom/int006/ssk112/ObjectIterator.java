package com.workday.custom.int006.ssk112;


import java.util.Iterator;

/**
 * This class provides a means to obtain a non-generic Iterator from a generic Iterator or object which
 * implements the generic Iterable interface.  This is useful as MVEL 1.3 appears to be unable to cope
 * with accessing methods returning generic objects or methods on anonymous classes.
 * 
 * @author Doug Lee
 *
 */

@SuppressWarnings({ "rawtypes" })
public class ObjectIterator implements Iterator {
	Iterator<?>	parent;
	
	public ObjectIterator(Iterable o) {
		parent = o.iterator();
	}

	public ObjectIterator(Iterator<?> parent) {
		this.parent = parent;
	}

	@Override
	public boolean hasNext() {
		return parent.hasNext();
	}

	@Override
	public Object next() {
		return parent.next();
	}

	@Override
	public void remove() {
		parent.remove();
	}
	
	
}
