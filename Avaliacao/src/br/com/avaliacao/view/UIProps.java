package br.com.avaliacao.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 * Classe para configurar as telas do sistema
 * @author Igor Muzeka
 * @version 1.0
 */
public class UIProps {
	
	private static final Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
	
	/**
	 * M�todo para atribuir uma fonte padr�o para todos os
	 * componentes utilizado neste projeto
	 * @param pnl - JPanel que cont�m os componentes a serem padronizados
	 */
	public static void setFont(JPanel pnl) {
		for (Component c : pnl.getComponents()) {
			if (c == null) continue;
			if (c instanceof JLabel) c.setFont(font);
			else if (c instanceof JTextField) c.setFont(font);
			else if (c instanceof JButton) c.setFont(font);
			else if (c instanceof JComboBox) c.setFont(font);
		}
	}
	
	/**
	 * M�todo que retira as informa��es preenchidas por um usu�rio
	 * @param pnl - JPanel que cont�m os componentes
	 */
	public static void clean(JPanel pnl) {
		for (Component c : pnl.getComponents()) {
			if (c == null) continue;
			if (c instanceof JTextField) ((JTextField) c).setText("");
			else if (c instanceof JComboBox) ((JComboBox) c).setSelectedIndex(-1);
			else if (c instanceof JScrollPane) {
				for (Component c2 : ((JScrollPane) c).getComponents()) {
					if (c2 instanceof JViewport) {
						for (Component c3 : ((JViewport) c2).getComponents()) {
							if (c3 instanceof JTable) ((JTable)c3).getSelectionModel().clearSelection();
						}
					}
				}
			}
		}
	}

}
