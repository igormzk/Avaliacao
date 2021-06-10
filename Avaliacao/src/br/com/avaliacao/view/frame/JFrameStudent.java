package br.com.avaliacao.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.avaliacao.controller.ValidateStudent;
import br.com.avaliacao.controller.db.StudentDAO;
import br.com.avaliacao.model.Student;
import br.com.avaliacao.view.UIProps;
import br.com.avaliacao.view.document.TextDocument;
import br.com.avaliacao.view.panel.PanelButton;
import br.com.avaliacao.view.table.TableModelStudent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Tela de cadastro de estudante
 * @author Igor Muzeka
 * @version 1.0
 */
public class JFrameStudent extends JFrame {

	private JPanel pnlMain;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblName;
	private JTextField txtName;
	private PanelButton pnlButton;
	private JScrollPane scpStudent;
	private TableModelStudent tblModelStudent;
	private JTable tblStudent;
	private final ValidateStudent vs = new ValidateStudent();

	public JFrameStudent() {
		super();
		setTitle("Cadastro de Estudantes");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(601, 300);
		setContentPane(getPnlMain());
		pnlMain.add(getScpStudent());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel getPnlMain() {
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain.add(getLblId());
		pnlMain.add(getTxtId());
		pnlMain.add(getLblName());
		pnlMain.add(getTxtName());
		pnlMain.add(getPnlButton());
		UIProps.setFont(pnlMain);
		return pnlMain;
	}

	private JLabel getLblId() {
		lblId = new JLabel("Código");
		lblId.setBounds(10, 5, 46, 14);
		return lblId;
	}

	private JTextField getTxtId() {
		txtId = new JTextField();
		txtId.setBounds(10, 21, 86, 20);
		txtId.setEnabled(false);
		return txtId;
	}

	private JLabel getLblName() {
		lblName = new JLabel("Nome");
		lblName.setBounds(106, 5, 46, 14);
		return lblName;
	}

	private JTextField getTxtName() {
		txtName = new JTextField();
		txtName.setBounds(106, 21, 469, 20);
		txtName.setDocument(new TextDocument(70));
		return txtName;
	}

	private PanelButton getPnlButton() {
		pnlButton = new PanelButton() {

			@Override
			public void save() {
				saveStudent();
				clean();
			}

			@Override
			public void delete() {
				deleteStudent();
				clean();
			}

			@Override
			public void clean() {
				UIProps.clean(pnlMain);
			}
		};
		pnlButton.setBounds(116, 50, 360, 30);
		UIProps.setFont(pnlButton);

		return pnlButton;
	}

	private JScrollPane getScpStudent() {
		scpStudent = new JScrollPane();
		scpStudent.setBounds(10, 91, 565, 159);
		scpStudent.setViewportView(getTblStudent());
		return scpStudent;
	}

	private JTable getTblStudent() {
		tblModelStudent = new TableModelStudent(new StudentDAO().load());
		tblStudent = new JTable(tblModelStudent);
		tblStudent.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStudent.getColumnModel().getColumn(tblModelStudent.ID).setPreferredWidth(65);
		tblStudent.getColumnModel().getColumn(tblModelStudent.NAME).setPreferredWidth(485);
		tblStudent.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return;
				if (tblStudent.getSelectedRow() > -1) {
					displayStudent(tblModelStudent.getList().get(tblStudent.getSelectedRow()));
				}
			}
		});
		return tblStudent;
	}

	/**
	 * Método para preencher um objeto do tipo estudante
	 * @return Student - estudante com o que foi preenchido na tela
	 */
	private Student fill() {
		Student s = null;
		if (tblStudent.getSelectedRow() != -1) {
			s = tblModelStudent.getList().get(tblStudent.getSelectedRow());
		} else {
			s = new Student();
		}
		s.setName(txtName.getText());
		return s;
	}
	
	/**
	 * Método que preenche os campos da tela de um estudante que já foi salvo anteriormente
	 * @param s Student - estudante salvo
	 */
	private void displayStudent(Student s) {
		txtId.setText(String.valueOf(s.getId()));
		txtName.setText(s.getName());
	}
	
	private void deleteStudent() {
		int row = tblStudent.getSelectedRow();
		if (row > -1) {
			new StudentDAO().delete(Integer.parseInt(txtId.getText()));
			tblModelStudent.onRemove(row);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um registro para excluir", "Validação!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que irá salvar o estudante se os campos foram preenchidos corretamente
	 */
	private void saveStudent() {
		Student s = fill();
		String str = vs.validate(s);
		if (str == null) {
			s.setId(new StudentDAO().save(s));
			if (tblStudent.getSelectedRow() == -1) {
				tblModelStudent.onAdd(s);
			} else {
				tblModelStudent.onUpdate(s);
			}
		} else {
			txtName.requestFocus();
			JOptionPane.showMessageDialog(this, str, "Validação!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
