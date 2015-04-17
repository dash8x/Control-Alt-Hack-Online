/**
 * Main class of the application
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */


package grp.ctrlalthack.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;

public class MainView extends JFrame {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 544954603755763496L;
	
	private CardParent cards;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {					
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		cards = new CardParent();
		getContentPane().add(cards, "name_1322188755735631");		
	}

}
