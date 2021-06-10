package br.com.avaliacao.model;

import java.math.BigDecimal;

/**
 * Classe base para notas
 * @author Igor Muzeka
 * @version 1.0
 */
public class Grade {
	
	private Integer id;
	private Student student;
	private Course course;
	private BigDecimal grade1;
	private BigDecimal grade2;
	private BigDecimal grade3;
	private BigDecimal grade4;
	private BigDecimal avg;
	private String status;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public BigDecimal getGrade1() {
		return grade1;
	}

	public void setGrade1(BigDecimal grade1) {
		this.grade1 = grade1;
	}

	public BigDecimal getGrade2() {
		return grade2;
	}

	public void setGrade2(BigDecimal grade2) {
		this.grade2 = grade2;
	}

	public BigDecimal getGrade3() {
		return grade3;
	}

	public void setGrade3(BigDecimal grade3) {
		this.grade3 = grade3;
	}

	public BigDecimal getGrade4() {
		return grade4;
	}

	public void setGrade4(BigDecimal grade4) {
		this.grade4 = grade4;
	}

	public BigDecimal getAvg() {
		if (avg == null) {
			calcAvg();
		}
		return avg;
	}

	/**
	 * Método para verificar a situação de um estudante
	 * Utilizar assim que a média estiver disponível.
	 * @return String - situação
	 */
	public String getStatus() {
		if (avg.compareTo(new BigDecimal(5)) < 0) {
			status = "Reprovado";
		} else if (avg.compareTo(new BigDecimal(7)) > -1) {
			status = "Aprovado";
		} else {
			status = "Em Recuperação";
		}
		return status;
	}
	
	/**
	 * Método que calcula a média.
	 * Só utilizar quando todas as notas estiverem corretas.
	 */
	private void calcAvg() {
		BigDecimal sum = grade1.add(grade2).add(grade3).add(grade4);
		avg = sum.divide(new BigDecimal(4)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}
	
}
