package br.com.avaliacao.controller;

import br.com.avaliacao.model.Course;

/**
 * Classe para validação de disciplinas
 * @author Igor Muzeka
 * @version 1.0
 */
public class ValidateCourse implements Validate<Course> {

	@Override
	public String validate(Course obj) {
		if (obj.getName().isEmpty()) return "Informe o nome da disciplina";
		if (obj.getCredit() < 1) return "A quantidade de créditos deve ser maior que 0 (zero)";
		return null;
	}

}
