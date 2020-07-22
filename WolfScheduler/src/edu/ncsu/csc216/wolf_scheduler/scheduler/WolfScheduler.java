/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;


/**
 * This class named WolfScheduler is to read in and store 
 * as a list all of the Course records stored in a file. 
 * Additionally, WolfScheduler creates a schedule for the user 
 * and provides functionality for naming the schedule, adding a Course to the schedule, 
 * removing a Course from the schedule, resetting the schedule.
 * @author Zhongxiao Mei
 */
public class WolfScheduler {
    
	/** courseCatalog ArrayList of Courses */
	private ArrayList<Course> courseCatalog;
	/** schedule ArrayList of Courses */
	private ArrayList<Activity> schedule;
	/** the title of schedule */
	private String title;
	
	/**
	 * Initializes schedule to an empty ArrayList and sets title to My Schedule. 
	 * Initializes the catalog ArrayList and populates the Courses in the catalog 
	 * from the information in the courseRecordsFileName. 
	 * Throws an IllegalArgumentException if the file cannot be found
	 * @param courseRecordsFileName the fileName of the input file
	 */
	public WolfScheduler(String courseRecordsFileName) {
		try {
		    courseCatalog = CourseRecordIO.readCourseRecords(courseRecordsFileName);
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		this.schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
	}
    
	/**
	 * Uses the name and section to find a Course in the catalog.
	 * Returns null if the Course cannot be found.
	 * @param name the name
	 * @param section the section number
	 * @return the course from catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		if (courseCatalog.size() == 0) {
			return null;
		}
		else {
			for (int i = 0; i < courseCatalog.size(); i++) {
				Course courseInfo = courseCatalog.get(i);		
				if (courseInfo.getName().equals(name) && courseInfo.getSection().equals(section)) {
					return courseInfo;
				}
			}
		}
		return null;
	}
	
	/**
	 * add the Course represented by the name and section to the schedule
	 * @param name the name
	 * @param section the section number
	 * @return return true if can be added, otherwise return false
	 */
	public boolean addCourse(String name, String section) {
		Course s = getCourseFromCatalog(name, section);
		
		if (s != null) {
			for (int i = 0; i < schedule.size(); i++) {
				
				if (schedule.get(i).isDuplicate(s)) {
					throw new IllegalArgumentException("You are already enrolled in " + name);
				}
				Activity aa = schedule.get(i);
				String activities = aa.toString();
				String[] activityList = activities.split(",");
				if (activityList[0].equals(name)) {
					throw new IllegalArgumentException("You are already enrolled in " + name);
				}
				try {
					schedule.get(i).checkConflict(s);
				}
				catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
			schedule.add(s);
			return true;
			
		}
		return false;
		
	}
	
	/**
	 * add the Event represented by eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventWeeklyRepeat, eventDetails.
	 * @param eventTitle the title of event
	 * @param eventMeetingDays the meeting days of event
	 * @param eventStartTime the start time of event
	 * @param eventEndTime the end time of event
	 * @param eventWeeklyRepeat the weekly repeat number of event
	 * @param eventDetails the details of event
	 */
	public void addEvent(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, int eventWeeklyRepeat, String eventDetails) {
		Event events = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventWeeklyRepeat, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			Activity s = schedule.get(i);
			if (s.isDuplicate(events)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				s.checkConflict(events);
			}
			catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(events);
	}
	
	/**
	 * remove the Course represented by the name and section to the schedule
	 * @param idx TODO
	 * @return Returns true if the Course is removed from the schedule and false otherwise.
	 */
	public boolean removeActivity(int idx) {
		try {
			schedule.remove(idx);
			return true;
		}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * Resets the schedule to an empty ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}
	
	/**
	 * Returns a String[][] that contains a catalog item on each row. 
	 * The first column is the Course name, the second is the section, 
	 * and the third is the title.
	 * @return a String[][] that contains a catalog item on each row
	 */
	public String[][] getCourseCatalog() {
		String[][] catalog = new String[courseCatalog.size()][4];
		Course c;
		for (int i = 0; i < courseCatalog.size(); i++) {
			c = courseCatalog.get(i);
			catalog[i][0] = c.getName();
			catalog[i][1] = c.getSection();
			catalog[i][2] = c.getTitle();	
			catalog[i][3] = c.getMeetingString();				
		}
		return catalog;
	}

	/**
	 * Returns a String[][] that contains a schedule item on each row. 
	 * The first column is the Course name, the second is the section, 
	 * and the third is the title.
	 * @return Returns a String[][] that contains a schedule item on each row
	 */
	public String[][] getScheduledActivities() {
		Activity c;
		if (schedule.size() != 0) {
			String[][] scheduled = new String[schedule.size()][4];
			for (int i = 0; i < schedule.size(); i++) {
				c = schedule.get(i);
				String[] str = c.getShortDisplayArray();
				scheduled[i][0] = str[0];
				scheduled[i][1] = str[1];
				scheduled[i][2] = str[2];
				scheduled[i][3] = str[3];
				
			}
			return scheduled;
		}
		else {
			return new String[0][0];
		}
	}

	/**
	 * Returns a String[][] that contains a schedule item on each row. 
	 * The first column is the Course name, the second is the section, 
	 * the third is the title, the fourth is the credits, the fifth is the instructorId, 
	 * and the sixth is the meeting time String.
	 * @return Returns a String[][] that contains a schedule item on each row.
	 */
	public String[][] getFullScheduledActivities() {
		Activity c;
		if (schedule.size() != 0) {
			String[][] fullScheduled = new String[schedule.size()][7];
			for (int i = 0; i < schedule.size(); i++) {
				c = schedule.get(i);
				String[] str = c.getLongDisplayArray();
				fullScheduled[i][0] = str[0];
				fullScheduled[i][1] = str[1];
				fullScheduled[i][2] = str[2];
				fullScheduled[i][3] = str[3];
				fullScheduled[i][4] = str[4];
				fullScheduled[i][5] = str[5];
				fullScheduled[i][6] = str[6];
			}
			return fullScheduled;
		}
		else {
			return new String[0][0];
		}
		
	}

	/**
	 * Returns the schedule title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * set the schedule title 
	 * @param title the title
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Writes the schedule to the given file
	 * @param fileName the output file name
	 */
	public void exportSchedule(String fileName)  {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
}
