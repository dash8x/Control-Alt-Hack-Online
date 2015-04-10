/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;


import grp.ctrlalthack.model.entropy.BagOfTricksCard;
import grp.ctrlalthack.model.entropy.BoTAutoSuccessCard;
import grp.ctrlalthack.model.entropy.BoTFreeRerollCard;
import grp.ctrlalthack.model.entropy.BoTSkillModCard;
import grp.ctrlalthack.model.entropy.EntropyCard;

import javax.swing.JPanel;

import java.awt.Color;







import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;








import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public abstract class EntropyCardPanel extends JPanel {
	
	protected JTextArea lbl_card_desc;	
	protected JLabel lbl_card_title;	
	protected String card_type;
	protected EntropyCard card;
	
	//list to store variables
	protected JLabel lbl_name;
		
	
	/**
	 * Create the panel.
	 */
	public EntropyCardPanel(String card_type) {
		
		this.card_type = card_type;
		
		setBorder(new LineBorder(new Color(204, 102, 51), 10));
		setBackground(Color.WHITE);	
		setPreferredSize(new Dimension(250, 350));
		
		lbl_name = new JLabel(card_type);
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		lbl_name.setOpaque(true);
		lbl_name.setBackground(new Color(255, 153, 51));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_name.setForeground(Color.WHITE);						
		
		lbl_card_title = new JLabel("Card Title");
		lbl_card_title.setForeground(new Color(204, 102, 0));
		lbl_card_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_card_title.setFont(new Font("Tahoma", Font.BOLD, 15));		
		
		lbl_card_desc = new JTextArea("Card desc");
		lbl_card_desc.setWrapStyleWord(true);
		lbl_card_desc.setLineWrap(true);
		lbl_card_desc.setEditable(false);
		lbl_card_desc.setOpaque(false);
		lbl_card_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_card_desc.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));		
	}
	
	/**
	 * Sets the card
	 */
	public void setCard(EntropyCard card) {
		this.card = card;
		if ( card != null ) {
			this.populateFields();
		}
	}
	
	/**
	 * Populate the fields
	 */
	protected abstract void populateFields();
	
	/**
	 * Generates a card panel for given type
	 */
	public static EntropyCardPanel generateEntropyCardPanel(EntropyCard card) {
		if ( card instanceof BagOfTricksCard ) {
			if ( card instanceof BoTAutoSuccessCard ) {
				return new BoTAutoSuccessCardPanel((BoTAutoSuccessCard) card);
			} else if ( card instanceof BoTFreeRerollCard ) {
				return new BoTFreeRerollCardPanel((BoTFreeRerollCard) card);
			} else if ( card instanceof BoTSkillModCard ) {
				return new BoTSkillModCardPanel((BoTSkillModCard) card);
			} 
		}
		return null;
	}

}
