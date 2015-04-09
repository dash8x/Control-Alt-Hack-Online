/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.GameConstants;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.entropy.BoTSkillModCard;
import grp.ctrlalthack.model.entropy.SkillModifier;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.model.mission.MissionTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class BoTSkillModCardPanel extends JPanel {
	
	private JTextArea lbl_card_desc;	
	private JLabel lbl_card_title;
	private BoTSkillModCard card;
	
	//list to store variables
	private ArrayList<JLabel> skill_mod_labels;		
	private JLabel lbl_skill_mod_1;
	private JLabel lbl_name;
	private JLabel lbl_skill_mod_2;
	private JLabel lbl_keep_in_play;
	private JLabel lblCost;
	private JLabel lbl_cost;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<BoTSkillModCard> cards = DataIO.readBoTSkillModCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new BoTSkillModCardPanel(cards.get(3)));					
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	public BoTSkillModCardPanel(BoTSkillModCard card) {
		
		this.card = card;
		this.skill_mod_labels = new ArrayList<JLabel>();		
		
		setBorder(new LineBorder(new Color(204, 102, 51), 10));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);		
		
		lbl_name = new JLabel("BAG OF TRICKS");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		lbl_name.setOpaque(true);
		lbl_name.setBackground(new Color(255, 153, 51));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_name.setForeground(Color.WHITE);
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.gridwidth = 2;
		gbc_lbl_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_name.gridx = 0;
		gbc_lbl_name.gridy = 0;
		add(lbl_name, gbc_lbl_name);				
		
		lbl_card_title = new JLabel("Card Title");
		lbl_card_title.setForeground(new Color(204, 102, 0));
		lbl_card_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_card_title.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lbl_card_title = new GridBagConstraints();
		gbc_lbl_card_title.gridwidth = 2;
		gbc_lbl_card_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_card_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_card_title.gridx = 0;
		gbc_lbl_card_title.gridy = 2;
		add(lbl_card_title, gbc_lbl_card_title);
		
		lbl_card_desc = new JTextArea("Card desc");
		lbl_card_desc.setWrapStyleWord(true);
		lbl_card_desc.setLineWrap(true);
		lbl_card_desc.setEditable(false);
		lbl_card_desc.setOpaque(false);
		lbl_card_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_card_desc.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
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
		
		lbl_keep_in_play = new JLabel("Keep this card in play.");
		lbl_keep_in_play.setForeground(Color.BLACK);
		lbl_keep_in_play.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lbl_keep_in_play.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_keep_in_play = new GridBagConstraints();
		gbc_lbl_keep_in_play.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_keep_in_play.gridwidth = 2;
		gbc_lbl_keep_in_play.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_keep_in_play.gridx = 0;
		gbc_lbl_keep_in_play.gridy = 7;
		add(lbl_keep_in_play, gbc_lbl_keep_in_play);
		
		lblCost = new JLabel("Cost:");
		lblCost.setForeground(new Color(153, 102, 0));
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCost.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblCost = new GridBagConstraints();
		gbc_lblCost.insets = new Insets(0, 0, 0, 5);
		gbc_lblCost.gridx = 0;
		gbc_lblCost.gridy = 9;
		add(lblCost, gbc_lblCost);
		
		lbl_cost = new JLabel("$2000");
		lbl_cost.setForeground(new Color(153, 102, 0));
		lbl_cost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_cost.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
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
	private void populateFields() {
		if ( this.card != null ) {									
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
