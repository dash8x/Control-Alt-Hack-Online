/**
 * Object to wrap trade
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import java.io.Serializable;

public class Trade implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3200514985724069779L;
	
	private int player_id; //id
	private int cash;		
	private String player_name = "";
	
	/**
	 * Constructor
	 */
	public Trade(int player_id, int cash) {
		this(player_id, cash, "");
	}
	
	/**
	 * Constructor
	 */
	public Trade(int player_id, int cash, String player_name) {
		this.player_id = player_id;		
		if ( cash < 1 ) {
			throw new IllegalArgumentException("Cash offer must be greater than 0");
		}
		this.cash = cash;
		this.setPlayerName(player_name);
	}			

	/**
	 * @return the cash
	 */
	public int getCash() {
		return cash;
	}
	
	/**
	 * @return the id
	 */
	public int getPlayerID() {
		return player_id;
	}	
	
	/**
	 * @return the id
	 */
	public void setPlayerName(String name) {
		player_name = name;
	}
	
	/**
	 * @return the id
	 */
	public String getPlayerName() {
		return player_name;
	}
	
}
