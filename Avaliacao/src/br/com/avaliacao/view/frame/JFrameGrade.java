package br.com.avaliacao.view.frame;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
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

import br.com.avaliacao.controller.ValidateGrade;
import br.com.avaliacao.controller.db.CourseDAO;
import br.com.avaliacao.controller.db.GradeDAO;
import br.com.avaliacao.controller.db.StudentDAO;
import br.com.avaliacao.model.Course;
import br.com.avaliacao.model.Grade;
import br.com.avaliacao.model.Student;
import br.com.avaliacao.view.UIProps;
import br.com.avaliacao.view.document.FloatDocument;
import br.com.avaliacao.view.panel.PanelButton;
import br.com.avaliacao.view.table.TableModelGrade;

/**
 * Tela para castro de notas
 * @author Igor Muzeka
 * @version 1.0
 */
public class JFrameGrade extends JFrame {

	private JPanel pnlMain;
	private JLabel lblStudent;
	private JComboBox<Student> cmbStudent;
	private JLabel lblCourse;
	private JComboBox<Course> cmbCourse;
	private PanelButton pnlButton;
	private JScrollPane scpGrade;
	private TableModelGrade tblModelGrade;
	private JTable tblGrade;
	private final ValidateGrade vg = new ValidateGrade();
	private JLabel lblGrade1;
	private JTextField txtGrade1;
	private JLabel lblGrade2;
	private JTextField txtGrade2;
	private JLabel lblGrade3;
	private JTextField txtGrade3;
	private JLabel lblGrade4;
	private JTextField txtGrade4;
	private JLabel lblAvg;
	private JTextField txtAvg;
	private JLabel lblStatus;

