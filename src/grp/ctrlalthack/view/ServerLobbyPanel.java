/**
 * Server lobby panel
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.Player;


import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ServerLobbyPanel extends CardPanel implements ViewConstants {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716216701135013734L;
	
	private JButton btn_start_game;
	private JButton btn_cancel_start_game;	
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public ServerLobbyPanel(CardParent cards) {		
		super(cards);	
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 30, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lbl_panel_title = new JLabel("Server Lobby");
		lbl_panel_title.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblStartServer = new GridBagConstraints();
		gbc_lblStartServer.gridwidth = 3;
		gbc_lblStartServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartServer.gridx = 2;
		gbc_lblStartServer.gridy = 1;
		add(lbl_panel_title, gbc_lblStartServer);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(new ClientsTableModel());
		scrollPane.setViewportView(table);
		
		btn_start_game = new JButton("Ready");
		btn_start_game.setFont(new Font("Tahoma", Font.PLAIN, 13));
		if ( getParent().inServerMode() ) {
			//start button for server host
			btn_start_game.setText("Start Game");
		}
		GridBagConstraints gbc_btn_start_game = new GridBagConstraints();
		gbc_btn_start_game.anchor = GridBagConstraints.WEST;
		gbc_btn_start_game.insets = new Insets(0, 0, 5, 5);
		gbc_btn_start_game.gridx = 2;
		gbc_btn_start_game.gridy = 5;
		add(btn_start_game, gbc_btn_start_game);
		
		btn_cancel_start_game = new JButton("Cancel");
		btn_cancel_start_game.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_cancel_start_game = new GridBagConstraints();
		gbc_btn_cancel_start_game.anchor = GridBagConstraints.WEST;
		gbc_btn_cancel_start_game.insets = new Insets(0, 0, 5, 5);
		gbc_btn_cancel_start_game.gridx = 3;
		gbc_btn_cancel_start_game.gridy = 5;
		add(btn_cancel_start_game, gbc_btn_cancel_start_game);
		
		btn_cancel_start_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doCancel();
			}
		});
		
		btn_start_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doStartGame();
			}			
		});
				
	}
	
	/**
	 * Start game button handler
	 */
	private void doStartGame() {				
		if ( !getParent().inServerMode() ) {
			getParent().readyToStart();
			//disable the ready button
			this.btn_start_game.setEnabled(false);
		} else {
			getParent().startGame();
		}
	}
	
	/**
	 * Cancel
	 */
	private void doCancel() {
		stopClient();
		stopServer();
		navigateTo(HOME_PANEL);
		//getParentLayout().removeLayoutComponent(getInstance());
		//TODO
	}
	
	/**
	 * Updates the clients table
	 */
	public void updatePlayers(ArrayList<Player> players) {
		this.table.setModel(new ClientsTableModel(players));
	}

}
