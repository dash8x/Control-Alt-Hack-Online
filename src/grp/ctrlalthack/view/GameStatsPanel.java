/**
 * Displays overall game statistics
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.GameStats;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class GameStatsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7326593342846798511L;
	
	private GameStats stats;	
	private JLabel lbl_player;
	private JLabel lbl_hacker_creds;
	private JLabel lbl_cash;
	private JLabel lbl_round;
	private JLabel lbl_phase;	
	
	/**
	 * Create the panel.
	 */
	public GameStatsPanel(GameStats stats) {				
		
		setBorder(new LineBorder(new Color(255, 153, 51), 4, true));
		setBackground(new Color(255, 153, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCreds = new JLabel("Total Hacker Creds:");
		lblCreds.setOpaque(true);
		lblCreds.setForeground(new Color(0, 0, 0));
		lblCreds.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreds.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblCreds = new GridBagConstraints();
		gbc_lblCreds.fill = GridBagConstraints.BOTH;
		gbc_lblCreds.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreds.gridx = 0;
		gbc_lblCreds.gridy = 0;
		add(lblCreds, gbc_lblCreds);
		
		lbl_hacker_creds = new JLabel("");
		lbl_hacker_creds.setOpaque(true);
		lbl_hacker_creds.setForeground(Color.BLACK);
		lbl_hacker_creds.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hacker_creds.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_hacker_creds = new GridBagConstraints();
		gbc_lbl_hacker_creds.fill = GridBagConstraints.BOTH;
		gbc_lbl_hacker_creds.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_hacker_creds.gridx = 1;
		gbc_lbl_hacker_creds.gridy = 0;
		add(lbl_hacker_creds, gbc_lbl_hacker_creds);
		
		JLabel lblTotalCash = new JLabel("Total Cash:");
		lblTotalCash.setOpaque(true);
		lblTotalCash.setForeground(Color.BLACK);
		lblTotalCash.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalCash.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblTotalCash = new GridBagConstraints();
		gbc_lblTotalCash.fill = GridBagConstraints.BOTH;
		gbc_lblTotalCash.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCash.gridx = 0;
		gbc_lblTotalCash.gridy = 1;
		add(lblTotalCash, gbc_lblTotalCash);
		
		lbl_cash = new JLabel("");
		lbl_cash.setOpaque(true);
		lbl_cash.setForeground(Color.BLACK);
		lbl_cash.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_cash.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_cash = new GridBagConstraints();
		gbc_lbl_cash.fill = GridBagConstraints.BOTH;
		gbc_lbl_cash.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_cash.gridx = 1;
		gbc_lbl_cash.gridy = 1;
		add(lbl_cash, gbc_lbl_cash);
		
		JLabel lblCurrentRound = new JLabel("Current Round:");
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
		
		lbl_round = new JLabel("");
		lbl_round.setOpaque(true);
		lbl_round.setForeground(Color.BLACK);
		lbl_round.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_round.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_round = new GridBagConstraints();
		gbc_lbl_round.fill = GridBagConstraints.BOTH;
		gbc_lbl_round.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_round.gridx = 1;
		gbc_lbl_round.gridy = 2;
		add(lbl_round, gbc_lbl_round);
		
		JLabel lblCurrentPhase = new JLabel("Current Phase:");
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
		
		lbl_phase = new JLabel("");
		lbl_phase.setOpaque(true);
		lbl_phase.setForeground(Color.BLACK);
		lbl_phase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_phase.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_phase = new GridBagConstraints();
		gbc_lbl_phase.fill = GridBagConstraints.BOTH;
		gbc_lbl_phase.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_phase.gridx = 1;
		gbc_lbl_phase.gridy = 3;
		add(lbl_phase, gbc_lbl_phase);
		
		JLabel lblCurrentPlayer = new JLabel("Current Player:");
		lblCurrentPlayer.setOpaque(true);
		lblCurrentPlayer.setForeground(Color.BLACK);
		lblCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentPlayer.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.fill = GridBagConstraints.BOTH;
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 0, 5);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 4;
		add(lblCurrentPlayer, gbc_lblCurrentPlayer);
		
		lbl_player = new JLabel("");
		lbl_player.setOpaque(true);
		lbl_player.setForeground(Color.BLACK);
		lbl_player.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_player.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_player = new GridBagConstraints();
		gbc_lbl_player.fill = GridBagConstraints.BOTH;
		gbc_lbl_player.gridx = 1;
		gbc_lbl_player.gridy = 4;
		add(lbl_player, gbc_lbl_player);
		
		//fill in the values
		setStats(stats);
	}
	
	/**
	 * Populate the fields
	 */
	public void setStats(GameStats stats) {
		this.stats = stats;
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.stats != null ) {									
			this.lbl_hacker_creds.setText(Integer.toString(this.stats.getTotalCreds()));
			this.lbl_cash.setText("$"+this.stats.getTotalCash());
			this.lbl_round.setText(Integer.toString(this.stats.getCurrRound()));
			this.lbl_phase.setText(Integer.toString(this.stats.getCurrPhase()));
			this.lbl_player.setText(this.stats.getCurrPlayer());
		}
	}


}
