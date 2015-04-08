/**
 * Class for HackerCredPenalty perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

import grp.ctrlalthack.model.GameConstants;

public class StartCash extends Perk implements GameConstants {	

	private int amount;
	
	/**
	 * Constructor
	 */
	public StartCash(int amount) {
		super();
		this.amount = amount;
	}
	
	/**
	 * Penalty
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * toString
	 */
	public String toString() {		
		return "You normally gets " + this.getAmount() + " at the begginning of each round"
				+ " (instead of " + START_CASH + ").";
	}
		
}
