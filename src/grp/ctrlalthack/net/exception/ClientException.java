/**
 * Superclass for all Response related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class ClientException extends NetworkException {	

	/**
	 * Empty Constructor
	 */
	public ClientException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public ClientException(String message){
		super(message);
	}
}
