/**
 * Exception thrown in Client
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net.exception;

public class ClientException extends NetworkException {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702840570930976786L;

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
