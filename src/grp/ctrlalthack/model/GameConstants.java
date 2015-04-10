/**
 * Constants for the game
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface GameConstants {
		
	//skill names
	public static final String HARDWARE = "hardware_hacking";
	public static final String NETWORK = "network_ninja";
	public static final String SOCIAL = "social_engineering";
	public static final String SOFTWARE = "software_wizardry";
	public static final String CRYPT = "cryptanalysis";	
	public static final String LOCKPICK = "lockpicking";
	public static final String SEARCH = "search_fu";
	public static final String FORENSICS = "forensics";
	public static final String BARISTA = "barista";
	public static final String WEB = "web_procurement";
	public static final String CONNECTIONS = "connections";
	
	public static final Set<String> VALID_SKILLS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
			new String[]{HARDWARE, NETWORK, SOCIAL, SOFTWARE, CRYPT,
		LOCKPICK, SEARCH, SEARCH, SEARCH, FORENSICS, BARISTA, WEB, CONNECTIONS}))); 
			
	//misc
	public static final int INFINITE_SKILL = -1;
	public static final int START_CASH = 2000;
	public static final int START_CREDS = 6;
	
	//game status
	public static final int STATUS_WAITING_TO_START = 0;
	public static final int STATUS_CHOOSE_CHARACTER = 1;

}
