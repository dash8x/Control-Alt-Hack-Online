/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.Player;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PlayerDetailsPanel extends JPanel {
	
	private Player player;	
	private JLabel lbl_hacker_creds;
	private JLabel lbl_cash;
	private JButton btn_view_entropy_in_play;
	private JButton btn_view_mission;
	private HackerCardPanel character_panel;
	private JLabel lbl_player_name;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ArrayList<BoTAutoSuccessCard> cards = DataIO.readBoTAutoSuccessCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new PlayerDetailsPanel(null));					
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the panel.
	 */
	public PlayerDetailsPanel(Player player) {				
		
		setBorder(new LineBorder(new Color(51, 204, 51), 4, true));
		setBackground(new Color(51, 204, 51));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbl_player_name = new JLabel("Player Name");
		lbl_player_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_player_name.setOpaque(true);
		lbl_player_name.setForeground(new Color(0, 0, 0));
		lbl_player_name.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_player_name.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_player_name = new GridBagConstraints();
		gbc_lbl_player_name.gridwidth = 3;
		gbc_lbl_player_name.fill = GridBagConstraints.BOTH;
		gbc_lbl_player_name.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_player_name.gridx = 0;
		gbc_lbl_player_name.gridy = 0;
		add(lbl_player_name, gbc_lbl_player_name);
		
		character_panel = new HackerCardPanel();
		GridBagConstraints gbc_character_panel = new GridBagConstraints();
		gbc_character_panel.gridwidth = 3;
		gbc_character_panel.insets = new Insets(0, 0, 5, 0);
		gbc_character_panel.fill = GridBagConstraints.BOTH;
		gbc_character_panel.gridx = 0;
		gbc_character_panel.gridy = 1;
		add(character_panel, gbc_character_panel);
		
		JLabel lblCurrentRound = new JLabel("Hacker Creds:");
		lblCurrentRound.setOpaque(true);
		lblCurrentRound.setForeground(Color.BLACK);
		lblCurrentRound.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentRound.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblCurrentRound = new GridBagConstraints();
		gbc_lblCurrentRound.fill = GridBagConstraints.BOTH;
		gbc_lblCurrentRound.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentRound.gridx = 0;
		gbc_lblCurrentRound.gridy = 2;
		add(lblCurrentRound, gbc_lblCurrentRound);
		
		lbl_hacker_creds = new JLabel("Hacker Creds");
		lbl_hacker_creds.setOpaque(true);
		lbl_hacker_creds.setForeground(Color.BLACK);
		lbl_hacker_creds.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hacker_creds.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_hacker_creds = new GridBagConstraints();
		gbc_lbl_hacker_creds.gridwidth = 2;
		gbc_lbl_hacker_creds.fill = GridBagConstraints.BOTH;
		gbc_lbl_hacker_creds.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_hacker_creds.gridx = 1;
		gbc_lbl_hacker_creds.gridy = 2;
		add(lbl_hacker_creds, gbc_lbl_hacker_creds);
		
		JLabel lblCurrentPhase = new JLabel("Cash:");
		lblCurrentPhase.setOpaque(true);
		lblCurrentPhase.setForeground(Color.BLACK);
		lblCurrentPhase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentPhase.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblCurrentPhase = new GridBagConstraints();
		gbc_lblCurrentPhase.fill = GridBagConstraints.BOTH;
		gbc_lblCurrentPhase.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPhase.gridx = 0;
		gbc_lblCurrentPhase.gridy = 3;
		add(lblCurrentPhase, gbc_lblCurrentPhase);
		
		lbl_cash = new JLabel("Cash");
		lbl_cash.setOpaque(true);
		lbl_cash.setForeground(Color.BLACK);
		lbl_cash.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_cash.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_cash = new GridBagConstraints();
		gbc_lbl_cash.gridwidth = 2;
		gbc_lbl_cash.fill = GridBagConstraints.BOTH;
		gbc_lbl_cash.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_cash.gridx = 1;
		gbc_lbl_cash.gridy = 3;
		add(lbl_cash, gbc_lbl_cash);
		
		btn_view_mission = new JButton("View Mission");
		btn_view_mission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewMission();
			}
		});
		GridBagConstraints gbc_btn_view_mission = new GridBagConstraints();
		gbc_btn_view_mission.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_view_mission.anchor = GridBagConstraints.SOUTH;
		gbc_btn_view_mission.insets = new Insets(0, 0, 0, 5);
		gbc_btn_view_mission.gridx = 0;
		gbc_btn_view_mission.gridy = 4;
		add(btn_view_mission, gbc_btn_view_mission);
		
		btn_view_entropy_in_play = new JButton("Entropy Cards in Play");
		btn_view_entropy_in_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewEntropyInPlay();
			}
		});
		GridBagConstraints gbc_btn_view_entropy_in_play = new GridBagConstraints();
		gbc_btn_view_entropy_in_play.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_view_entropy_in_play.insets = new Insets(0, 0, 0, 5);
		gbc_btn_view_entropy_in_play.gridx = 1;
		gbc_btn_view_entropy_in_play.gridy = 4;
		add(btn_view_entropy_in_play, gbc_btn_view_entropy_in_play);
		
		//fill in the values
		setPlayer(player);
	}
	
	/**
	 * View the mission
	 */
	private void viewMission() {
		if ( this.player != null ) {
			JDialog mission_dialog = new JDialog(null, "Mission", JDialog.DEFAULT_MODALITY_TYPE);
			mission_dialog.add(new MissionCardPanel(player.getMission()));		
			mission_dialog.setSize(new Dimension(400,600));
			mission_dialog.setVisible(true);
		}
	}
	
	/**
	 * View in play
	 */
	private void viewEntropyInPlay() {
		if ( this.player != null ) {
			if ( player.getEntropyCardsInPlay() == null || player.getEntropyCardsInPlay().size() == 0 ) {
				JOptionPane.showMessageDialog(null, this.player.getPlayerName() + " has no entropy cards in play", "No cards", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JDialog entropy_in_play_dialog = new JDialog(null, "Entropy Cards in Play", JDialog.DEFAULT_MODALITY_TYPE);
				entropy_in_play_dialog.add(new ListEntropyCardsPanel(player.getEntropyCardsInPlay()));		
				entropy_in_play_dialog.setSize(new Dimension(400,600));
				entropy_in_play_dialog.setVisible(true);
			}
		}
	}

	/**
	 * Populate the fields
	 */
	public void setPlayer(Player player) {
		this.player = player;
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.player != null ) {									
			lbl_player_name.setText(player.getPlayerName());
			lbl_hacker_creds.setText(Integer.toString(player.getHackerCreds()));
			lbl_cash.setText("$" + player.getCash());
			character_panel.setCard(player.getCharacter());
			btn_view_entropy_in_play.setEnabled(true);
			btn_view_mission.setEnabled(true);
		} else {
			lbl_player_name.setText("No player selected");
			lbl_hacker_creds.setText("");
			lbl_cash.setText("");
			btn_view_entropy_in_play.setEnabled(false);
			btn_view_mission.setEnabled(false);
		}
	}


}
