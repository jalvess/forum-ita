/**
 * 
 */
package br.com.coursera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.coursera.factory.ConnectionFactory;
import br.com.coursera.factory.QueryStatementFactory;
import br.com.coursera.interfaces.IDAO;
import br.com.coursera.model.Comentario;

/**
 * @author Jose
 *
 */
public class ComentarioDAO implements IDAO<Comentario> {

	/**
	 * @see br.com.coursera.interfaces.IDAO#lista()
	 * @return
	 **/
	@Override
	public List<Comentario> lista() {
		try (Connection conn = ConnectionFactory.getConnection();
				ResultSet rs = QueryStatementFactory.selectAll(Comentario.class, "", conn).executeQuery();) {
			List<Comentario> lista = new ArrayList<>();
			while (rs.next()) {
				Comentario com = new Comentario();
				com.setComentario(rs.getString("comentario"));
				com.setUsuarioLogin(rs.getString("login"));
				com.setId(rs.getInt("id_comentario"));
				lista.add(com);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see br.com.coursera.interfaces.IDAO#seleciona(java.lang.Object)
	 * @param t
	 * @return
	 **/
	@Override
	public Comentario seleciona(Comentario t) {
		return null;
	}

	/**
	 * @see br.com.coursera.interfaces.IDAO#adiciona(java.lang.Object)
	 * @param t
	 * @return
	 **/
	@Override
	public int adiciona(Comentario t) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			return QueryStatementFactory.adiciona(t, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @see br.com.coursera.interfaces.IDAO#deleta(java.lang.Object)
	 * @param t
	 * @return
	 **/
	@Override
	public int deleta(Comentario t) {
		return 0;
	}

	public List<Comentario> selecionaPorTopico(int topicoId) {
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement psmt = conn.prepareStatement("select * from comentario where id_topico = ?");) {
			psmt.setInt(1, topicoId);
			ResultSet rs = psmt.executeQuery();
			List<Comentario> comentarios = new ArrayList<>();
			while (rs.next()) {
				Comentario comentario = new Comentario();
				comentario.setComentario(rs.getString("comentario"));
				comentario.setUsuarioLogin(rs.getString("login"));
				comentario.setId(rs.getInt("id_comentario"));
				comentario.setTopicoId(rs.getInt("id_topico"));
				comentarios.add(comentario);
			}
			return comentarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
