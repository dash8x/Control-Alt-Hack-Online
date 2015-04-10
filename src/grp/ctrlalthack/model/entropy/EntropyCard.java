/**
 * Abstract Class for Entropy cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

import java.io.Serializable;

public abstract class EntropyCard implements Serializable {	
	
	//common fields
	protected String title; //card title
	protected String desc; //card description
	protected boolean discard_after_use = true; 
	
	/**
	 * Constructor
	 */
	public EntropyCard(String title, String desc) {
		this(title, desc, true);
	}		
	
	/**
	 * Constructor
	 */
	public EntropyCard(String title, String desc, boolean discard) {
		this.title = title;
		this.desc = desc;
		this.discard_after_use = discard;
	}	
	
	/**
	 * returns the mission title
	 */
	public String getTitle() {		
		return title;
	}
	
	/**
	 * returns the mission desc
	 */
	public String getDesc() {		
		return desc;
	}
	
	/**
	 * discard?
	 */
	public boolean discardAfter() {		
		return discard_after_use;
	}
		
}
