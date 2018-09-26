/**
 * 
 */
package br.com.coursera.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Jose
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * Usado para identificar o nome da coluna
	 * 
	 * @return String
	 */
	String nome();

	boolean generated() default false;

	/**
	 * Usado para identificar as colunas que identificam o registro
	 * 
	 * @return String
	 */
	boolean id() default false;

	/**
	 * Identifica a chave primária
	 * 
	 * @return boolean
	 */
	boolean pk() default false;
}
