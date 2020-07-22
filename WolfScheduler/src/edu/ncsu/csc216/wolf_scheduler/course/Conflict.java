/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The method throws the checked ConflictException 
 * if the possibly conflicting activity (parameter) 
 * conflicts with the current activity (this)
 * @author Zhongxiao Mei
 */
public interface Conflict {

	/** 
	 * checking conflict if the possibly conflicting activity conflicts with the current activity.
	 * @param possibleConflictingActivity possible Conflicting Activity
	 * @throws ConflictException
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
