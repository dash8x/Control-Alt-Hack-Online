/**
 * Superclass for all game related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

public class GameException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6889632719529667971L;

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
