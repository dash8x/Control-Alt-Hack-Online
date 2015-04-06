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
	public final static String CMD_INITIATE = "INITIATE";
	public final static String CMD_CHECK_UPDATED = "DATA_UPDATED";
	public final static String CMD_READY_TO_START = "READY_TO_START";
	public final static String CMD_SELECT_CHARACTER = "SELECT CHARACTER";	
	
	//responses
	public final static String RESP_TERMINATE = "TERMINATE";
	public final static String RESP_PLAYERS = "PLAYERS";
	public final static String RESP_INITIATE = "INITIATE";
	public final static String RESP_FAIL = "FAIL";
	public final static String RESP_ERROR = "ERROR";
	public final static String RESP_SUCCESS = "SUCCESS";
	public final static String RESP_CHECK_UPDATED = "DATA_UPDATED";
	
	
		
}
