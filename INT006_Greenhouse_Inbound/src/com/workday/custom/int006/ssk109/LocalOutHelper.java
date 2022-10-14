package com.workday.custom.int006.ssk109;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.capeclear.mediation.MediationContext;

public class LocalOutHelper {
	
	/**
	 * Obtain the current set of names of local-in loop detection flags
	 * @param ctx The MediationContext
	 * @return A Set containing the names of the in-scope local-in loop detection flags
	 */
	
	public static Set<String> SnapshotLoopDetection(MediationContext ctx) {
		Set<String> local_ins = new HashSet<String>();
		
		ProcessLocalInNames(ctx, new LocalInProcessor() {
			@Override
			public void process(String name) {
				local_ins.add(name);
			}
		});
		
		return local_ins;

	}
	
	/**
	 * Remove any loop detection flags which are not present in the Set presented to the function.  This can be used to fix up
	 * the loop detection status after an abort has been issued in a subassembly.
	 * 
	 * @param flags
	 */
	
	public static void ClearLoopDetectionFlags(MediationContext ctx, Set<String> flags) {
		if ( flags == null ) {
			throw new IllegalArgumentException("Second parameter to clearLoopDetectionFlags must be non-null");
		}

		//
		// We can't guarantee that the iterator over the property names is invariant with modification
		// of the underlying collection so we'll build a list of the properties to remove then
		// remove them once we've determined all that need to go
		//
		
		List<String> removal = new ArrayList<String>();
		
		ProcessLocalInNames(ctx, new LocalInProcessor() {
			@Override
			public void process(String name) {
				if ( !flags.contains(name) ) {
					removal.add(name);
				}
			}
		});
		
		//
		// Now remove the properties
		//
		for(String n : removal ) {
			ctx.removeProperty(n);
		}
	}

	interface LocalInProcessor {
		public void process(String name);
	}
	
	private static void ProcessLocalInNames(MediationContext ctx, LocalInProcessor processor) {
		@SuppressWarnings("unchecked")
		Iterator<String> names = ctx.getPropertyNames();
		String name;
		
		while (names.hasNext() ) {
			name = names.next();
			if ( name.indexOf(".local-in.") > 0 ) {
				processor.process(name);
			}
		}
	}

}
