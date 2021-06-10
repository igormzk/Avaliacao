package br.com.avaliacao.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.avaliacao.model.Course;

/**
 * Classe que contêm o controle da tabela de disciplinas
 * @author Igor Muzeka
 * @version 1.0
 */
public class TableModelCourse extends AbstractTableModel {
	
	private List<Course> list;
	
	public static final int ID = 0;
	public static final int NAME = 1;
	public static final int CREDIT = 2;
	private static final String[] COLUMNS = {"Código", "Nome", "Crédito"};
	
	public TableModelCourse(List<Course> list) {
		this.list = list;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case ID:
			return Integer.class;
		case NAME:
			return String.class;
		case CREDIT:
			return Integer.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida");
		}
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (list.isEmpty()) return null;
		Course c = list.get(rowIndex);
		switch (columnIndex) {
		case ID:
			return c.getId();
		case NAME:
			return c.getName();
		case CREDIT:
			return c.getCredit();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida");
		}
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public int indexOf(Course s) {
		return list.indexOf(s);
	}
	
	public void onAdd(Course s) {
		list.add(s);
		fireTableRowsInserted(indexOf(s), indexOf(s));
	}
	
	public void onUpdate(Course s) {
		fireTableRowsUpdated(indexOf(s), indexOf(s));
	}
	
	public void onRemove(int rowIndex) {
		list.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public List<Course> getList() {
		return list;
	}

	public void setList(List<Course> list) {
		this.list = list;
	}
	
}
