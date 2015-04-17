/**
 * Bag Of Tricks auto success card
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

import grp.ctrlalthack.model.HackerCard;

public class BoTAutoSuccessCard extends BagOfTricksCard {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2010492328816599149L;
	
	//common fields
	private String skill;
	
	/**
	 * Constructor
	 */
	public BoTAutoSuccessCard(String title, String desc, int cost, String skill) {
		super(title, desc, true, cost);	
		setSkill(skill);
	}

	/**
	 * @param skill
	 */
	private void setSkill(String skill) {
		if ( skill == null || !HackerCard.isSkill(skill) ) {
			throw new IllegalArgumentException("Invalid skill " + skill);
		}
		this.skill = skill;
	}		
	
	/**
	 * returns the cost
	 */
	public String getSkill() {		
		return skill;
	}
	
	/**
	 * reroll to string
	 */
	public String getAutoSuccessTitle() {
		String ret = "Automatically turns any failed ";
		ret += HackerCard.getSkillName(this.getSkill());		
		ret += " roll into a success.";
		return ret;		
	}
	
}
