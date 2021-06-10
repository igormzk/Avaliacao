package br.com.avaliacao.controller.db;

import java.util.List;

/**
 * Interface que todas as classe que vão se comunicar com o banco de dados devem ter
 * @author Igor Muzeka
 * @version 1.0
 */
public interface DAO<T> {

	public int save(T obj);
	public int delete(int id);
	public T load(int id);
	public List<T> load();
}
