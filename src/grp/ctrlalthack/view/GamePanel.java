/**
 * Server lobby panel
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;


import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;






import grp.ctrlalthack.model.Player;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;



import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class GamePanel extends CardPanel implements ViewConstants {
//public class GamePanel extends JPanel implements ViewConstants {
	
	//list of cards and buttons
	private JTable tbl_players;
	private PlayMissionPanel play_mission_panel;
	private PlayerDetailsPanel player_details_panel;
	private GameStatsPanel game_stats_panel;
	private JButton btn_your_mission;
	private JButton btn_your_entropy_on_hand;
	private JButton btn_your_entropy_in_play;
	private JButton btn_your_character;
	private JLabel lblGameLog;
	private JScrollPane scrollPane_1;
	private JTextPane txt_game_log;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ArrayList<HackerCard> cards = DataIO.readHackerCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new GamePanel());
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
	 * Create the panel.
	 */
	//public GamePanel() {
	public GamePanel(CardParent cards) {		
		super(cards);		
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setBorder(new EmptyBorder(5,5,5,5));
		lblPlayers.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPlayers = new GridBagConstraints();
		gbc_lblPlayers.gridwidth = 2;
		gbc_lblPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayers.gridx = 0;
		gbc_lblPlayers.gridy = 0;
		add(lblPlayers, gbc_lblPlayers);
		
		play_mission_panel = new PlayMissionPanel(null);
		GridBagConstraints gbc_play_mission_panel = new GridBagConstraints();
		gbc_play_mission_panel.gridheight = 7;
		gbc_play_mission_panel.insets = new Insets(0, 0, 0, 5);
		gbc_play_mission_panel.fill = GridBagConstraints.BOTH;
		gbc_play_mission_panel.gridx = 2;
		gbc_play_mission_panel.gridy = 0;
		add(play_mission_panel, gbc_play_mission_panel);
		
		game_stats_panel = new GameStatsPanel(null);
		GridBagConstraints gbc_game_stats_panel = new GridBagConstraints();
		gbc_game_stats_panel.gridheight = 2;
		gbc_game_stats_panel.insets = new Insets(0, 0, 5, 0);
		gbc_game_stats_panel.fill = GridBagConstraints.BOTH;
		gbc_game_stats_panel.gridx = 3;
		gbc_game_stats_panel.gridy = 0;
		add(game_stats_panel, gbc_game_stats_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		tbl_players = new JTable();
		tbl_players.setModel(new PlayersTableModel());
		//show details when player row clicked
		tbl_players.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 1) {
					showSelectedPlayer();
				}
			}
		});
		scrollPane.setViewportView(tbl_players);
		
		player_details_panel = new PlayerDetailsPanel(null);
		GridBagConstraints gbc_player_details_panel = new GridBagConstraints();
		gbc_player_details_panel.gridheight = 5;
		gbc_player_details_panel.fill = GridBagConstraints.BOTH;
		gbc_player_details_panel.gridx = 3;
		gbc_player_details_panel.gridy = 2;
		add(player_details_panel, gbc_player_details_panel);
		
		lblGameLog = new JLabel("Game Log");
		lblGameLog.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGameLog.setBorder(new EmptyBorder(5,5,5,5));
		GridBagConstraints gbc_lblGameLog = new GridBagConstraints();
		gbc_lblGameLog.gridwidth = 2;
		gbc_lblGameLog.insets = new Insets(0, 0, 5, 5);
		gbc_lblGameLog.gridx = 0;
		gbc_lblGameLog.gridy = 3;
		add(lblGameLog, gbc_lblGameLog);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		txt_game_log = new JTextPane();
		txt_game_log.setEditable(false);
		scrollPane_1.setViewportView(txt_game_log);
		
		btn_your_character = new JButton("Your Character");
		GridBagConstraints gbc_btn_your_character = new GridBagConstraints();
		gbc_btn_your_character.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_your_character.insets = new Insets(0, 0, 5, 5);
		gbc_btn_your_character.gridx = 0;
		gbc_btn_your_character.gridy = 5;
		add(btn_your_character, gbc_btn_your_character);
		
		btn_your_mission = new JButton("Your Mission");
		GridBagConstraints gbc_btn_your_mission = new GridBagConstraints();
		gbc_btn_your_mission.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_your_mission.insets = new Insets(0, 0, 5, 5);
		gbc_btn_your_mission.gridx = 1;
		gbc_btn_your_mission.gridy = 5;
		add(btn_your_mission, gbc_btn_your_mission);
		
		btn_your_entropy_on_hand = new JButton("Entropy On-hand");
		GridBagConstraints gbc_btn_your_entropy_on_hand = new GridBagConstraints();
		gbc_btn_your_entropy_on_hand.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_your_entropy_on_hand.insets = new Insets(0, 0, 0, 5);
		gbc_btn_your_entropy_on_hand.gridx = 0;
		gbc_btn_your_entropy_on_hand.gridy = 6;
		add(btn_your_entropy_on_hand, gbc_btn_your_entropy_on_hand);
		
		btn_your_entropy_in_play = new JButton("Entropy in Play");
		GridBagConstraints gbc_btn_your_entropy_in_play = new GridBagConstraints();
		gbc_btn_your_entropy_in_play.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_your_entropy_in_play.insets = new Insets(0, 0, 0, 5);
		gbc_btn_your_entropy_in_play.gridx = 1;
		gbc_btn_your_entropy_in_play.gridy = 6;
		add(btn_your_entropy_in_play, gbc_btn_your_entropy_in_play);
		
		//populate fields
		populateFields();
				
	}
		
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		//TODO
	}
	
	/**
	 * Updates status
	 */
	public void updateStatus(String msg) {
		this.play_mission_panel.displayStatusMessage(msg);;
	}
	
	/**
	 * Updates roll msg
	 */
	public void updateRollMessage(String msg) {
		this.play_mission_panel.displayRollMessage(msg);;
	}
	
	/**
	 * Updates the players table
	 */
	public void updatePlayers(ArrayList<Player> players) {
		this.tbl_players.setModel(new PlayersTableModel(players));
	}

	
	/**
	 * Updates the game stats
	 */
	public void updateGameStats(GameStats stats) {
		this.game_stats_panel.setStats(stats);
	}
	
	/**
	 * Show details for selected player
	 */
	private void showSelectedPlayer() {
		if ( tbl_players.getSelectedRow() > - 1 ) {
			//get the selected student details
			Player player = ((PlayersTableModel)tbl_players.getModel()).getRow(tbl_players.getSelectedRow());
			this.player_details_panel.setPlayer(player);	
			this.player_details_panel.setVisible(true);
		} else {
			this.player_details_panel.setVisible(false);
		}
	}
	
	/**
	 * Method to show a log message of given type
	 * @param message to display
	 */
	public void displayLog(String message, String message_type) {
		Color text_color;
		switch(message_type) {			
			case "DEFAULT":
			default:
				text_color = Color.BLACK;
		}
		this.txt_game_log.setEditable(true); //temporarily enable editing
		this.prependToPane(this.txt_game_log, message + "\n", text_color, false);
		this.txt_game_log.setEditable(false);
	}
	
	/**
	 * Appends text to textpane with a given color and boldness
	 * Reference http://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
	 * @param tp the textpane
	 * @param msg the message
	 * @param c the color of the text
	 */
	 private void prependToPane(JTextPane tp, String msg, Color c, boolean bold) {
		 StyleContext sc = StyleContext.getDefaultStyleContext();
	     AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

	     aset = sc.addAttribute(aset, StyleConstants.FontFamily, Font.MONOSPACED); //setting font causes hang
	     aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);	     
	     aset = sc.addAttribute(aset, StyleConstants.Bold, bold);	     
	     	     	   
	     //int len = tp.getDocument().getLength();
	     tp.setCaretPosition(0);
	     tp.setCharacterAttributes(aset, false);
	     tp.replaceSelection(msg);	     
	}
}
