/**
 * Exception thrown when game ends
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

public class EndGameException extends GameException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8216925974690768258L;

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
