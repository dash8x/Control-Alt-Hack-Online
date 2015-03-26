package grp.ctrlalthack.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class MainView extends JFrame {	

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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 30, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblControlA = new JLabel("Control-Alt-Hack Online");
		GridBagConstraints gbc_lblControlA = new GridBagConstraints();
		gbc_lblControlA.insets = new Insets(0, 0, 5, 5);
		gbc_lblControlA.gridx = 1;
		gbc_lblControlA.gridy = 1;
		getContentPane().add(lblControlA, gbc_lblControlA);
		
		JButton btn_start_server = new JButton("Start New Server");
		GridBagConstraints gbc_btn_start_server = new GridBagConstraints();
		gbc_btn_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_start_server.gridx = 1;
		gbc_btn_start_server.gridy = 3;
		getContentPane().add(btn_start_server, gbc_btn_start_server);
		
		JButton btn_join_server = new JButton("Join Server");
		GridBagConstraints gbc_btn_join_server = new GridBagConstraints();
		gbc_btn_join_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_join_server.gridx = 1;
		gbc_btn_join_server.gridy = 4;
		getContentPane().add(btn_join_server, gbc_btn_join_server);
	}

}
