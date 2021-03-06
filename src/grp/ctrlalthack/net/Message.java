/**
 * Object to send contextual messages
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import java.io.Serializable;

public class Message implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -3032314617933471069L;
	
	private String message; //message
	private String context; //context
	
	//context
	public static final String CONTEXT_CHARACTER_CHOOSE = "CHARACTER_CHOSEN";
	public static final String CONTEXT_ROLL = "ROLL";
	public static final String CONTEXT_ROUND = "ROUND";
	public static final String CONTEXT_PHASE = "PHASE";
	public static final String CONTEXT_END = "END";
	
	
	/**
	 * Constructor
	 */
	public Message(String message) {
		this(message, "");
	}
	
	/**
	 * Constructor
	 */
	public Message(String message, String context) {
		this.context = context;	
		this.message = message;
	}	

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	
}
