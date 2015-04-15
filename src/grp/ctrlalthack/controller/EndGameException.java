/**
 * Superclass for all game related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

public class EndGameException extends GameException {
	
	/**
	 * Serial Version UID
	 */
	

	/**
	 * Empty Constructor
	 */
	public EndGameException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public EndGameException(String message){
		super(message);
	}
}
