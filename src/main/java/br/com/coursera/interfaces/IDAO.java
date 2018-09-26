/**
 * 
 */
package br.com.coursera.interfaces;

import java.util.List;

/**
 * @author Jose
 *
 */
public interface IDAO<T> {
	/**
	 * @return List<T>
	 */
	List<T> lista();

	/**
	 * @param t
	 * @return T
	 */
	T seleciona(T t);

	/**
	 * @param t void
	 */
	int adiciona(T t);

	/**
	 * @param t void
	 */
	int deleta(T t);
}
