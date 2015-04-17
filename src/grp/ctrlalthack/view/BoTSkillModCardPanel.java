/**
 * Displays Bag of Tricks Skill Modifier card
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;


import grp.ctrlalthack.model.entropy.BoTSkillModCard;
import grp.ctrlalthack.model.entropy.SkillModifier;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class BoTSkillModCardPanel extends EntropyBagOfTricksCardPanel {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7480015942731055329L;
	
	//list to store variables
	private ArrayList<JLabel> skill_mod_labels;		
	private JLabel lbl_skill_mod_1;	
	private JLabel lbl_skill_mod_2;
	
	/**
	 * Create the panel.
	 */
	public BoTSkillModCardPanel(BoTSkillModCard card) {
		
		super();
		
		this.card = card;
		this.skill_mod_labels = new ArrayList<JLabel>();		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);		
				
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.gridwidth = 2;
		gbc_lbl_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_name.gridx = 0;
		gbc_lbl_name.gridy = 0;
		add(lbl_name, gbc_lbl_name);				
				
		GridBagConstraints gbc_lbl_card_title = new GridBagConstraints();
		gbc_lbl_card_title.gridwidth = 2;
		gbc_lbl_card_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_card_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_card_title.gridx = 0;
		gbc_lbl_card_title.gridy = 2;
		add(lbl_card_title, gbc_lbl_card_title);
				
		GridBagConstraints gbc_lbl_card_desc = new GridBagConstraints();
		gbc_lbl_card_desc.gridwidth = 2;
		gbc_lbl_card_desc.fill = GridBagConstraints.BOTH;
		gbc_lbl_card_desc.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_card_desc.gridx = 0;
		gbc_lbl_card_desc.gridy = 3;
		add(lbl_card_desc, gbc_lbl_card_desc);
		
		lbl_skill_mod_1 = new JLabel("+1 Skill");
		lbl_skill_mod_1.setForeground(Color.BLACK);
		lbl_skill_mod_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_skill_mod_1.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_skill_mod_1 = new GridBagConstraints();
		gbc_lbl_skill_mod_1.gridwidth = 2;
		gbc_lbl_skill_mod_1.anchor = GridBagConstraints.WEST;
		gbc_lbl_skill_mod_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_skill_mod_1.gridx = 0;
		gbc_lbl_skill_mod_1.gridy = 5;
		add(lbl_skill_mod_1, gbc_lbl_skill_mod_1);
		
		lbl_skill_mod_2 = new JLabel("+1 Skill");
		lbl_skill_mod_2.setForeground(Color.BLACK);
		lbl_skill_mod_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_skill_mod_2.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_skill_mod_2 = new GridBagConstraints();
		gbc_lbl_skill_mod_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_mod_2.gridwidth = 2;
		gbc_lbl_skill_mod_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_skill_mod_2.gridx = 0;
		gbc_lbl_skill_mod_2.gridy = 6;
		add(lbl_skill_mod_2, gbc_lbl_skill_mod_2);
		
		lbl_keep_in_play.setText("Keep this card in play.");		
		GridBagConstraints gbc_lbl_keep_in_play = new GridBagConstraints();
		gbc_lbl_keep_in_play.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_keep_in_play.gridwidth = 2;
		gbc_lbl_keep_in_play.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_keep_in_play.gridx = 0;
		gbc_lbl_keep_in_play.gridy = 7;
		add(lbl_keep_in_play, gbc_lbl_keep_in_play);
				
		GridBagConstraints gbc_lblCost = new GridBagConstraints();
		gbc_lblCost.insets = new Insets(0, 0, 0, 5);
		gbc_lblCost.gridx = 0;
		gbc_lblCost.gridy = 9;
		add(lblCost, gbc_lblCost);
				
		GridBagConstraints gbc_lbl_cost = new GridBagConstraints();
		gbc_lbl_cost.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_cost.gridx = 1;
		gbc_lbl_cost.gridy = 9;
		add(lbl_cost, gbc_lbl_cost);				
		
		//add task panels to list
		skill_mod_labels.add(lbl_skill_mod_1);
		skill_mod_labels.add(lbl_skill_mod_2);
		
		//fill in the values
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	protected void populateFields() {
		if ( this.card != null ) {
			BoTSkillModCard card = (BoTSkillModCard) this.card;
			lbl_card_title.setText(card.getTitle());			
			lbl_card_desc.setText(card.getDesc());									
			lbl_cost.setText("$" + card.getCost());

			//add skill_mods
			ArrayList<SkillModifier> skill_mods = card.getSkillModifiers();
		    for ( int i = 0; i < skill_mod_labels.size(); i++ ) {
		    	JLabel lb = skill_mod_labels.get(i);
				try {
					lb.setText(skill_mods.get(i).toString());
				} catch (IndexOutOfBoundsException e) {	
					lb.setVisible(false);
				}
		    }
		}
	}


}
