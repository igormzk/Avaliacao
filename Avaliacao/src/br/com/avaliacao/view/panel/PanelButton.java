package br.com.avaliacao.view.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * Classe com os bot�es de navega��o padr�o.
 * Ao utiliz�-la n�o � necess�rio criar cada bot�o novamente em cada tela do sistema
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
	 * M�todo que ser� desenvolvido para fazer um upsert(update/insert) nas telas
	 */
	public abstract void save();
	/**
	 * M�todo para excluir um registro em uma determinada tela
	 */
	public abstract void delete();
	/**
	 * M�todo para limpar os campos preenchidos pelo usu�rio
	 */
	public abstract void clean();
	
}
