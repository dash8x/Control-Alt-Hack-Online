/**
 * Bag Of Tricks cards skill free re-roll card
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

import grp.ctrlalthack.model.HackerCard;

import java.util.ArrayList;

public class BoTFreeRerollCard extends BagOfTricksCard {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6186945214230272289L;
	
	//common fields
	private ArrayList<String> skills = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public BoTFreeRerollCard(String title, String desc, int cost, ArrayList<String> skills) {
		super(title, desc, false, cost);	
		setSkills(skills);
	}

	/**
	 * @param skills
	 */
	private void setSkills(ArrayList<String> skills) {
		if ( skills == null || skills.size() == 0 || skills.size() > 2 ) {
			throw new IllegalArgumentException("Free re-roll skills cannot be empty or more than 2");
		}
		this.skills = skills;
	}		
	
	/**
	 * returns the cost
	 */
	public ArrayList<String> getSkills() {		
		return skills;
	}
	
	/**
	 * reroll to string
	 */
	public String getRerollTitle() {
		String ret = "Once per turn, you may re-roll a failed ";
		ret += HackerCard.getSkillName(this.getSkills().get(0));
		if ( this.getSkills().size() == 2) {
			ret += " or " + HackerCard.getSkillName(this.getSkills().get(1));
		}
		ret += " roll.";
		return ret;		
	}
	
}
