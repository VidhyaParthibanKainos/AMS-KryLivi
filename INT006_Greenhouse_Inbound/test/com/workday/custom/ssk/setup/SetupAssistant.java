package com.workday.custom.ssk.setup;

import java.io.File;

import com.workday.custom.ssk.common.ImportProcessor;

public class SetupAssistant {
	
	private static final boolean isDebugModeOn = false;
	
	public static void main(String[] args) {
		try {
			File rootDirectory = new File(".");
			ImportProcessor ip = new ImportProcessor(isDebugModeOn, rootDirectory);
			ip.executeRenamingTasks(rootDirectory);

			if (isDebugModeOn) {
				System.out.println("The process has completed.  Please review the output in the Console view.");
			} else {
				System.out.println("The name refactoring steps have been completed.  Please refresh your Workday Studio Assembly Project in the Project Explorer.  If the Assembly Editor is currently open, you will need to close and reopen it before it will recognize the changes made.");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}
}
