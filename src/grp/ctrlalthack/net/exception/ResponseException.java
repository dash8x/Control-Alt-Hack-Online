/**
 * Superclass for all Response related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class ResponseException extends NetworkException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4565739940253206909L;

	/**
	 * Empty Constructor
	 */
	public ResponseException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public ResponseException(String message){
		super(message);
	}
}
