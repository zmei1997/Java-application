/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The ConflictException is a checked exception.
 * The default message is “Schedule conflict.”
 * @author Zhongxiao Mei
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates an exception with the given message
	 * @param message the message
	 */
	ConflictException(String message){
		super(message);
	}
	
	/**
	 * Creates an exception with the default message
	 */
	ConflictException(){
		super("Schedule conflict.");
	}

}
