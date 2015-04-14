/**
 * Defines what happens when a mission ends
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.mission;

import java.io.Serializable;

public abstract class MissionOutcome implements Serializable {	
	
	//fields
	protected boolean all_players = false; //whether applies to all players
	protected int cash = 0; //cash bonus
	protected int hacker_creds = 0; //hacker cred bonus
	protected int entropy_cards = 0; //number of entropy cards
	protected String desc = ""; //the description
	
	/**
	 * Constructor
	 */
	public MissionOutcome(String desc, int hacker_creds, int cash, int entropy_cards, boolean all_players) {
		this.desc = desc;
		this.hacker_creds = hacker_creds;
		this.cash = cash;
		this.entropy_cards = entropy_cards;
		this.all_players = all_players;
	}

	/**
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @return the all_players
	 */
	public boolean forAllPlayers() {
		return all_players;
	}

	/**
	 * @return the cash
	 */
	public int getCash() {
		return Math.abs(cash);
	}

	/**
	 * @return the hacker_creds
	 */
	public int getHackerCreds() {
		return Math.abs(hacker_creds);
	}

	/**
	 * @return the entropy_cards
	 */
	public int getEntropyCards() {
		return Math.abs(entropy_cards);
	}	
	
	/**
	 * returns the title
	 */
	public abstract String getTitle();
		
}
