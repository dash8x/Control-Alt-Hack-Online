/**
 * Superclass for all Network related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class NetworkException extends RuntimeException {
	
	/**
	 * Empty Constructor
	 */
	public NetworkException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public NetworkException(String message){
		super(message);
	}
}
