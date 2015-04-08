/**
 * Defines Hacker Character Cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import grp.ctrlalthack.model.perks.DrawDouble;
import grp.ctrlalthack.model.perks.HackerCredPenalty;
import grp.ctrlalthack.model.perks.Perk;
import grp.ctrlalthack.model.perks.StartCash;

import java.util.HashMap;

public class HackerCard implements GameConstants {
	
	private String name; //name of the character
	private String desc; //character description
	private int kitchen_sink; //kitchen sink skill level
	private HashMap<String,Integer> skills; //skill set
	private Perk perk; //special perks
	
	/*
	 * 
	 * reroll_cond 
	 * by_entropy_card_discard
	 */
	
	/**
	 * Constructor
	 */
	public HackerCard(String name, String desc, HashMap<String,Integer> skills, int kitchen_sink) {
		this(name, desc, skills, kitchen_sink, null);		
	}		
	
	/**
	 * Constructor
	 */
	public HackerCard(String name, String desc, HashMap<String,Integer> skills, int kitchen_sink, Perk perk) {
		this.name = name;
		this.desc = desc;	
		this.setKitchenSink(kitchen_sink);
		this.setSkills(skills);	
		this.perk = perk;
	}
	
	/**
	 * @param kitchen_sink the kitchen_sink to set
	 */
	private void setKitchenSink(int kitchen_sink) {
		if ( kitchen_sink < 0 ) {
			throw new IllegalArgumentException("Skill value has to be positive");
		}
		this.kitchen_sink = kitchen_sink;
	}

	/**
	 * @param skills the skills to set
	 */
	private void setSkills(HashMap<String, Integer> skills) {
		if ( skills == null || skills.isEmpty() ) {
			throw new IllegalArgumentException("Skill set cannot be empty");
		} else if ( skills.size() > 7 ) {
			throw new IllegalArgumentException("Skill set can have maximum 7 skills");
		}
		this.skills = skills;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the kitchen_sink
	 */
	public int getKitchenSink() {
		return kitchen_sink;
	}

	/**
	 * @return the perk
	 */
	public Perk getPerk() {
		return perk;
	}
	
	/**
	 * @return the skills
	 */
	public HashMap<String, Integer> getSkills() {
		return skills;
	}
	
	/**
	 * @return the value for a specific skill
	 */
	public int getSkill(String skill) {		
		Integer skill_val = this.getSkills().get(skill);
		if (  skill_val == null ) {
			//return kitchen sink if skill not specified
			return this.getKitchenSink();
		} else {
			return skill_val;
		}
	}
	
	/**
	 * @return the start_cash
	 */
	public int getStartCash() {
		if ( getPerk() instanceof StartCash ) {
			return ((StartCash) getPerk()).getAmount();
		} else {
			return START_CASH;
		}
	}

	/**
	 * @return the draw_double
	 */
	public boolean canDrawDouble() {
		return this.getPerk() instanceof DrawDouble;
	}	

	/**
	 * @return the fail_penalty
	 */
	public int getHackerCredPenalty() {
		if ( this.getPerk() instanceof HackerCredPenalty ) {
			return ((HackerCredPenalty) getPerk()).getPenalty();
		} else {
			return 0;
		}
	}
	
	/**
	 * @returns the skill name from slug
	 */
	public static String getSkillName(String skill) {
		String name = "";
		switch (skill) {						
			case HARDWARE:
				name = "Hardware Hacking";
			case NETWORK:
				name = "Network Ninja";
			case SOCIAL:
				name = "Social Engineering";
			case SOFTWARE:
				name = "Software Wizardry";
			case CRYPT:
				name = "Cryptanalysis";
			case LOCKPICK:
				name = "Lockpicking";
			case SEARCH:
				name = "Search Fu";
			case FORENSICS:
				name = "Forensics";
			case BARISTA:
				name = "Barista";
			case WEB:
				name = "Web Procurement";
			case CONNECTIONS:
				name = "Connections";
		}
		return name;
	}

}
