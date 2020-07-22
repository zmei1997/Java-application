/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This class named Course is to schedule meeting days
 * @author Zhongxiao Mei
 */
public class Course extends Activity {
	
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** the length of section String */
	private static final int SECTION_LENGTH = 3;
	/** the maximum length of the name String */
	private static final int MAX_NAME_LENGTH = 6;
	/** the minimum length of the name String */
	private static final int MIN_NAME_LENGTH = 4;
	/** the maximum number of credits */
	private static final int MAX_CREDITS = 5;
	/** the minimum number of credits */
	private static final int MIN_CREDITS = 1;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		setName(name);
	    setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    setMeetingDays(meetingDays);
	    setCourseTime(startTime, endTime);
	}
	    
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
     * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or 
	 * greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
	        throw new IllegalArgumentException();
	    }
	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException();
	    }
		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * @param section the section to set
	 */
	public void setSection(String section) {

		if (section == null) {
	        throw new IllegalArgumentException();
	    }
		if (section.length() != SECTION_LENGTH) {
	        throw new IllegalArgumentException();
	    }
		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
	        throw new IllegalArgumentException();
	    }
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructorId
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructorId
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null) {
	        throw new IllegalArgumentException();
	    }
		if (instructorId.length() == 0) {
	        throw new IllegalArgumentException();
	    }
		this.instructorId = instructorId;
	}

	
	/** 
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/** Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
		    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * returns an array of length 4 containing the Course name, section, title, and meeting days string
	 * @return returns an array of length 4 containing the Course name, section, title, and meeting days string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];
		shortDisplayArray[0] = name;
		shortDisplayArray[1] = section;
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();
		return shortDisplayArray;
	}

	/**
	 * returns an array of length 7 containing the Course name, section, title, credits, instructorId, meeting days string, empty string
	 * @return returns an array of length 7 containing the Course name, section, title, credits, instructorId, meeting days string, empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		longDisplayArray[0] = name;
		longDisplayArray[1] = section;
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = String.valueOf(credits);
		longDisplayArray[4] = instructorId;
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = "";
		return longDisplayArray;
	}
	
	/**
	 * Returns true if the given Activity is a duplicate of the Course by name.
	 * @param activity activity list
	 * @return returns true if the given Activity is a duplicate of the Course by name.
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
    			Course c = (Course) activity;
    			if (c.getName().equals(getName())) {
    				return true;
    			}
		}
		return false;
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
		if (!meetingDays.matches("[MTWHFA]+")) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException();
		}
		if (!meetingDays.contains("M") && !meetingDays.contains("T") && !meetingDays.contains("W") && !meetingDays.contains("H") && !meetingDays.contains("F") && !meetingDays.contains("A")) {
				throw new IllegalArgumentException();
		}
		super.setMeetingDays(meetingDays);
	}
	
}
