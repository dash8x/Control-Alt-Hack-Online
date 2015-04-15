/**
 * Standard object used to send responses from server to client
 * Wraps the response keyword and parameters into a single object
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Message;
import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.model.Trade;
import grp.ctrlalthack.model.mission.MissionCard;

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
			new String[]{RESP_CHECK_UPDATED, RESP_TERMINATE, RESP_ERROR, RESP_SUCCESS,
					RESP_FAIL, RESP_PLAYERS, RESP_CHARACTER_CHOICES, RESP_MESSAGE, RESP_GAME_STATS,
					RESP_CHECK_TURN, RESP_MISSION, RESP_ROLL_TASK, RESP_PLAYER, RESP_TRADE}
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
			case RESP_CHARACTER_CHOICES:
				return params != null && params.size() == 1 && (params.get("characters") instanceof HackerCard[]);
			case RESP_ERROR:
				return params != null && params.size() == 1 && (params.get("message") instanceof String);
			case RESP_SUCCESS:
				return params != null && params.size() == 1 && (params.get("operation") instanceof String);
			case RESP_FAIL:
				return params != null && params.size() == 2 && (params.get("operation") instanceof String) && (params.get("message") instanceof String);			
			case RESP_TERMINATE:
				return (params == null || params.size() == 0);
			case RESP_PLAYERS:
				return params != null && params.size() == 1 && (params.get("players") instanceof ArrayList);
			case RESP_PLAYER:
				return params != null && params.size() == 1 && (params.get("player") instanceof Player);
			case RESP_TRADE:
				return params != null && params.size() == 1 && ((params.get("trade") instanceof Trade) || params.get("trade") == null);
			case RESP_MISSION:
				return params != null && params.size() == 1 && (params.get("mission") instanceof MissionCard);
			case RESP_CHECK_TURN:
				return params != null && params.size() == 1 && (params.get("your_turn") instanceof Boolean);
			case RESP_ROLL_TASK:
				return params != null && params.size() == 1 && (params.get("result") instanceof Boolean);
			case RESP_GAME_STATS:
				return params != null && params.size() == 1 && (params.get("stats") instanceof GameStats);
			case RESP_MESSAGE:
				return params != null && params.size() == 1 && (params.get("message") instanceof Message);
			case RESP_CHECK_UPDATED:
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
