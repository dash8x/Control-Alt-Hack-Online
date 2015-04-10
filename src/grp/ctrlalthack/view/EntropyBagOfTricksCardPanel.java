/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;


import javax.swing.JPanel;

import java.awt.Color;


import javax.swing.JLabel;

import java.awt.Font;


import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public abstract class EntropyBagOfTricksCardPanel extends EntropyCardPanel {
	
	protected JLabel lbl_keep_in_play;
	protected JLabel lblCost;
	protected JLabel lbl_cost;			
	
	/**
	 * Create the panel.
	 */
	public EntropyBagOfTricksCardPanel() {
		
		super("BAG OF TRICKS");
		
		lbl_keep_in_play = new JLabel("Keep this card in play.");
		lbl_keep_in_play.setForeground(Color.BLACK);
		lbl_keep_in_play.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lbl_keep_in_play.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		
		lblCost = new JLabel("Cost:");
		lblCost.setForeground(new Color(153, 102, 0));
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCost.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		
		lbl_cost = new JLabel("$2000");
		lbl_cost.setForeground(new Color(153, 102, 0));
		lbl_cost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_cost.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
	}
	
	/**
	 * Populate the fields
	 */
	protected abstract void populateFields();


}
