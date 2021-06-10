package br.com.avaliacao.controller;

import br.com.avaliacao.model.Student;

/**
 * Classe para valida��o de estudantes
 * @author Igor Muzeka
 * @version 1.0
 */
public class ValidateStudent implements Validate<Student> {

	public String validate(Student obj) {
		if (obj.getName().isEmpty()) return "Informe o nome do estudante";
		return null;
	}
	

}
