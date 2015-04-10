/**
 * Object to wrap game stats
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import java.io.Serializable;

public class GameStats implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7188238375013362447L;
	
	private int total_creds; //total hacker creds
	private int  total_cash; //cash
	private int curr_round; //round
	private int curr_phase; //phase
	private String curr_player; //player
	
	/**
	 * Constructor
	 */
	public GameStats(int total_creds, int total_cash, int curr_round, int curr_phase, String curr_player) {
		this.total_creds = total_creds;
		this.total_cash = total_cash;	
		this.curr_round = curr_round;
		this.curr_phase = curr_phase;	
		this.curr_player = curr_player;
	}

	/**
	 * @return the total_creds
	 */
	public int getTotalCreds() {
		return total_creds;
	}

	/**
	 * @return the total_cash
	 */
	public int getTotalCash() {
		return total_cash;
	}

	/**
	 * @return the curr_round
	 */
	public int getCurrRound() {
		return curr_round;
	}

	/**
	 * @return the curr_phase
	 */
	public int getCurrPhase() {
		return curr_phase;
	}

	/**
	 * @return the curr_player
	 */
	public String getCurrPlayer() {
		return curr_player;
	}
	
	
}
