/**
 * Abstract Class for Bag Of Tricks cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

public abstract class BagOfTricksCard extends EntropyCard {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7050967132459634079L;
	
	//common fields
	protected int cost; 
	
	/**
	 * Constructor
	 */
	public BagOfTricksCard(String title, String desc, boolean discard, int cost) {
		super(title, desc, discard);
		this.cost = cost;
	}		
	
	/**
	 * returns the cost
	 */
	public int getCost() {		
		return cost;
	}
	
}
