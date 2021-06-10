package br.com.avaliacao.controller;

/**
 * Interface que todas as classes validadoras devem implmentar
 * @author Igor Muzeka
 * @version 1.0
 */
public interface Validate<T> {

	public String validate(T obj);
}
