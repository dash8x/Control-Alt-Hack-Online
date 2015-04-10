/**
 * Custom exception for FAIL responses
 * Thrown when FAIL Response received from the server 
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class FailResponseException extends ResponseException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -139133561375237364L;

	/**
	 * Empty Constructor
	 */
	public FailResponseException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public FailResponseException(String message){
		super(message);
	}
}