	public JFrameGrade() {
		super();
		setTitle("Cadastro de Notas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(601, 418);
		setContentPane(getPnlMain());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel getPnlMain() {
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain.add(getLblStudent());
		pnlMain.add(getCmbStudent());
		pnlMain.add(getLblCourse());
		pnlMain.add(getCmbCourse());
		pnlMain.add(getLblGrade1());
		pnlMain.add(getTxtGrade1());
		pnlMain.add(getLblGrade2());
		pnlMain.add(getTxtGrade2());
		pnlMain.add(getLblGrade3());
		pnlMain.add(getTxtGrade3());
		pnlMain.add(getLblGrade4());
		pnlMain.add(getTxtGrade4());
		pnlMain.add(getLblAvg());
		pnlMain.add(getTxtAvg());
		pnlMain.add(getLblStatus());
		pnlMain.add(getPnlButton());
		pnlMain.add(getScpGrade());
		UIProps.setFont(pnlMain);
		return pnlMain;
	}

	private JLabel getLblStudent() {
		lblStudent = new JLabel("Aluno");
		lblStudent.setBounds(10, 5, 46, 14);
		return lblStudent;
	}

	private JComboBox<Student> getCmbStudent() {
		cmbStudent = new JComboBox<Student>();
		List<Student> list = new StudentDAO().load();
		list.forEach(student -> {
			cmbStudent.addItem(student);
		});
		cmbStudent.setBounds(10, 21, 469, 20);
		cmbStudent.setSelectedIndex(-1);
		return cmbStudent;
	}
	
	private JLabel getLblCourse () {
		lblCourse = new JLabel("Disciplina");
		lblCourse.setBounds(10, 51, 61, 14);
		return lblCourse;
	}
	private JComboBox<Course> getCmbCourse() {
			cmbCourse = new JComboBox<Course>();
			List<Course> list = new CourseDAO().load();
			list.forEach(course ->{
				cmbCourse.addItem(course);
			});
			cmbCourse.setSelectedIndex(-1);
			cmbCourse.setBounds(10, 67, 203, 20);
		return cmbCourse;
	}
	
	private JLabel getLblGrade1() {
			lblGrade1 = new JLabel("Nota 1");
			lblGrade1.setBounds(10, 97, 46, 14);
		return lblGrade1;
	}
	private JTextField getTxtGrade1() {
			txtGrade1 = new JTextField();
			txtGrade1.setDocument(new FloatDocument());
			txtGrade1.setBounds(10, 113, 86, 20);
			txtGrade1.addFocusListener(new FocusListener() {
				
				@Override
				public void focusGained(FocusEvent e) {
					try
			        {
						if (!txtGrade1.getText().isEmpty()) {
			            KeyEvent ke = new KeyEvent(e.getComponent(), KeyEvent.KEY_PRESSED,
			                    System.currentTimeMillis(), InputEvent.CTRL_MASK,
			                    KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
			            e.getComponent().dispatchEvent(ke);
						}
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			        }
					
				}
			
				@Override
				public void focusLost(FocusEvent e) {
					if (canCalcAvg()) {
						displayAvgStatus();
					}
				}
			});
		return txtGrade1;
	}
	private JLabel getLblGrade2() {
			lblGrade2 = new JLabel("Nota 2");
			lblGrade2.setBounds(105, 97, 46, 14);
		return lblGrade2;
	}
	private JTextField getTxtGrade2() {
			txtGrade2 = new JTextField();
			txtGrade2.setDocument(new FloatDocument());
			txtGrade2.setBounds(105, 113, 86, 20);
			txtGrade2.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					try
			        {
						if (!txtGrade2.getText().isEmpty()) {
			            KeyEvent ke = new KeyEvent(e.getComponent(), KeyEvent.KEY_PRESSED,
			                    System.currentTimeMillis(), InputEvent.CTRL_MASK,
			                    KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
			            e.getComponent().dispatchEvent(ke);
						}
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			        }
					
				}
				
				@Override
				public void focusLost(FocusEvent e) {
					if (canCalcAvg()) {
						displayAvgStatus();
					}
				}
			});
		return txtGrade2;
	}
	private JLabel getLblGrade3() {
			lblGrade3 = new JLabel("Nota 3");
			lblGrade3.setBounds(201, 97, 46, 14);
		return lblGrade3;
	}
	private JTextField getTxtGrade3() {
			txtGrade3 = new JTextField();
			txtGrade3.setDocument(new FloatDocument());
			txtGrade3.setBounds(201, 113, 86, 20);
			txtGrade3.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					try
			        {
						if (!txtGrade3.getText().isEmpty()) {
			            KeyEvent ke = new KeyEvent(e.getComponent(), KeyEvent.KEY_PRESSED,
			                    System.currentTimeMillis(), InputEvent.CTRL_MASK,
			                    KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
			            e.getComponent().dispatchEvent(ke);
						}
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			        }
					
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (canCalcAvg()) {
						displayAvgStatus();
					}
				}
			});
		return txtGrade3;
	}
	private JLabel getLblGrade4() {
			lblGrade4 = new JLabel("Nota 4");
			lblGrade4.setBounds(297, 97, 46, 14);
		return lblGrade4;
	}
	private JTextField getTxtGrade4() {
			txtGrade4 = new JTextField();
			txtGrade4.setDocument(new FloatDocument());
			txtGrade4.setBounds(297, 113, 86, 20);
			txtGrade4.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					try
			        {
						if (!txtGrade1.getText().isEmpty()) {
			            KeyEvent ke = new KeyEvent(e.getComponent(), KeyEvent.KEY_PRESSED,
			                    System.currentTimeMillis(), InputEvent.CTRL_MASK,
			                    KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
			            e.getComponent().dispatchEvent(ke);
						}
			        } catch (Exception ex) {
			        	ex.printStackTrace();
			        }
					
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (canCalcAvg()) {
						displayAvgStatus();
					}
				}
			});
		return txtGrade4;
	}
	private JLabel getLblAvg() {
			lblAvg = new JLabel("Média");
			lblAvg.setBounds(393, 97, 46, 14);
		return lblAvg;
	}
	private JTextField getTxtAvg() {
			txtAvg = new JTextField();
			txtAvg.setEnabled(false);
			txtAvg.setBounds(393, 113, 86, 20);
		return txtAvg;
	}
	private JLabel getLblStatus() {
			lblStatus = new JLabel("");
			lblStatus.setBounds(178, 138, 142, 20);
		return lblStatus;
	}

	private PanelButton getPnlButton() {
		pnlButton = new PanelButton() {

			@Override
			public void save() {
				if (saveGrade())
					clean();
			}

			@Override
			public void delete() {
				deleteGrade();
				clean();
			}

			@Override
			public void clean() {
				UIProps.clean(pnlMain);
				lblStatus.setText("");
			}
		};
		pnlButton.setBounds(80, 169, 360, 30);
		UIProps.setFont(pnlButton);

		return pnlButton;
	}

	private JScrollPane getScpGrade() {
		scpGrade = new JScrollPane();
		scpGrade.setBounds(10, 209, 565, 159);
		scpGrade.setViewportView(getTblGrade());
		return scpGrade;
	}

	private JTable getTblGrade() {
		tblModelGrade = new TableModelGrade(new GradeDAO().load());
		tblGrade = new JTable(tblModelGrade);
		tblGrade.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblGrade.getColumnModel().getColumn(tblModelGrade.NAME).setPreferredWidth(250);
		tblGrade.getColumnModel().getColumn(tblModelGrade.COURSE).setPreferredWidth(150);
		tblGrade.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return;
				if (tblGrade.getSelectedRow() > -1) {
					displayGrade(tblModelGrade.getList().get(tblGrade.getSelectedRow()));
				}
			}
		});
		return tblGrade;
	}

	/**
	 * Método para preencher um objeto do tipo nota
	 * @return Grade - notas com o que foi preenchido na tela
	 */
	private Grade fill() {
		Grade g = null;
		if (tblGrade.getSelectedRow() != -1) {
			g = tblModelGrade.getList().get(tblGrade.getSelectedRow());
		} else {
			g = new Grade();
		}
		g.setStudent((Student)cmbStudent.getSelectedItem());
		g.setCourse((Course)cmbCourse.getSelectedItem());
		if (!txtGrade1.getText().isEmpty()) {
			g.setGrade1(new BigDecimal(txtGrade1.getText().replaceAll(",", ".")));
		}
		if (!txtGrade2.getText().isEmpty()) {
			g.setGrade2(new BigDecimal(txtGrade2.getText().replaceAll(",", ".")));
		}
		if (!txtGrade3.getText().isEmpty()) {
			g.setGrade3(new BigDecimal(txtGrade3.getText().replaceAll(",", ".")));
		}
		if (!txtGrade4.getText().isEmpty()) {
			g.setGrade4(new BigDecimal(txtGrade4.getText().replaceAll(",", ".")));
		}
		return g;
	}
	
	/**
	 * Método que preenche os campos da tela de uma nota que já foi salva anteriormente
	 * @param g Grade - Nota salva
	 */
	private void displayGrade(Grade g) {
		cmbStudent.setSelectedItem(g.getStudent());
		cmbCourse.setSelectedItem(g.getCourse());
		txtGrade1.setText(String.valueOf(g.getGrade1()).replaceAll("\\.", ""));
		txtGrade2.setText(String.valueOf(g.getGrade2()).replaceAll("\\.", ""));
		txtGrade3.setText(String.valueOf(g.getGrade3()).replaceAll("\\.", ""));
		txtGrade4.setText(String.valueOf(g.getGrade4()).replaceAll("\\.", ""));
		displayAvgStatus();
	}
	
	private void deleteGrade() {
		int row = tblGrade.getSelectedRow();
		if (row > -1) {
			new GradeDAO().delete(tblModelGrade.getList().get(row).getId());
			tblModelGrade.onRemove(row);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um registro para excluir", "Validação!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que irá salvar as notas se os campos forem preenchidos corretamente.
	 * Caso ocorra algum problema de validação as informações dos campos não serão limpas
	 * @return boolean - salvou corretamente
	 */
	private boolean saveGrade() {
		Grade g = fill();
		String str = vg.validate(g);
		if (str == null) {
			g.setId(new GradeDAO().save(g));
			if (tblGrade.getSelectedRow() == -1) {
				tblModelGrade.onAdd(g);
			} else {
				tblModelGrade.onUpdate(g);
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(this, str, "Validação!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * Método que verifica se todas as notas informadas estão preenchidas corretamente
	 * @return boolean - Notas todas preenchidas para cálculo da média
	 */
	private boolean canCalcAvg() {
		if (txtGrade1.getText().isEmpty() || txtGrade2.getText().isEmpty() || txtGrade3.getText().isEmpty() || txtGrade4.getText().isEmpty()) {
			return false;
		}
		
		if (new BigDecimal(txtGrade1.getText().replaceAll(",", ".")).compareTo(BigDecimal.TEN) > 0) { 
			requestFocus(txtGrade1);
			return false;
		}
		if (new BigDecimal(txtGrade2.getText().replaceAll(",", ".")).compareTo(BigDecimal.TEN) > 0) {
			requestFocus(txtGrade2);
			return false;
		}
		if (new BigDecimal(txtGrade3.getText().replaceAll(",", ".")).compareTo(BigDecimal.TEN) > 0) {
			requestFocus(txtGrade3);
			return false;
		}
		if (new BigDecimal(txtGrade4.getText().replaceAll(",", ".")).compareTo(BigDecimal.TEN) > 0) {
			requestFocus(txtGrade4);
			return false;
		}
		return true;
	}
	
	/**
	 * Método que mostrará a média e a situação do estudante
	 */
	private void displayAvgStatus() {
		Grade g = fill();
		txtAvg.setText(String.valueOf(g.getAvg()).replaceAll("\\.", ","));
		lblStatus.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		String color = "black";
		String status = g.getStatus();
		if (status.equals("Aprovado")) {
			color = "blue";
		} else if (status.equals("Reprovado")) {
			color = "red";
		}
		lblStatus.setText("<HTML><p style=\"color:" + color +"\">" + status + "</p></HTML>");
	}
	
	/**
	 * Método que mostrará ao usuário uma nota incorreta
	 * @param txtGrade JTextField - campo que contêm uma nota
	 */
	private void requestFocus(JTextField txtGrade) {
		txtGrade.setToolTipText("<HTML><p style=\"color:red\">A nota não pode ser maior que 10 (dez)</p></HTML>");
		txtGrade.requestFocus();
	}
	
}
