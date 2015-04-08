/**
 * Class for free reroll perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

public class DrawDouble extends Perk {	
		
	/**
	 * Constructor
	 */
	public DrawDouble() {
		super();
	}	
	
	/**
	 * toString method
	 */
	public String toString() {
		String ret = "You normally draws two Mission cards and chooses which one you want to use; "
				+ "If you come to the Staff Video Conference, you may look at other players' exposed Missions before "
				+ "you decide. The other drawn Mission is discarded.";
		return ret;
	}
		
}
