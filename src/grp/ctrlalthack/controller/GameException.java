/**
 * Superclass for all game related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

public class GameException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	

	/**
	 * Empty Constructor
	 */
	public GameException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public GameException(String message){
		super(message);
	}
}
