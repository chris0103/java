package app.adapter;

import javax.swing.table.AbstractTableModel;

import com.oozinoz.firework.Rocket;

public class RocketTableModel extends AbstractTableModel {
	
	protected Rocket[] rockets;
	protected String[] columnNames = new String[] {"Name", "Price", "Apogee"};

	public RocketTableModel(Rocket[] rockets) {
		this.rockets = rockets;
	}
	
	public String getColumnName(int i) {
		return columnNames[i];
	}
	
	public int getRowCount() {
		return rockets.length;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Rocket rocket = rockets[rowIndex];
		if (columnIndex == 0) {
			return rocket.getName();
		} else if (columnIndex == 1) {
			return rocket.getPrice();
		} else if (columnIndex == 2) {
			return rocket.getApogee();
		}
		return null;
	}
}
