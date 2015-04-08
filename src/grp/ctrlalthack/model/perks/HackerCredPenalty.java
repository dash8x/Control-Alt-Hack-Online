/**
 * Class for HackerCredPenalty perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

public class HackerCredPenalty extends Perk {	
		
	/**
	 * Constructor
	 */
	public HackerCredPenalty() {
		super();
	}
	
	/**
	 * Penalty
	 */
	public int getPenalty() {
		return 1;
	}
	
	/**
	 * toString
	 */
	public String toString() {		
		return "Any time you fail a Mission, you loses " + this.getPenalty() + " extra Hacker Cred point(s).";
	}
		
}
