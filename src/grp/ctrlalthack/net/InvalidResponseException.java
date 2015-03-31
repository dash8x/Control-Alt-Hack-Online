/**
 * Custom exception for Invalid responses
 * Thrown when unexpected Response received from the server
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

public class InvalidResponseException extends ResponseException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 88863994134102933L;

	/**
	 * Empty Constructor
	 */
	public InvalidResponseException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public InvalidResponseException(String message){
		super(message);
	}
}

