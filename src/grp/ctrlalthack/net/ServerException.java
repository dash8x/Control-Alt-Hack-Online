/**
 * Superclass for all Response related Exceptions
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

public class ServerException extends NetworkException {	

	/**
	 * Empty Constructor
	 */
	public ServerException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message error message
	 */
	public ServerException(String message){
		super(message);
	}
}
