/**
 * Abstract class for perks
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

import grp.ctrlalthack.model.conditions.PlayCondition;

public abstract class Perk {	
	
	private PlayCondition condition;
	
	/**
	 * Constructor
	 */
	public Perk() {
		this(null);
	}
	
	/**
	 * Constructor
	 */
	public Perk(PlayCondition cond) {
		this.condition = cond;
	}
	
	/**
	 * Returns the condition
	 */
	public PlayCondition getCondition() {
		return this.condition;
	}
		
}
