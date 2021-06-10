package br.com.avaliacao.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.avaliacao.view.UIProps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * Tela inicial do sistema
 * @author Igor Muzeka
 * @version 1.0
 */
public class JFrameMain extends JFrame {
	
	private JPanel pnlMain;
	private JButton btnStudent;
	private JButton btnCourse;
	private JButton btnGrade;
	
	public JFrameMain() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Sistema de Avaliação 1.0");
		setContentPane(getPnlMain());
		setSize(400, 186);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JPanel getPnlMain() {
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain.add(getBtnStudent());
		pnlMain.add(getBtnGrade());
		pnlMain.add(getBtnCourse());
		UIProps.setFont(pnlMain);
		return pnlMain;
	}

	private JButton getBtnStudent() {
		btnStudent = new JButton("Alunos");
		btnStudent.setBounds(77, 11, 206, 30);
		btnStudent.setMnemonic(KeyEvent.VK_A);
		btnStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameStudent();
			}
		});
		return btnStudent;
	}
	
	private JButton getBtnCourse() {
		btnCourse = new JButton("Disciplinas");
		btnCourse.setBounds(77, 51, 206, 30);
		btnCourse.setMnemonic(KeyEvent.VK_D);
		btnCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameCourse();
			}
		});
		return btnCourse;
	}

	private JButton getBtnGrade() {
		btnGrade = new JButton("Notas");
		btnGrade.setMnemonic(KeyEvent.VK_N);
		btnGrade.setBounds(77, 92, 206, 30);
		btnGrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameGrade();
			}
		});
		return btnGrade;
	}
}
