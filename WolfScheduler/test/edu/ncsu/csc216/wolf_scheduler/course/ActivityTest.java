/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * testing Activity conflict
 * @author Zhongxiao Mei
 */
public class ActivityTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
		//test not conflict
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		Activity a3 = new Course("CSC230", "C and Software Tools", "001", 3, "dbsturgi", "MW", 1145, 1300);
		Activity a4 = new Course("CSC230", "C and Software Tools", "001", 3, "dbsturgi", "TF", 1145, 1300);
		try {
 	        a1.checkConflict(a2);
 	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
 	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
 	    } catch (ConflictException e) {
 	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
 	    }
	    
	    try {
	    		a3.checkConflict(a4);
	    		assertEquals("Incorrect meeting string for this Activity.", "MW 11:45AM-1:00PM", a3.getMeetingString());
	    		assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TF 11:45AM-1:00PM", a4.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    //test conflict
	    a1.setMeetingDays("TH");
 	    a1.setActivityTime(1445, 1530);
 	    try {
 	        a1.checkConflict(a2);
 	        fail(); //ConflictException should have been thrown, but was not.
 	    } catch (ConflictException e) {
 	        //Check that the internal state didn't change during method call.
 	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
 	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
 	    }
 	    
 	    a4.setMeetingDays("MW");
 	    a4.setActivityTime(1045, 1145);
	    try {
	        a4.checkConflict(a3);
	        fail(); 
	    } catch (ConflictException e) {
	        assertEquals("MW 10:45AM-11:45AM", a4.getMeetingString());
	        assertEquals("MW 11:45AM-1:00PM", a3.getMeetingString());
	    }    
	}

}
