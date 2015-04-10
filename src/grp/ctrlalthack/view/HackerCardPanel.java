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
import java.util.Iterator;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class HackerCardPanel extends JPanel {

	private JLabel lbl_skill_1;
	private JTextArea lbl_desc;
	private JTextArea lbl_perk;
	private JLabel lbl_name;
	private SkillLabel lbl_val_1;
	private SkillLabel lbl_skill_5;
	private SkillLabel lbl_val_5;
	private SkillLabel lbl_skill_2;
	private SkillLabel lbl_val_2;
	private SkillLabel lbl_skill_6;
	private SkillLabel lbl_val_6;
	private SkillLabel lbl_skill_3;
	private SkillLabel lbl_val_3;
	private SkillLabel lbl_skill_7;
	private SkillLabel lbl_val_7;
	private SkillLabel lbl_skill_4;
	private SkillLabel lbl_val_4;
	private SkillLabel lbl_skill_8;
	private SkillLabel lbl_val_8;
	private HackerCard card;
	
	//list to store variables
	private ArrayList<SkillLabel> skill_labels;
	private ArrayList<SkillLabel> skill_values;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<HackerCard> cards = DataIO.readHackerCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new HackerCardPanel(cards.get(3)));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
	 * Create the panel.
	 */
	public HackerCardPanel() {
		this(null);
	}
	
	/**
	 * Create the panel.
	 */
	public HackerCardPanel(HackerCard card) {
				
		this.skill_labels = new ArrayList<SkillLabel>();
		this.skill_values = new ArrayList<SkillLabel>();
		
		setBorder(new LineBorder(new Color(0, 0, 153), 10));
		setBackground(new Color(153, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 5, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbl_name = new JLabel("Franzi");
		lbl_name.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		lbl_name.setOpaque(true);
		lbl_name.setBackground(new Color(51, 102, 255));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_name.setForeground(Color.WHITE);
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_name.gridwidth = 5;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_name.gridx = 0;
		gbc_lbl_name.gridy = 0;
		add(lbl_name, gbc_lbl_name);
		
		lbl_skill_1 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_1 = new GridBagConstraints();
		gbc_lbl_skill_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_1.gridx = 0;
		gbc_lbl_skill_1.gridy = 2;
		add(lbl_skill_1, gbc_lbl_skill_1);
		
		lbl_val_1 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_1 = new GridBagConstraints();
		gbc_lbl_val_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_val_1.gridx = 1;
		gbc_lbl_val_1.gridy = 2;
		add(lbl_val_1, gbc_lbl_val_1);
				
		
		lbl_skill_2 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_2 = new GridBagConstraints();
		gbc_lbl_skill_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_2.gridx = 0;
		gbc_lbl_skill_2.gridy = 3;
		add(lbl_skill_2, gbc_lbl_skill_2);
		
		lbl_val_2 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_2 = new GridBagConstraints();
		gbc_lbl_val_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_val_2.gridx = 1;
		gbc_lbl_val_2.gridy = 3;
		add(lbl_val_2, gbc_lbl_val_2);
		
		lbl_skill_5 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_5 = new GridBagConstraints();
		gbc_lbl_skill_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_5.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_5.gridx = 3;
		gbc_lbl_skill_5.gridy = 2;
		add(lbl_skill_5, gbc_lbl_skill_5);
		
		lbl_val_5 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_5 = new GridBagConstraints();
		gbc_lbl_val_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_5.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_val_5.gridx = 4;
		gbc_lbl_val_5.gridy = 2;
		add(lbl_val_5, gbc_lbl_val_5);
		
		lbl_skill_6 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_6 = new GridBagConstraints();
		gbc_lbl_skill_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_6.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_6.gridx = 3;
		gbc_lbl_skill_6.gridy = 3;
		add(lbl_skill_6, gbc_lbl_skill_6);
		
		lbl_val_6 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_6 = new GridBagConstraints();
		gbc_lbl_val_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_6.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_val_6.gridx = 4;
		gbc_lbl_val_6.gridy = 3;
		add(lbl_val_6, gbc_lbl_val_6);
		
		lbl_skill_3 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_3 = new GridBagConstraints();
		gbc_lbl_skill_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_3.gridx = 0;
		gbc_lbl_skill_3.gridy = 4;
		add(lbl_skill_3, gbc_lbl_skill_3);
		
		lbl_val_3 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_3 = new GridBagConstraints();
		gbc_lbl_val_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_val_3.gridx = 1;
		gbc_lbl_val_3.gridy = 4;
		add(lbl_val_3, gbc_lbl_val_3);
		
		lbl_skill_7 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_7 = new GridBagConstraints();
		gbc_lbl_skill_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_7.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_7.gridx = 3;
		gbc_lbl_skill_7.gridy = 4;
		add(lbl_skill_7, gbc_lbl_skill_7);
		
		lbl_val_7 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_7 = new GridBagConstraints();
		gbc_lbl_val_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_7.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_val_7.gridx = 4;
		gbc_lbl_val_7.gridy = 4;
		add(lbl_val_7, gbc_lbl_val_7);
		
		lbl_skill_4 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_4 = new GridBagConstraints();
		gbc_lbl_skill_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_4.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_4.gridx = 0;
		gbc_lbl_skill_4.gridy = 5;
		add(lbl_skill_4, gbc_lbl_skill_4);
		
		lbl_val_4 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_4 = new GridBagConstraints();
		gbc_lbl_val_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_4.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_val_4.gridx = 1;
		gbc_lbl_val_4.gridy = 5;
		add(lbl_val_4, gbc_lbl_val_4);
		
		lbl_skill_8 = new SkillLabel("Skill 1");		
		GridBagConstraints gbc_lbl_skill_8 = new GridBagConstraints();
		gbc_lbl_skill_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_skill_8.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_skill_8.gridx = 3;
		gbc_lbl_skill_8.gridy = 5;
		add(lbl_skill_8, gbc_lbl_skill_8);
		
		lbl_val_8 = new SkillLabel("2");		
		GridBagConstraints gbc_lbl_val_8 = new GridBagConstraints();
		gbc_lbl_val_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_val_8.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_val_8.gridx = 4;
		gbc_lbl_val_8.gridy = 5;
		add(lbl_val_8, gbc_lbl_val_8);
		
		lbl_perk = new JTextArea("New label");
		lbl_perk.setWrapStyleWord(true);
		lbl_perk.setLineWrap(true);
		lbl_perk.setEditable(false);
		lbl_perk.setOpaque(false);
		lbl_perk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Border lbl_perk_padding = BorderFactory.createEmptyBorder(10,10,10,10);
		lbl_perk.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_perk = new GridBagConstraints();
		gbc_lbl_perk.fill = GridBagConstraints.BOTH;
		gbc_lbl_perk.gridwidth = 5;
		gbc_lbl_perk.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_perk.gridx = 0;
		gbc_lbl_perk.gridy = 7;
		add(lbl_perk, gbc_lbl_perk);
		
		lbl_desc = new JTextArea("New label");
		lbl_desc.setLineWrap(true);
		lbl_desc.setWrapStyleWord(true);
		lbl_desc.setEditable(false);
		lbl_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Border lbl_desc_padding = BorderFactory.createEmptyBorder(10,10,10,10);
		lbl_desc.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		lbl_desc.setBackground(Color.WHITE);
		lbl_desc.setOpaque(true);
		GridBagConstraints gbc_lbl_desc = new GridBagConstraints();
		gbc_lbl_desc.fill = GridBagConstraints.BOTH;
		gbc_lbl_desc.gridwidth = 5;
		gbc_lbl_desc.gridx = 0;
		gbc_lbl_desc.gridy = 9;
		add(lbl_desc, gbc_lbl_desc);
		
		//add labels to list
		skill_labels.add(lbl_skill_2);
		skill_labels.add(lbl_skill_3);
		skill_labels.add(lbl_skill_4);
		skill_labels.add(lbl_skill_5);
		skill_labels.add(lbl_skill_6);
		skill_labels.add(lbl_skill_7);
		skill_labels.add(lbl_skill_8);
		
		skill_values.add(lbl_val_2);
		skill_values.add(lbl_val_3);
		skill_values.add(lbl_val_4);
		skill_values.add(lbl_val_5);
		skill_values.add(lbl_val_6);
		skill_values.add(lbl_val_7);
		skill_values.add(lbl_val_8);
		
		//fill in the values
		this.setCard(card);		
	}
	
	/**
	 * Sets the card
	 */
	public void setCard(HackerCard card) {
		this.card = card;
		populateFields();
	}

	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.card != null ) {
			lbl_name.setText(card.getName());
			lbl_desc.setText(card.getDesc());
			lbl_perk.setText(card.getPerkText());
			lbl_skill_1.setText("Kitchen Sink");
			lbl_val_1.setText(Integer.toString(card.getKitchenSink()));
			//add skills
			Iterator it = card.getSkills().entrySet().iterator();
		    for ( int i = 0; i < skill_labels.size(); i++ ) {
				SkillLabel label = skill_labels.get(i);
				SkillLabel val = skill_values.get(i);
		    	if (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        label.setText(HackerCard.getSkillName(pair.getKey().toString()));
			        if ( (Integer) pair.getValue() == GameConstants.INFINITE_SKILL ) {
			        	val.setText("*");
			        } else {
			        	val.setText(pair.getValue().toString());
			        }			       
			    } else {
			    	label.setVisible(false);
			    	val.setVisible(false);
			    }
		    }
		    this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Skill panel
	 */
	private class SkillLabel extends JLabel {
		
		/**
		 * Constructor
		 */
		public SkillLabel() {
			this("");
		}

		/**
		 * Constructor
		 */
		public SkillLabel(String text) {
			super(text);
			this.setBackground(Color.WHITE);
			this.setFont(new Font("Tahoma", Font.PLAIN, 12));
			Border lbl_skill_padding = BorderFactory.createEmptyBorder(5,5,5,5);
			this.setBorder(BorderFactory.createCompoundBorder(null,lbl_skill_padding));
			this.setOpaque(true);
		}
	}

}
