package br.com.avaliacao.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.avaliacao.model.Student;

/**
 * Classe contendo o controle da tabela de estudantes
 * @author Igor Muzeka
 * @version 1.0
 */
public class TableModelStudent extends AbstractTableModel {
	
	private List<Student> list;
	
	public static final int ID = 0;
	public static final int NAME = 1;
	private static final String[] COLUMNS = {"Código", "Nome"};
	
	public TableModelStudent(List<Student> list) {
		this.list = list;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case ID:
			return Integer.class;
		case NAME:
			return String.class;
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
		Student s = list.get(rowIndex);
		switch (columnIndex) {
		case ID:
			return s.getId();
		case NAME:
			return s.getName();
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
	
	public int indexOf(Student s) {
		return list.indexOf(s);
	}
	
	public void onAdd(Student s) {
		list.add(s);
		fireTableRowsInserted(indexOf(s), indexOf(s));
	}
	
	public void onUpdate(Student s) {
		fireTableRowsUpdated(indexOf(s), indexOf(s));
	}
	
	public void onRemove(int rowIndex) {
		list.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}
	
}
