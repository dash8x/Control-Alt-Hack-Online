/**
 * Abstract Class for Bag Of Tricks cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

public abstract class BagOfTricksCard extends EntropyCard {	
	
	//common fields
	protected int cost; 
	
	/**
	 * Constructor
	 */
	public BagOfTricksCard(String title, String desc, int cost) {
		super(title, desc, false);
		this.cost = cost;
	}		
	
	/**
	 * returns the cost
	 */
	public int getCost() {		
		return cost;
	}
	
}
