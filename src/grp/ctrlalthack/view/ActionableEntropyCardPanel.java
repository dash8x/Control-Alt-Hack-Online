/**
 * Displays actionable entropy card
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.entropy.BagOfTricksCard;
import grp.ctrlalthack.model.entropy.EntropyCard;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ActionableEntropyCardPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3199508874385217762L;
	
	private EntropyCard card;	
	private int id;
	private EntropyCardPanel card_panel;
	private JButton btn_action;
	private CardParent parent;		
	
	/**
	 * Constructor
	 */
	public ActionableEntropyCardPanel(int id, EntropyCard card) {	
		this(null, id, card);
	}
	
	/**
	 * Create the panel.
	 */
	public ActionableEntropyCardPanel(CardParent parent, int id, EntropyCard card) {				
		this.parent = parent;
		this.id = id;
		this.card = card;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		card_panel = EntropyCardPanel.generateEntropyCardPanel(this.card);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(card_panel, gbc_panel);
		
		btn_action = new JButton("USE");		
		btn_action.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_action.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				useCard();
			}
		});
		GridBagConstraints gbc_btn_action = new GridBagConstraints();
		gbc_btn_action.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_action.gridx = 0;
		gbc_btn_action.gridy = 1;
		add(btn_action, gbc_btn_action);
		
		populateFields();
	}
	
	/**
	 * Populate fields
	 */
	public void populateFields() {
		if ( this.parent == null ) {
			this.btn_action.setEnabled(false);
		} else {
			this.btn_action.setEnabled(true);
		}
	}
	
	/**
	 * Use the card
	 */
	protected void useCard() {
		if ( this.card != null && this.parent != null ) {
			if ( this.card instanceof BagOfTricksCard ) {
				if (this.parent.buyBagOfTricks(getID())) {
					this.card_panel.setVisible(false);
					this.btn_action.setEnabled(false);
				}
			}
		}
	}


	/**
	 * Returns the id
	 */
	public int getID() {
		return this.id;
	}
	

}
