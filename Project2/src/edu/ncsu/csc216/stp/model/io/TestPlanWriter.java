package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Writes the open test plans to the given file.
 * 
 * @author Maddie Moore
 * @author Annabella Jackmore
 *
 */
public class TestPlanWriter {
	
	/**
	 * Receives a File with the file name to write to and an ISortedList of TestPlans to write to file. If there 
	 * are any errors or exceptions, an IllegalArgumentException is thrown with the message “Unable to save file.”
	 * @param file File to write TestPlans to
	 * @param testPlans TestPlans to write to file
	 * @throws IllegalArgumentException with message "Unable to save file" if there are any exceptions 
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> testPlans) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(file);
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("Unable to save file");
		}  
		try {
			for(int i = 0; i < testPlans.size(); i++) {
				fileWriter.println("! " + testPlans.get(i).getTestPlanName().toString());
				for(int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
					if(testPlans.get(i).getTestCase(j) == null) {
						 continue;
					} else {
						fileWriter.print(testPlans.get(i).getTestCase(j).toString());
					} 
				} 
			}
	    	fileWriter.close();
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		} 
	}
}
