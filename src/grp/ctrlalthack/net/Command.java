/**
 * Standard object used to send requests from client to server
 * Wraps the request keyword and other parameters into a single object
 * 
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.model.Trade;

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
			new String[]{CMD_CHECK_UPDATED, CMD_TERMINATE, CMD_INITIATE, CMD_GET_PLAYERS,
					CMD_SELECT_CHARACTER, CMD_READY_TO_START, CMD_GET_CHARACTER_CHOICES,
					CMD_START_GAME, CMD_GET_MESSAGE, CMD_GET_GAME_STATS, CMD_CHECK_TURN, CMD_GET_CURR_MISSION,
					CMD_ROLL_TASK, CMD_GET_CURR_PLAYER, CMD_GET_MY_PLAYER, CMD_BUY_BAG_OF_TRICKS, CMD_ATTEND,
					CMD_TRADE, CMD_RESPOND_OFFER, CMD_GET_INCOMING_OFFER}
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
			case CMD_INITIATE:
				return params != null && params.size() == 2 && (params.get("password") instanceof String) && (params.get("player_name") instanceof String);	
			case CMD_SELECT_CHARACTER:
				return params != null && params.size() == 1 && (params.get("character") instanceof Integer);	
			case CMD_BUY_BAG_OF_TRICKS:
				return params != null && params.size() == 1 && (params.get("card") instanceof Integer);	
			case CMD_TRADE:
				return params != null && params.size() == 1 && (params.get("trade") instanceof Trade);
			case CMD_RESPOND_OFFER:
				return params != null && params.size() == 1 && (params.get("accept") instanceof Boolean);
			case CMD_GET_CHARACTER_CHOICES:
				return params == null || params.size() == 0;
			case CMD_READY_TO_START:
				return params == null || params.size() == 0;
			case CMD_GET_MY_PLAYER:
				return params == null || params.size() == 0;
			case CMD_GET_INCOMING_OFFER:
				return params == null || params.size() == 0;
			case CMD_GET_PLAYERS:
				return params == null || params.size() == 0;
			case CMD_GET_CURR_MISSION:
				return params == null || params.size() == 0;
			case CMD_GET_CURR_PLAYER:
				return params == null || params.size() == 0;
			case CMD_CHECK_TURN:
				return params == null || params.size() == 0;
			case CMD_ATTEND:
				return params == null || params.size() == 0;
			case CMD_GET_GAME_STATS:
				return params == null || params.size() == 0;
			case CMD_ROLL_TASK:
				return params == null || params.size() == 0;
			case CMD_GET_MESSAGE:
				return params == null || params.size() == 0;
			case CMD_CHECK_UPDATED:
				return params == null || params.size() == 0;
			case CMD_START_GAME:
				return params == null || params.size() == 0;
			case CMD_TERMINATE:
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
