/**
 * Defines what happens when a mission fails
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.mission;

public class MissionFailure extends MissionOutcome {			
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8121922935986452384L;

	/**
	 * Constructor
	 */
	public MissionFailure(String desc, int hacker_creds, int cash, int entropy_cards, boolean all_players) {
		super(desc, hacker_creds, cash, entropy_cards, all_players);		
	}
		
	@Override
	public String getTitle() {
		String ret = "";
		if ( getHackerCreds() > 0 ) {
			ret += "-" + getHackerCreds() + " Hacker Creds ";
		}
		if ( getCash() > 0 ) {
			ret += "-$" + getCash();
		}
		return ret;
	}
}
