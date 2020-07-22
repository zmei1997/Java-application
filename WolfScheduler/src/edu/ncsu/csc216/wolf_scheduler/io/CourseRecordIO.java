package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * @author Zhongxiao Mei
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException("File not found");
		}
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				if (course == null) {
					throw new IllegalArgumentException();
				}
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				//skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * The private helper method to process each Courses 
	 * @param nextLine a line of course information
	 * @return a list of Courses
	 */
	private static Course readCourse(String nextLine) {
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		Course c;
		try {
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int credits = s.nextInt();
			String instructorId = s.next();
			String meetingDays = s.next();
			if (meetingDays.equals("A")) {
				if (s.hasNextInt()) {
					s.close();
					throw new IllegalArgumentException();
				}
				c = new Course(name, title, section, credits, instructorId, meetingDays);
			}
			else {
				int startTime = s.nextInt();
				int endTime = s.nextInt();
				c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			}
			s.close();
		}
		catch (NoSuchElementException e) {
			s.close();
			return null;
		}
		return c;
	}

}
