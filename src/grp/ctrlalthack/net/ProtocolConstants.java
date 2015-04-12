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
	public final static String CMD_GET_GAME_STATS = "GET_GAME_STATS";
	public final static String CMD_INITIATE = "INITIATE";
	public final static String CMD_CHECK_UPDATED = "DATA_UPDATED";
	public final static String CMD_READY_TO_START = "READY_TO_START";
	public final static String CMD_GET_CHARACTER_CHOICES = "GET_CHARACTER_CHOICES";
	public final static String CMD_SELECT_CHARACTER = "SELECT_CHARACTER";
	public final static String CMD_START_GAME = "START_GAME";
	
	//responses
	public final static String RESP_CHARACTER_CHOICES = "CHARACTER_CHOICES";
	public final static String RESP_TERMINATE = "TERMINATE";
	public final static String RESP_PLAYERS = "PLAYERS";
	public final static String RESP_GAME_STATS = "GAME_STATS";
	public final static String RESP_INITIATE = "INITIATE";
	public final static String RESP_FAIL = "FAIL";
	public final static String RESP_ERROR = "ERROR";
	public final static String RESP_SUCCESS = "SUCCESS";
	public final static String RESP_CHECK_UPDATED = "DATA_UPDATED";
	public final static String RESP_MESSAGE = "MESSAGE";
	
	
		
}
