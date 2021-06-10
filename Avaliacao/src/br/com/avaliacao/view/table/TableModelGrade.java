package br.com.avaliacao.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.avaliacao.model.Grade;

/**
 * Classe que contêm o controle da tabela de notas
 * @author Igor Muzeka
 * @version 1.0
 */
public class TableModelGrade extends AbstractTableModel {

	private List<Grade> list;
	
	public static final int NAME = 0;
	public static final int AVG = 1;
	public static final int STATUS = 2;
	public static final int COURSE = 3;
	private static final String[] COLUMNS = {"Nome", "Média", "Situação", "Disciplina"};
	
	public TableModelGrade(List<Grade> list) {
		this.list = list;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case NAME:
			return String.class;
		case AVG:
			return String.class;
		case STATUS:
			return String.class;
		case COURSE:
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
		Grade g = list.get(rowIndex);
		switch (columnIndex) {
		case NAME:
			return g.getStudent().getName();
		case AVG:
			return String.valueOf(g.getAvg()).replaceAll("\\.", ",");
		case STATUS:
			return g.getStatus();
		case COURSE:
			return g.getCourse().getName();
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
	
	public int indexOf(Grade g) {
		return list.indexOf(g);
	}
	
	public void onAdd(Grade g) {
		list.add(g);
		fireTableRowsInserted(indexOf(g), indexOf(g));
	}
	
	public void onUpdate(Grade g) {
		fireTableRowsUpdated(indexOf(g), indexOf(g));
	}
	
	public void onRemove(int rowIndex) {
		list.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public List<Grade> getList() {
		return list;
	}

	public void setList(List<Grade> list) {
		this.list = list;
	}
}
