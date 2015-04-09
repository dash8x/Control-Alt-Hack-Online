/**
 * Defines what happens when a mission succeeds
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.mission;

public class MissionSuccess extends MissionOutcome {			
	
	/**
	 * Constructor
	 */
	public MissionSuccess(String desc, int hacker_creds, int cash, int entropy_cards, boolean all_players) {
		super(desc, hacker_creds, cash, entropy_cards, all_players);		
	}
		
}
