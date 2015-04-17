/**
 * Custom AbstractTableModel for displaying players
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.Player;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PlayersTableModel extends AbstractTableModel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8228285472818261150L;

	//stores the courses
	private ArrayList<Player> players;
	
	//the column titles
	private static final String[] COLUMNS = new String[]{"#", "Name","Creds", "Cash", "Attending"};
	
	/**
	 * Constructor
	 */
	public PlayersTableModel() {
		this(new ArrayList<Player>());
	}
	
	/**
	 * Constructor
	 */
	public PlayersTableModel( ArrayList<Player> players ) {
		if (players == null) {
			players = new  ArrayList<Player>();
		} 
		this.players = players;
	}
	
	/**
	 * Updates the list of courses
	 */
	public void updateData( ArrayList<Player> players ) {
		this.players = players;
	}
	
	/**
	 * Returns the number of columns in the table
	 */
	@Override	
	public int getColumnCount() {
		return COLUMNS.length;
	}
		
	/**
	 * Returns the number of rows in the table
	 */
	@Override
	public int getRowCount() {		
		return this.players.size();
	}

	/**
	 * Returns the value at a given cell
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Player player = getRow(row);
		//return the corresponding field
		switch(col) {
			case 0:
				return row + 1;
			case 1:
				return player.getPlayerName();
			case 2:
				return player.getHackerCreds();
			case 3:
				return "$" + player.getCash();
			case 4:
				return (player.isAttending()) ? "YES" : "NO";
		}
		return null;
	}
		
	/**
	 * Returns the row
	 */
	public Player getRow(int row) {
		return this.players.get(row);		
	}
	
	/**
	 * Returns the name of a given column
	 */
	@Override
	public String getColumnName(int col) {
	   return COLUMNS[col];
	}

}
