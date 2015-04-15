/**
 * Constants for protocol
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;


public interface ProtocolConstants {

	//commands
	public final static String CMD_TERMINATE = "TERMINATE";
	public final static String CMD_GET_PLAYERS = "GET_PLAYERS";
	public final static String CMD_GET_MESSAGE = "GET_MESSAGE";
	public final static String CMD_GET_CURR_MISSION = "GET_CURR_MISSION";
	public final static String CMD_GET_CURR_PLAYER = "GET_CURR_PLAYER";
	public final static String CMD_GET_MY_PLAYER = "GET_MY_PLAYER";
	public final static String CMD_GET_GAME_STATS = "GET_GAME_STATS";
	public final static String CMD_INITIATE = "INITIATE";
	public final static String CMD_CHECK_UPDATED = "DATA_UPDATED";
	public final static String CMD_READY_TO_START = "READY_TO_START";
	public final static String CMD_GET_CHARACTER_CHOICES = "GET_CHARACTER_CHOICES";
	public final static String CMD_SELECT_CHARACTER = "SELECT_CHARACTER";
	public final static String CMD_BUY_BAG_OF_TRICKS = "BUY_BAG_OF_TRICKS";
	public final static String CMD_START_GAME = "START_GAME";
	public final static String CMD_CHECK_TURN = "CHECK_TURN";
	public final static String CMD_ROLL_TASK = "ROLL_TASK";	
	public final static String CMD_ATTEND = "ATTEND";	
	public final static String CMD_TRADE = "TRADE";
	public final static String CMD_RESPOND_OFFER = "RESPOND_TO_OFFER";
	public final static String CMD_GET_INCOMING_OFFER = "CMD_GET_INCOMING_OFFER";
		
	//responses
	public final static String RESP_CHARACTER_CHOICES = "CHARACTER_CHOICES";
	public final static String RESP_TERMINATE = "TERMINATE";
	public final static String RESP_PLAYERS = "PLAYERS";
	public final static String RESP_PLAYER = "PLAYER";
	public final static String RESP_TRADE = "TRADE";
	public final static String RESP_MISSION = "MISSION";
	public final static String RESP_GAME_STATS = "GAME_STATS";
	public final static String RESP_INITIATE = "INITIATE";
	public final static String RESP_FAIL = "FAIL";
	public final static String RESP_ERROR = "ERROR";
	public final static String RESP_SUCCESS = "SUCCESS";
	public final static String RESP_CHECK_UPDATED = "DATA_UPDATED";
	public final static String RESP_MESSAGE = "MESSAGE";
	public final static String RESP_CHECK_TURN = "CHECK_TURN";
	public final static String RESP_ROLL_TASK = "ROLL_TASK";
	
	
		
}
