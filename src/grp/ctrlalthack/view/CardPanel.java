/**
 * Abstract class for card panels
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */


package grp.ctrlalthack.view;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public abstract class CardPanel extends JPanel {
	
	private JPanel cards;
	
	/**
	 * Constructor
	 */
	public CardPanel(JPanel cards) {
		super();
		this.cards = cards;
	}
	
	/**
	 * Get cards
	 */
	public JPanel getParent() {
		return this.cards;
	}
	
	/**
	 * Get cards layout
	 */
	public CardLayout getParentLayout() {
		return (CardLayout) this.cards.getLayout();
	}
	
	/**
	 * Navigate to a given panel
	 */
	public void navigateTo(String panel_name) {
		CardLayout cl = this.getParentLayout();
        cl.show(this.getParent(), panel_name);
	}
	
	/**
	 * Returns current instance
	 */
	public CardPanel getInstance() {
		return this;
	}
	
	/**
	 * Method to get int from field
	 */
	public static int getTextInt(JTextComponent field) {
		String string_val = field.getText();		
		try {
			return Integer.parseInt(string_val);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Shows error
	 */
	public void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}
}
