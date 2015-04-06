/**
 * Standard object used to send responses from server to client
 * Wraps the response keyword and parameters into a single object
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Response extends Protocol {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -896165912253055513L;
	
	//allowed responses
	public static final Set<String> RESPONSES = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
			new String[]{"DATA UPDATED", "TERMINATE", "ERROR", "SUCCESS", "FAIL", "PLAYERS"}
			)));
	
	/**
	 * Constructor
	 */
	public Response(String command, HashMap<String,Object> params) {
		super(command, params);		
	}	
		
	/**
	 * Checks if a given set of parameters are valid for a command string
	 * @param response string
	 * @param params hashmap
	 */
	@Override
	public boolean isValidParams(String response, HashMap<String,Object> params) {
		switch(response) {				
			case "ERROR":
				return params != null && params.size() == 1 && (params.get("message") instanceof String);
			case "SUCCESS":
				return params != null && params.size() == 1 && (params.get("operation") instanceof String);
			case "FAIL":
				return params != null && params.size() == 2 && (params.get("operation") instanceof String) && (params.get("message") instanceof String);			
			case "TERMINATE":
				return (params == null || params.size() == 0);
			case "PLAYERS":
				return params != null && params.size() == 1 && (params.get("players") instanceof ArrayList);
			case "DATA UPDATED":
				return params != null && params.size() == 1 && (params.get("updated") instanceof String);
		}
		return false;
	}

	/**
	 * Checks if a given keyword is a valid command
	 * @param keyword string
	 */
	@Override
	public boolean isValidKeyword(String keyword) {
		return RESPONSES.contains(keyword);
	}
	
	/**
	 * toString method
	 */
	public String toString() {
		return "RESPONSE " + super.toString();		
	}
	
}
