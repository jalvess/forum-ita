/**
 * 
 */
package br.com.coursera.factory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import br.com.coursera.annotations.Column;

/**
 * @author Jose
 *
 */
public final class QueryStatementFactory {

	/**
	 * Cria e retorna um objeto PreparedStament recebendo como parâmetros o tipo de
	 * classe pertencente a entidade e o objeto Connection
	 * 
	 * @param clazz
	 * @param conn
	 * @return PreparedStatement
	 */
	public static PreparedStatement selectAll(Class<?> clazz, String order, Connection conn) {
		try {
			return conn.prepareStatement("select * from " + clazz.getSimpleName() + order);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * Cria um objeto PreparedStament com uma query já configurada para retornar um
	 * registro especifico utilizando as anotações do tipo Column presentes nos
	 * campos da classe entidade alvo
	 * 
	 * @param t
	 * @param conn
	 * @return PreparedStatement
	 */
	public static PreparedStatement selecionaPorIdentificador(Object t, Connection conn) {
		try {
			StringBuilder query = new StringBuilder();
			query.append("where ");
			List<Object> params = new ArrayList<>();
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class)) {
					if (field.getAnnotation(Column.class).id()) {
						if ("String".equals(field.getType().getSimpleName()) && !field.getAnnotation(Column.class).pk()) {
							query.append(" and " + field.getAnnotation(Column.class).nome() + " ilike ? ");
						} else {
							query.append(" and " + field.getAnnotation(Column.class).nome()  + "= ? ");
						}
						params.add(field.getName());
					}
				}
			}
			PreparedStatement ps = conn.prepareStatement("select * from  " + t.getClass().getSimpleName() + " "
					+ query.toString().replaceAll("(?<=where)\\s+and", ""));
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1,
						t.getClass().getMethod("get" + params.get(i).toString().substring(0, 1).toUpperCase()
								+ params.get(i).toString().substring(1)).invoke(t));
			}
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param t
	 * @param conn void
	 * @return
	 */
	public static int adiciona(Object t, Connection conn) {
		StringBuilder query = new StringBuilder();
		StringBuilder queryValues = new StringBuilder("values(");
		query.append(" insert into " + t.getClass().getSimpleName() + " (");
		List<Object> values = new ArrayList<>();
		for (Field field : t.getClass().getDeclaredFields()) {
			if (field.getAnnotationsByType(Column.class)[0].generated())
				continue;
			query.append(field.getAnnotationsByType(Column.class)[0].nome() + ",");
			queryValues.append("?,");
			try {
				values.add(t.getClass()
						.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1))
						.invoke(t));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		query.deleteCharAt(query.lastIndexOf(","));
		query.append(") ");
		queryValues.deleteCharAt(queryValues.lastIndexOf(","));
		queryValues.append(")");
		query.append(queryValues);
		try (PreparedStatement psmt = conn.prepareStatement(query.toString())) {
			IntStream.range(0, values.size()).forEach(index -> {
				try {
					psmt.setObject(index + 1, values.get(index));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * @param t
	 * @param conn void
	 */
	public static int deleta(Object t, Connection conn) {
		StringBuilder query = new StringBuilder("delete from " + t.getClass().getSimpleName() + " where ");
		StringBuffer buffer = new StringBuffer();
		Arrays.asList(t.getClass().getDeclaredFields()).forEach(i -> {
			if (i.getAnnotation(Column.class).pk()) {
				query.append(i.getName());
				if ("String".equals(i.getType().getSimpleName())) {
					query.append(" ilike ?");
				} else {
					query.append(" = ?");
				}
				try {
					buffer.append(t.getClass()
							.getMethod("get" + i.getName().substring(0, 1).toUpperCase() + i.getName().substring(1))
							.invoke(t));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try (PreparedStatement psmt = conn.prepareStatement(query.toString())) {
			psmt.setObject(1, buffer.toString());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
