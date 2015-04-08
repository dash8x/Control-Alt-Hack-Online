/**
 * Class for free reroll perk
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.perks;

import java.util.ArrayList;

import grp.ctrlalthack.model.conditions.DiscardEntropyCard;
import grp.ctrlalthack.model.conditions.PlayCondition;

public class SubstituteSkill extends Perk {	
	
	private ArrayList<String> skills_to_substitute;
	private ArrayList<String> skills_substituted_for;
	
	/**
	 * Constructor
	 */
	public SubstituteSkill() {
		this(null, null);
	}
	
	/**
	 * Constructor
	 */
	public SubstituteSkill(ArrayList<String> skills_to_substitute, ArrayList<String> skills_substituted_for) {
		super();
		this.skills_to_substitute = skills_to_substitute;
		this.skills_substituted_for = skills_substituted_for;
	}
	

	/**
	 * @return the skills_to_substitute
	 */
	public ArrayList<String> getSkillsToSubstitute() {
		return skills_to_substitute;
	}

	/**
	 * @return the skills_substituted_for
	 */
	public ArrayList<String> getSkillsSubstitutedFor() {
		return skills_substituted_for;
	}

	/**
	 * toString method
	 */
	public String toString() {
		String ret = "";
		if ( this.getCondition() instanceof DiscardEntropyCard ) {
			ret = "By discarding two cards from your hand, you can substitute " + this.getSkillsToSubstitute()
					+ " for " + this.getSkillsSubstitutedFor()
					+ " This substituition only applies to one task.";
		} else {
			ret = "Once per turn, you may substitute any one skill for any other skill. This substituition only applies to one task.";
		}
		return ret;
	}
		
}
