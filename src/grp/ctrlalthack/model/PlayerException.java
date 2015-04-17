/**
 * Exception thrown by player
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

public class PlayerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 49890419274838519L;

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
