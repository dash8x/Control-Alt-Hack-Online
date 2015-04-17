/**
 * Custom AbstractTableModel for displaying player clients
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.Player;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ClientsTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4903899538620328103L;

	//stores the courses
	private ArrayList<Player> players;
	
	//the column titles
	private static final String[] COLUMNS = new String[]{"Player IP", "Player Name","Player Status"};
	
	/**
	 * Constructor
	 */
	public ClientsTableModel() {
		this(new ArrayList<Player>());
	}
	
	/**
	 * Constructor
	 */
	public ClientsTableModel( ArrayList<Player> players ) {
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
		Player player = this.players.get(row);
		//return the corresponding field
		switch(col) {
			case 0:
				return player.getPlayerIP();
			case 1:
				return player.getPlayerName();
			case 2:
				return (player.isReadyToStart()) ? "READY" : "WAITING";			
		}
		return null;
	}
		
	/**
	 * Returns the name of a given column
	 */
	@Override
	public String getColumnName(int col) {
	   return COLUMNS[col];
	}

}
