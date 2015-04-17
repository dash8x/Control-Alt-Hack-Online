/**
 * Displays a list of Entropy Card panels
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.entropy.EntropyCard;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class ListEntropyCardsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928018893052298921L;
	
	private ArrayList<EntropyCard> cards;
	private CardParent parent;

	private JPanel body;	
	
	/**
	 * Create the panel.
	 */
	public ListEntropyCardsPanel(ArrayList<EntropyCard> cards) {
		this(null, cards);
	}
	
	/**
	 * Create the panel.
	 */
	public ListEntropyCardsPanel(CardParent parent, ArrayList<EntropyCard> cards) {				
		this.parent = parent;
		this.cards = cards;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(204, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		body = new JPanel();
		body.setOpaque(false);
		scrollPane.setViewportView(body);	
		body.setLayout(new GridLayout(0, 2, 5, 5));
		
		populateFields();
	}
		
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.cards != null ) {														
			for (int i = 0; i < this.cards.size(); i++) {				
				ActionableEntropyCardPanel panel = new ActionableEntropyCardPanel(this.parent, i, cards.get(i));			
		        body.add(panel);
		    }
		}
	}


}
