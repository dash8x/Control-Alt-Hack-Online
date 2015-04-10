/**
 * Server lobby panel
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;


import grp.ctrlalthack.model.HackerCard;


import java.awt.GridBagLayout;

import javax.swing.JLabel;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


import java.awt.Color;
import java.awt.Font;

public class ChooseCharacterPanel extends CardPanel implements ViewConstants {
//public class ChooseCharacterPanel extends JPanel implements ViewConstants {
	
	private JButton btn_cancel_game;	
	
	private HackerCardPanel hacker_card_1;
	private HackerCardPanel hacker_card_3;
	private HackerCardPanel hacker_card_2;
	private JButton btn_choose_1;
	private JButton btn_choose_3;
	private JButton btn_choose_2;
	
	//list of cards and buttons
	private HackerCard[] hacker_cards;
	private ArrayList<JButton> choose_buttons = new ArrayList<JButton>();
	private ArrayList<HackerCardPanel> hacker_panels = new ArrayList<HackerCardPanel>();
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<HackerCard> cards = DataIO.readHackerCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new ChooseCharacterPanel(cards));
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
	//public ChooseCharacterPanel(CardParent cards) {
	public ChooseCharacterPanel(CardParent cards, HackerCard[] hacker_cards) {		
		super(cards);		
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 10, 0, 0, 30, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblStartServer = new JLabel("Choose Character");
		lblStartServer.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblStartServer = new GridBagConstraints();
		gbc_lblStartServer.gridwidth = 3;
		gbc_lblStartServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartServer.gridx = 1;
		gbc_lblStartServer.gridy = 1;
		add(lblStartServer, gbc_lblStartServer);
		
		hacker_card_1 = new HackerCardPanel();
		GridBagConstraints gbc_hacker_card_1 = new GridBagConstraints();
		gbc_hacker_card_1.insets = new Insets(0, 0, 5, 5);
		gbc_hacker_card_1.fill = GridBagConstraints.BOTH;
		gbc_hacker_card_1.gridx = 1;
		gbc_hacker_card_1.gridy = 3;
		add(hacker_card_1, gbc_hacker_card_1);
		
		hacker_card_3 = new HackerCardPanel();
		GridBagConstraints gbc_hacker_card_3 = new GridBagConstraints();
		gbc_hacker_card_3.insets = new Insets(0, 0, 5, 5);
		gbc_hacker_card_3.fill = GridBagConstraints.BOTH;
		gbc_hacker_card_3.gridx = 2;
		gbc_hacker_card_3.gridy = 3;
		add(hacker_card_3, gbc_hacker_card_3);
		
		hacker_card_2 = new HackerCardPanel();
		GridBagConstraints gbc_hacker_card_2 = new GridBagConstraints();
		gbc_hacker_card_2.insets = new Insets(0, 0, 5, 5);
		gbc_hacker_card_2.fill = GridBagConstraints.BOTH;
		gbc_hacker_card_2.gridx = 3;
		gbc_hacker_card_2.gridy = 3;
		add(hacker_card_2, gbc_hacker_card_2);
		
		btn_choose_1 = new JButton("Choose");
		btn_choose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseCard(0);
			}
		});
		btn_choose_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_choose_1 = new GridBagConstraints();
		gbc_btn_choose_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_choose_1.insets = new Insets(0, 0, 5, 5);
		gbc_btn_choose_1.gridx = 1;
		gbc_btn_choose_1.gridy = 4;
		add(btn_choose_1, gbc_btn_choose_1);
		
		btn_choose_3 = new JButton("Choose");
		btn_choose_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseCard(2);
			}
		});
		btn_choose_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_choose_3 = new GridBagConstraints();
		gbc_btn_choose_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_choose_3.insets = new Insets(0, 0, 5, 5);
		gbc_btn_choose_3.gridx = 2;
		gbc_btn_choose_3.gridy = 4;
		add(btn_choose_3, gbc_btn_choose_3);
		
		btn_choose_2 = new JButton("Choose");
		btn_choose_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseCard(1);
			}
		});
		btn_choose_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_choose_2 = new GridBagConstraints();
		gbc_btn_choose_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_choose_2.insets = new Insets(0, 0, 5, 5);
		gbc_btn_choose_2.gridx = 3;
		gbc_btn_choose_2.gridy = 4;
		add(btn_choose_2, gbc_btn_choose_2);
		
		btn_cancel_game = new JButton("Cancel");
		GridBagConstraints gbc_btn_cancel_game = new GridBagConstraints();
		gbc_btn_cancel_game.anchor = GridBagConstraints.WEST;
		gbc_btn_cancel_game.insets = new Insets(0, 0, 5, 5);
		gbc_btn_cancel_game.gridx = 1;
		gbc_btn_cancel_game.gridy = 6;
		add(btn_cancel_game, gbc_btn_cancel_game);
		
		btn_cancel_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});
		
		//add to list
		hacker_panels.add(hacker_card_1);
		hacker_panels.add(hacker_card_2);
		hacker_panels.add(hacker_card_3);
		
		choose_buttons.add(btn_choose_1);
		choose_buttons.add(btn_choose_2);
		choose_buttons.add(btn_choose_3);
		
		this.hacker_cards = hacker_cards;
		
		//populate fields
		populateFields();
				
	}
	
	/**
	 * Choose the card
	 */
	private void chooseCard(int card_id) {
		
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.hacker_cards != null ) {
			for (int i = 0; i < this.hacker_panels.size(); i++) {
				HackerCardPanel hp = this.hacker_panels.get(i);
				JButton bt = this.choose_buttons.get(i);
				try {
					hp.setCard(this.hacker_cards[i]);
					bt.setEnabled(true);
					bt.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException e) {
					bt.setEnabled(false);
					bt.setVisible(false);
					hp.setVisible(true);
				}
			}
		}
	}
		

}
