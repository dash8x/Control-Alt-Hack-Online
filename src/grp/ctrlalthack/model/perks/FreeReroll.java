/**
 * Class for free reroll perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

import grp.ctrlalthack.model.conditions.DiscardEntropyCard;
import grp.ctrlalthack.model.conditions.PlayCondition;

public class FreeReroll extends Perk {	
		
	/**
	 * Constructor
	 */
	public FreeReroll() {
		this(null);
	}
	
	/**
	 * Constructor
	 */
	public FreeReroll(PlayCondition cond) {
		super(cond);
	}
	
	/**
	 * toString method
	 */
	public String toString() {
		String ret = "";
		if ( this.getCondition() instanceof DiscardEntropyCard ) {
			ret = "Any time you fail a roll, you can immediately try again by discarding one Entropy card from your hand. You may continue until you run out of cards.";
		} else {
			ret = "Once per turn, you get a free re-roll. These free re-rolls may not be saved up.";
		}
		return ret;
	}
		
}
