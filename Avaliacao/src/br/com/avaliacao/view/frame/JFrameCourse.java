package br.com.avaliacao.view.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.avaliacao.controller.ValidateCourse;
import br.com.avaliacao.controller.db.CourseDAO;
import br.com.avaliacao.model.Course;
import br.com.avaliacao.view.UIProps;
import br.com.avaliacao.view.document.IntegerDocument;
import br.com.avaliacao.view.document.TextDocument;
import br.com.avaliacao.view.panel.PanelButton;
import br.com.avaliacao.view.table.TableModelCourse;

/**
 * Tela de cadastro de disciplinas
 * @author Igor Muzeka
 * @version 1.0
 */
public class JFrameCourse extends JFrame {

		private JPanel pnlMain;
		private JLabel lblId;
		private JTextField txtId;
		private JLabel lblName;
		private JTextField txtName;
		private PanelButton pnlButton;
		private JScrollPane scpCourse;
		private TableModelCourse tblModelCourse;
		private JTable tblCourse;
		private final ValidateCourse vc = new ValidateCourse();
		private JLabel lblCredit;
		private JTextField txtCredit;

		public JFrameCourse() {
			super();
			setTitle("Cadastro de Disciplinas");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(601, 300);
			setContentPane(getPnlMain());
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
			pnlMain.add(getScpCourse());
			pnlMain.add(getLblCredit());
			pnlMain.add(getTxtCredit());
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
			txtName.setBounds(106, 21, 360, 20);
			txtName.setDocument(new TextDocument(30));
			return txtName;
		}
		private JLabel getLblCredit() {
				lblCredit = new JLabel("Crédito");
				lblCredit.setBounds(476, 5, 46, 14);
			return lblCredit;
		}
		private JTextField getTxtCredit() {
				txtCredit = new JTextField();
				txtCredit.setBounds(476, 21, 86, 20);
				txtCredit.setDocument(new IntegerDocument());
			return txtCredit;
		}

		private PanelButton getPnlButton() {
			pnlButton = new PanelButton() {

				@Override
				public void save() {
					if (saveCourse()) {
						clean();
					}
				}

				@Override
				public void delete() {
					deleteCourse();
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

		private JScrollPane getScpCourse() {
			scpCourse = new JScrollPane();
			scpCourse.setBounds(10, 91, 565, 159);
			scpCourse.setViewportView(getTblCourse());
			return scpCourse;
		}

		private JTable getTblCourse() {
			tblModelCourse = new TableModelCourse(new CourseDAO().load());
			tblCourse = new JTable(tblModelCourse);
			tblCourse.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblCourse.getColumnModel().getColumn(tblModelCourse.ID).setPreferredWidth(65);
			tblCourse.getColumnModel().getColumn(tblModelCourse.NAME).setPreferredWidth(405);
			tblCourse.getColumnModel().getColumn(tblModelCourse.CREDIT).setPreferredWidth(80);
			tblCourse.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting()) return;
					if (tblCourse.getSelectedRow() > -1) {
						displayCourse(tblModelCourse.getList().get(tblCourse.getSelectedRow()));
					}
				}
			});
			return tblCourse;
		}

		/**
		 * Método para preencher um objeto do tipo discplina
		 * @return Course - disciplina com o que foi preenchido na tela
		 */
		private Course fill() {
			Course c = null;
			if (tblCourse.getSelectedRow() != -1) {
				c = tblModelCourse.getList().get(tblCourse.getSelectedRow());
			} else {
				c = new Course();
			}
			c.setName(txtName.getText());
			if (!txtCredit.getText().isEmpty()) {
				c.setCredit(Integer.parseInt(txtCredit.getText()));
			}
			return c;
		}
		
		/**
		 * Método que preenche os campos da tela de uma disciplina que já foi salva anteriormente
		 * @param c Course - Disciplina salva
		 */
		private void displayCourse(Course c) {
			txtId.setText(String.valueOf(c.getId()));
			txtName.setText(c.getName());
			txtCredit.setText(String.valueOf(c.getCredit()));
		}
		
		private void deleteCourse() {
			int row = tblCourse.getSelectedRow();
			if (row > -1 ) {
				int proc = new CourseDAO().delete(Integer.parseInt(txtId.getText()));
				if (proc > 0) {
					tblModelCourse.onRemove(row);
				} else {
					JOptionPane.showMessageDialog(this, "O registro selecionado não pode ser excluído", "Validação!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para excluir", "Validação!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/**
		 * Método que irá salvar as disciplina se os campos forem preenchidos corretamente.
		 * Caso ocorra algum problema de validação as informações dos campos não serão limpas
		 * @return boolean - salvou corretamente
		 */
		private boolean saveCourse() {
			Course c = fill();
			String str = vc.validate(c);
			if (str == null) {
				c.setId(new CourseDAO().save(c));
				if (tblCourse.getSelectedRow() == -1) {
					tblModelCourse.onAdd(c);
				} else {
					tblModelCourse.onUpdate(c);
				}
				return true;
			} else {
				txtName.requestFocus();
				JOptionPane.showMessageDialog(this, str, "Validação!", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	
}
