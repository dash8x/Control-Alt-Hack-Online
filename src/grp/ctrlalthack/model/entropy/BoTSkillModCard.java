/**
 * Bag Of Tricks cards skill modifier card
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

import java.util.ArrayList;

public class BoTSkillModCard extends BagOfTricksCard {	
	
	//common fields
	private ArrayList<SkillModifier> skill_mods = new ArrayList<SkillModifier>();
	
	/**
	 * Constructor
	 */
	public BoTSkillModCard(String title, String desc, int cost, ArrayList<SkillModifier> skill_mods) {
		super(title, desc, cost);	
		this.skill_mods = skill_mods;
	}		
	
	/**
	 * returns the cost
	 */
	public ArrayList<SkillModifier> getSkillModifiers() {		
		return skill_mods;
	}
	
}
