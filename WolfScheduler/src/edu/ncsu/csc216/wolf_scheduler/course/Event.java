/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * this class named Event is to get how many weeks between events, 
 * and the event details
 * @author Zhongxiao Mei
 */
public class Event extends Activity {

	/** describes how many weeks between events */
	private int weeklyRepeat;
	/** Event's details */
	private String eventDetails;
	/**
	 * Sets the fields to the given values
	 * @param title course title
	 * @param meetingDays meeting days
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param weeklyRepeat describes how many weeks between events
	 * @param eventDetails Event's details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}
	/**
	 * Returns the Course's weeklyRepeat
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}
	/**
	 * Sets the Course's weeklyRepeat
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}
	/**
	 * Returns the Course's eventDetails
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	/**
	 * Sets the Course's eventDetails
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}
	/**
	 * Returns an array of Strings containing empty string, empty string, title, and meeting string
	 * @return an array of Strings containing empty string, empty string, title, and meeting string
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];
		shortDisplayArray[0] = "";
		shortDisplayArray[1] = "";
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();
		return shortDisplayArray;
	}
	/**
	 * Returns an array of Strings containing empty string, empty string, title, empty string, 
	 * empty string, meeting string, and event details.
	 * @return Returns an array of Strings containing empty string, empty string, title, empty string, 
	 * empty string, meeting string, and event details.
	 */
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		longDisplayArray[0] = "";
		longDisplayArray[1] = "";
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = "";
		longDisplayArray[4] = "";
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = eventDetails;
		return longDisplayArray;
	}
	/** 
	 * overrides Activity’s Meeting day String
	 * @return Activity’s Meeting day String
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#getMeetingString()
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() +  " (every " + weeklyRepeat + " weeks)";
	}
	/** 
	 * Returns a comma separated list of Event fields
	 * @return a comma separated list of Event fields
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + weeklyRepeat + "," + eventDetails;
	}
	
	/**
	 * Sets the meetingDays
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#setMeetingDays(java.lang.String)
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		if (!meetingDays.matches("[UMTWHFS]+")) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException();
		}
		if (!meetingDays.contains("U") && !meetingDays.contains("M") && !meetingDays.contains("T") && !meetingDays.contains("W") && !meetingDays.contains("H") && !meetingDays.contains("F") && !meetingDays.contains("S") && !meetingDays.contains("A")) {
				throw new IllegalArgumentException();
		}
		super.setMeetingDays(meetingDays);
	}
	
	/**
	 * Returns true if the given Activity is a duplicate of the Event by title
	 * @param activity activity list
	 * @return Returns true if the given Activity is a duplicate of the Event by title
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
    			Event e = (Event) activity;
    			if (e.getTitle().equals(getTitle())) {
    				return true;
    			}
    			return false;
		}
		return false;
    }
}
