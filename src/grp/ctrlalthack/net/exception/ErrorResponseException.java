/**
 * Custom exception for response errors
 * Thrown when an ERROR Response received from the server
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class ErrorResponseException extends ResponseException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4935157319894776411L;

	/**
	 * Empty Constructor
	 */
	public ErrorResponseException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public ErrorResponseException(String message){
		super(message);
	}
}
