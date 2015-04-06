/**
 * Standard object used to send requests from client to server
 * Wraps the request keyword and other parameters into a single object
 * 
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.model.HackerCard;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Command extends Protocol {
	
	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = 4234893244784413698L;
	
	//allowed commands
	public static final Set<String> COMMANDS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
			new String[]{"DATA UPDATED", "TERMINATE", "INITIATE", "GET PLAYERS", "SELECT CHARACTER"}
			)));
	
	/**
	 * Constructor
	 */
	public Command(String command, HashMap<String,Object> params) {
		super(command, params);		
	}	
		
	/**
	 * Checks if a given set of parameters are valid for a command string
	 * @param command string
	 * @param params hahsmap
	 */
	@Override
	public boolean isValidParams(String command, HashMap<String,Object> params) {
		switch(command) {
			case "INITIATE":
				return params != null && params.size() == 2 && (params.get("password") instanceof String) && (params.get("player_name") instanceof String);	
			case "SELECT CHARACTER":
				return params != null && params.size() == 1 && (params.get("character") instanceof HackerCard);		
			case "GET PLAYERS":
				return params == null || params.size() == 0;
			case "DATA UPDATED":
				return params == null || params.size() == 0;
			case "TERMINATE":
				return params == null || params.size() == 0;
			default:
				return false; //default to false
		}		
	}

	/**
	 * Checks if a given keyword is a valid command
	 * @param keyword string
	 */
	@Override
	public boolean isValidKeyword(String keyword) {
		return COMMANDS.contains(keyword);
	}
	
	/**
	 * toString method
	 */
	public String toString() {
		return "COMMAND " + super.toString();
	}
	
}
