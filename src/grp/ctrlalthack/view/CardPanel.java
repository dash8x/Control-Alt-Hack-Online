/**
 * Abstract class for card panels
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */


package grp.ctrlalthack.view;

import grp.ctrlalthack.net.ClientService;
import grp.ctrlalthack.net.Server;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public abstract class CardPanel extends JPanel {
	
	private CardParent cards;

	/**
	 * Constructor
	 */
	public CardPanel(CardParent cards) {
		super();
		this.cards = cards;
	}
	
	/**
	 * Get cards
	 */
	public CardParent getParent() {
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
		this.getParent().navigateTo(panel_name);;
	}
	
	/**
	 * Returns current instance
	 */
	public CardPanel getInstance() {
		return this;
	}
	
	/**
	 * @return the server
	 */
	public Server getServer() {
		return this.getParent().getServer();
	}

	/**
	 * @return the client
	 */
	public ClientService getClient() {
		return this.getParent().getClient();
	}	
	
	/**
	 * Stops the client
	 */
	public void stopClient() {
		this.getParent().stopClient();
	}
	
	/**
	 * Stops the server
	 */
	public void stopServer() {
		this.getParent().stopServer();
	}
		
	/**
	 * Shows error
	 */
	public void showError(String msg) {
		this.getParent().showError(msg);
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
}
