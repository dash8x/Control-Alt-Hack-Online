/**
 * Main Home Panel
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HomePanel extends CardPanel implements ViewConstants {	
	
	/**
	 * Create the panel.
	 */
	public HomePanel(JPanel cards) {
		super(cards);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Control-Alt-Hack Online");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btn_start_server = new JButton("Start Server");
		GridBagConstraints gbc_btn_start_server = new GridBagConstraints();
		gbc_btn_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_start_server.gridx = 1;
		gbc_btn_start_server.gridy = 3;
		add(btn_start_server, gbc_btn_start_server);
		
		btn_start_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigateTo(START_SERVER_PANEL);
			}
		});
		
		
		JButton btn_join_server = new JButton("Join Server");
		GridBagConstraints gbc_btn_join_server = new GridBagConstraints();
		gbc_btn_join_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_join_server.gridx = 1;
		gbc_btn_join_server.gridy = 4;
		add(btn_join_server, gbc_btn_join_server);

	}

}
