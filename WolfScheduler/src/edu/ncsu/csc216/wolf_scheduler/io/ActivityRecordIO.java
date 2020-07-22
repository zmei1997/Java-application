package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes a set of ActivityRecords to a file.
 * @author Zhongxiao Mei
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Courses
	 * @param fileName name of the output file
	 * @param courses courses list
	 * @throws IOException if unable to write to the file
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (Activity c : courses) {
			fileWriter.println(c.toString());
		}
		fileWriter.close();
	}

}
