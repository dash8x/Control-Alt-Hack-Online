/**
 * Defines the task for a mission
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.mission;

import java.io.Serializable;

import grp.ctrlalthack.model.HackerCard;

public class MissionTask implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284095424253152682L;
	
	//fields
	private String skill; //the skill to roll for
	private int skill_mod = 0; //skill modifier
	private String alt_skill = null; //alternative skill
	private int alt_mod = 0; //alternative skill modifier
	protected String desc = ""; //the description
	
	/**
	 * Constructor
	 */
	public MissionTask(String desc, String skill, int skill_mod) {
		this(desc, skill, skill_mod, null, 0);
	}
	
	/**
	 * Constructor
	 */
	public MissionTask(String desc, String skill, int skill_mod, String alt_skill, int alt_mod) {
		this.desc = desc;
		this.setSkill(skill);
		this.setAltSkill(alt_skill);
		this.skill_mod = Math.abs(skill_mod);
		this.alt_mod = Math.abs(alt_mod);
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
	 * @param set alt skill
	 */
	public void setAltSkill(String skill) {		
		if ( skill != null && !skill.equals("") && !HackerCard.isSkill(skill) ) {
			throw new IllegalArgumentException("Invalid skill " + skill);
		}
		this.alt_skill = skill;		
	}

	/**
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}
	
	/**
	 * @return the alt_skill
	 */
	public String getAltSkill() {
		return alt_skill;
	}

	/**
	 * @return the skill_mod
	 */
	public int getSkillModifier() {
		return skill_mod;
	}

	/**
	 * @return the alt_skill_mod
	 */
	public int getAltSkillModifier() {
		return alt_mod;
	}
	
	/**
	 * checks if have an alternative skill
	 */
	public boolean hasAltSkill() {
		return this.getAltSkill() != null && !this.getAltSkill().equals("");
	}
	
	/**
	 * returns the task title
	 */
	public String getTitle() {
		String ret = HackerCard.getSkillName(getSkill());
		if ( getSkillModifier() != 0 ) {
			ret += " -" + getSkillModifier();
		}
		if ( hasAltSkill() ) {
			ret += " OR " + HackerCard.getSkillName(getAltSkill());
			if ( getAltSkillModifier() != 0 ) {
				ret += " -" + getAltSkillModifier();
			}
				
		}
		return ret;
	}
		
}
