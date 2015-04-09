/**
 * Defines a skill modifier
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.entropy;

import grp.ctrlalthack.model.HackerCard;

public class SkillModifier {	
	
	//fields
	private String skill; //the skill to roll for
	private int skill_mod = 0; //skill modifier
	
	/**
	 * Constructor
	 */
	public SkillModifier(String skill, int skill_mod) {		
		this.setSkill(skill);		
		this.skill_mod = skill_mod;		
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		if ( HackerCard.isSkill(skill) ) {
			this.skill = skill;
		} else {
			throw new IllegalArgumentException("Invalid skill " + skill);
		}
	}

	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}
	
	/**
	 * @return the skill_mod
	 */
	public int getSkillModifier() {
		return Math.abs(skill_mod);
	}
	
	/**
	 * returns the task title
	 */
	public String toString() {
		String ret = "";
		if ( getSkillModifier() != 0 ) {
			ret += "+" + getSkillModifier() + " ";
		}
		ret += HackerCard.getSkillName(getSkill());			
		return ret;
	}
		
}
