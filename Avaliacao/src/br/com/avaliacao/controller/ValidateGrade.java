package br.com.avaliacao.controller;

import java.math.BigDecimal;

import br.com.avaliacao.model.Grade;

/**
 * Classe para validação de notas
 * @author Igor Muzeka
 * @version 1.0
 */
public class ValidateGrade implements Validate<Grade> {

	@Override
	public String validate(Grade obj) {
		if (obj.getStudent() == null) return "Selecione o aluno";
		else if (obj.getCourse() == null) return "Selecione a disciplina";
		else if (obj.getGrade1() == null) return "Informe a primeira nota";
		else if (obj.getGrade1().compareTo(BigDecimal.TEN) > 0) return "A primeira nota não pode ser superior a 10 (dez)";
		else if (obj.getGrade2() == null) return "Informe a segunda nota";
		else if (obj.getGrade2().compareTo(BigDecimal.TEN) > 0) return "A segunda nota não pode ser superior a 10 (dez)";
		else if (obj.getGrade3() == null) return "Informe a terceira nota";
		else if (obj.getGrade3().compareTo(BigDecimal.TEN) > 0) return "A terceira nota não pode ser superior a 10 (dez)";
		else if (obj.getGrade4() == null) return "Informe a quarta nota";
		else if (obj.getGrade4().compareTo(BigDecimal.TEN) > 0) return "A quarta nota não pode ser superior a 10 (dez)";
		return null;
	}

	
}
