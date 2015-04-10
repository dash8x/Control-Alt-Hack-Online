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
import grp.ctrlalthack.model.entropy.BoTFreeRerollCard;
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

public class BoTFreeRerollCardPanel extends EntropyBagOfTricksCardPanel {
			
	private JTextArea lbl_reroll_text;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<BoTFreeRerollCard> cards = DataIO.readBoTFreeRerollCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new BoTFreeRerollCardPanel(cards.get(4)));					
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
	public BoTFreeRerollCardPanel(BoTFreeRerollCard card) {
		
		super();
		
		this.card = card;		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 5, 0, 0, 5, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		lbl_reroll_text = new JTextArea("Re-roll skills");
		lbl_reroll_text.setWrapStyleWord(true);
		lbl_reroll_text.setOpaque(false);
		lbl_reroll_text.setLineWrap(true);
		lbl_reroll_text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_reroll_text.setEditable(false);
		lbl_reroll_text.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_reroll_text = new GridBagConstraints();
		gbc_lbl_reroll_text.gridwidth = 2;
		gbc_lbl_reroll_text.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_reroll_text.fill = GridBagConstraints.BOTH;
		gbc_lbl_reroll_text.gridx = 0;
		gbc_lbl_reroll_text.gridy = 5;
		add(lbl_reroll_text, gbc_lbl_reroll_text);
		
		lbl_keep_in_play.setText("Keep this card in play.");		
		GridBagConstraints gbc_lbl_keep_in_play = new GridBagConstraints();
		gbc_lbl_keep_in_play.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_keep_in_play.gridwidth = 2;
		gbc_lbl_keep_in_play.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_keep_in_play.gridx = 0;
		gbc_lbl_keep_in_play.gridy = 6;
		add(lbl_keep_in_play, gbc_lbl_keep_in_play);
				
		GridBagConstraints gbc_lblCost = new GridBagConstraints();
		gbc_lblCost.insets = new Insets(0, 0, 0, 5);
		gbc_lblCost.gridx = 0;
		gbc_lblCost.gridy = 8;
		add(lblCost, gbc_lblCost);
				
		GridBagConstraints gbc_lbl_cost = new GridBagConstraints();
		gbc_lbl_cost.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_cost.gridx = 1;
		gbc_lbl_cost.gridy = 8;
		add(lbl_cost, gbc_lbl_cost);								
		
		//fill in the values
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	protected void populateFields() {
		if ( this.card != null ) {		
			BoTFreeRerollCard card = (BoTFreeRerollCard) this.card;
			lbl_card_title.setText(card.getTitle());			
			lbl_card_desc.setText(card.getDesc());									
			lbl_cost.setText("$" + card.getCost());
			lbl_reroll_text.setText(card.getRerollTitle());
		}
	}


}
