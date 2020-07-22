package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This class named Activity is to get Activity information
 * @author Zhongxiao Mei
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** the military time */
	private static final int TIME = 2359;
	/** the minimum military time */
	private static final int MINI_TIME = 0000;
	/** the value for an hour */
	private static final int HOUR = 59;
	

	/**
	 * construct Activity and the parameters is in the order of title, meetingDays, startTime, and endTime
	 * @param title course title
	 * @param meetingDays meeting days
	 * @param startTime the start time
	 * @param endTime the end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null) {
	        throw new IllegalArgumentException();
	    }
		if (title.length() == 0) {
	        throw new IllegalArgumentException();
	    }
		this.title = title;
	}

	/**
	 * Returns the Course's meetingDays
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the meeting information in standard time format
	 * @return the meetInfo
	 */
	public String getMeetingString() {
		if (!meetingDays.equals("A")) {
			String str;
			if (startTime >= 1200) {
				if (startTime / 100 == 12) {
					str = startTime / 100 + ":";
				} else {
					str = startTime / 100 - 12 + ":";
				}
				if (startTime % 100 < 10) {
					str = str + "0" + startTime % 100 + "PM-";
				} else {
					str = str + startTime % 100 + "PM-";
				}
			} else {
				str = startTime / 100 + ":";
				if (startTime % 100 < 10) {
					str = str + "0" + startTime % 100 + "AM-";
				} else {
					str = str + startTime % 100 + "AM-";
				}
			}
			if (endTime >= 1200) {
				if (endTime / 100 == 12) {
					str = str + endTime / 100 + ":";
				} else {
					str = str + String.valueOf(endTime / 100 - 12) + ":";
				}
				if (endTime % 100 < 10) {
					str = str + "0" + endTime % 100 + "PM";
				} else {
					str = str + endTime % 100 + "PM";
				}
			} else {
				str += String.valueOf(endTime / 100) + ":";
				if (endTime % 100 < 10) {
					str = str + "0" + endTime % 100 + "AM";
				} else {
					str = str + endTime % 100 + "AM";
				}
			}
			return meetingDays + " " + str;
		}
		return "Arranged";
	}

	/**
	 * Sets the Course's meetingDays
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}
	
	/**
	 * Sets the Activity's time
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < MINI_TIME || startTime > TIME || startTime % 100 > HOUR) {
			throw new IllegalArgumentException();
		}
		if (endTime < MINI_TIME || endTime > TIME || endTime % 100 > HOUR) { 
			throw new IllegalArgumentException();
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}
	/**
	 * Returns the Course's startTime
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's endTime
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course's time
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 */
	public void setCourseTime(int startTime, int endTime) {
		if (startTime < MINI_TIME || startTime > TIME || startTime % 100 > HOUR) {
			throw new IllegalArgumentException();
		}
		if (endTime < MINI_TIME || endTime > TIME || endTime % 100 > HOUR) { 
			throw new IllegalArgumentException();
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** 
	 * Generates a hashCode for Course using all fields
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/** 
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/** 
	 * Abstract method that returns an array of Strings 
	 * @return an array of Strings
	 */
	public abstract String[] getShortDisplayArray();
	/** 
	 * Abstract method that returns an array of Strings 
	 * @return an array of Strings
	 */
	public abstract String[] getLongDisplayArray();
	/** Abstract method that returns true if the given Activity is a duplicate of this object.
	 * @param activity activity list
	 * @return returns true if the given Activity is a duplicate of this object
	 */
	public abstract boolean isDuplicate(Activity activity);

	/** 
	 * check Conflict if Conflict on a single day, 
	 * or Conflict where the endTime for this is the same as the startTime for possiblyConflictingActivity.
	 * @param possibleConflictingActivity the possible conflicting activity
	 * @throws ConflictException
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if ((!(this.meetingDays.contains("A") && possibleConflictingActivity.meetingDays.contains("A")))
				&& ((((this.meetingDays.contains("M") && possibleConflictingActivity.meetingDays.contains("M"))
						|| (this.meetingDays.contains("T") && possibleConflictingActivity.meetingDays.contains("T"))
						|| (this.meetingDays.contains("W") && possibleConflictingActivity.meetingDays.contains("W"))
						|| (this.meetingDays.contains("H") && possibleConflictingActivity.meetingDays.contains("H"))
						|| (this.meetingDays.contains("F") && possibleConflictingActivity.meetingDays.contains("F"))
						|| (this.meetingDays.contains("S") && possibleConflictingActivity.meetingDays.contains("S"))
						|| (this.meetingDays.contains("U") && possibleConflictingActivity.meetingDays.contains("U")))
						&& ((this.getStartTime() <= possibleConflictingActivity.getStartTime()
								&& this.getEndTime() >= possibleConflictingActivity.getEndTime())
								|| (this.getStartTime() <= possibleConflictingActivity.getStartTime()
										&& (this.getEndTime() >= possibleConflictingActivity.getStartTime()
												&& this.getEndTime() <= possibleConflictingActivity.getEndTime()))
								|| ((this.getStartTime() >= possibleConflictingActivity.getStartTime()
										&& this.getStartTime() <= possibleConflictingActivity.getEndTime())
										&& (this.getEndTime() >= possibleConflictingActivity.getStartTime()
												&& this.getEndTime() <= possibleConflictingActivity.getEndTime()))
								|| ((this.getStartTime() >= possibleConflictingActivity.getStartTime()
										&& this.getStartTime() <= possibleConflictingActivity.getEndTime())
										&& this.getEndTime() >= possibleConflictingActivity.getEndTime()))))) {
			throw new ConflictException();
		}
	}	
	
}