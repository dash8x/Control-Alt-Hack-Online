/**
 * Superclass for all game related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

public class PlayerException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	

	/**
	 * Empty Constructor
	 */
	public PlayerException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public PlayerException(String message){
		super(message);
	}
}
