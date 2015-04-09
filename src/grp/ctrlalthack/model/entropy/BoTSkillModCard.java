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
		super(title, desc, false, cost);	
		setSkillMods(skill_mods);
	}

	/**
	 * @param skill_mods
	 */
	private void setSkillMods(ArrayList<SkillModifier> skill_mods) {
		if ( skill_mods == null || skill_mods.size() == 0 || skill_mods.size() > 2 ) {
			throw new IllegalArgumentException("Skill modifiers cannot be empty or more than 2");
		}
		this.skill_mods = skill_mods;
	}		
	
	/**
	 * returns the cost
	 */
	public ArrayList<SkillModifier> getSkillModifiers() {		
		return skill_mods;
	}
	
}
