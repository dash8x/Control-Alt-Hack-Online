/**
 * Exceptions thrown in Server
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class ServerException extends NetworkException {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7251097952440474625L;

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
