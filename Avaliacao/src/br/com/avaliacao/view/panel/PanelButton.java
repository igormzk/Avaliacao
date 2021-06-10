package br.com.avaliacao.view.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * Classe com os botões de navegação padrão.
 * Ao utilizá-la não é necessário criar cada botão novamente em cada tela do sistema
 * @author Igor Muzeka
 * @version 1.0
 */
public abstract class PanelButton extends JPanel {
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnClean;

	public PanelButton() {
		setLayout(new GridLayout(1, 0, 0, 0));
		setSize(360, 30);
		add(getBtnSave());
		add(getBtnDelete());
		add(getBtnClean());
	}

	private JButton getBtnSave() {
			btnSave = new JButton("Salvar");
			btnSave.setMnemonic(KeyEvent.VK_S);
			btnSave.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					save();
				}
			});
		return btnSave;
	}

	private JButton getBtnDelete() {
			btnDelete = new JButton("Excluir");
			btnDelete.setMnemonic(KeyEvent.VK_E);
			btnDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					delete();
				}
			});
		return btnDelete;
	}

	private JButton getBtnClean() {
			btnClean = new JButton("Limpar");
			btnClean.setMnemonic(KeyEvent.VK_L);
			btnClean.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					clean();
				}
			});
		return btnClean;
	}
	/**
	 * Método que será desenvolvido para fazer um upsert(update/insert) nas telas
	 */
	public abstract void save();
	/**
	 * Método para excluir um registro em uma determinada tela
	 */
	public abstract void delete();
	/**
	 * Método para limpar os campos preenchidos pelo usuário
	 */
	public abstract void clean();
	
}
