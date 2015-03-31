package grp.ctrlalthack.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JFrame implements ViewConstants {	

	private JPanel cards;

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
		
		cards = new JPanel();
		getContentPane().add(cards, "name_1322188755735631");
		cards.setLayout(new CardLayout(0, 0));
		
		JPanel home_panel = new HomePanel(this.cards);
		cards.add(home_panel, HOME_PANEL);				
		
		JPanel start_server_panel = new StartServerPanel(this.cards);
		cards.add(start_server_panel, START_SERVER_PANEL);
	}

}
