/**
 * Custom exception for no response
 * Thrown when Response cannot be received from server due to some reason
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class NoResponseException extends ResponseException {
		
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3973641999944719849L;

	/**
	 * Empty Constructor
	 */
	public NoResponseException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public NoResponseException(String message){
		super(message);
	}
}
