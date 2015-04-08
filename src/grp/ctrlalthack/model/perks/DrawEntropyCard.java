/**
 * Class for free reroll perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

import grp.ctrlalthack.model.conditions.PayCash;
import grp.ctrlalthack.model.conditions.PlayCondition;

public class DrawEntropyCard extends Perk {	
		
	/**
	 * Constructor
	 */
	public DrawEntropyCard() {
		this(null);
	}
	
	/**
	 * Constructor
	 */
	public DrawEntropyCard(PlayCondition cond) {
		super(cond);
	}
	
	/**
	 * Draw from entropy deck
	 */
	public int cashForEntropyDeck() {
		return 1000;
	}
	
	/**
	 * Draw from entropy discards deck
	 */
	public int cashForEntropyDiscards() {
		return 2000;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		String ret = "";
		if ( this.getCondition() instanceof PayCash ) {
			ret = "Each round, at the beginning of your Mission, you may spend " + this.cashForEntropyDeck() 
					+ " to draw the top Entropy card, or " + this.cashForEntropyDiscards() 
					+ " to draw the top top Entropy discard.";
		}
		return ret;
	}
		
}
