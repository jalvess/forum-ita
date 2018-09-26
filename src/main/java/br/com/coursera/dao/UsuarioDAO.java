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
import br.com.coursera.model.Usuario;

/**
 * @author Jose
 * @param <T>
 * @param <T>
 *
 */
public class UsuarioDAO implements IDAO<Usuario> {

	/**
	 * @see br.com.coursera.interfaces.IDAO#lista()
	 * @return
	 **/
	@Override
	public List<Usuario> lista() {
		try (Connection conn = ConnectionFactory.getConnection();
				ResultSet rs = QueryStatementFactory.selectAll(Usuario.class, " order by pontos desc", conn)
						.executeQuery();) {
			List<Usuario> lista = new ArrayList<>();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getString("nome"));
				u.setLogin(rs.getString("login"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				lista.add(u);
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
	public Usuario seleciona(Usuario user) {
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = QueryStatementFactory.selecionaPorIdentificador(user, conn);) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				rs.close();
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see br.com.coursera.interfaces.IDAO#adiciona(java.lang.Object)
	 * @param t
	 **/
	@Override
	public int adiciona(Usuario t) {
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
	 **/
	@Override
	public int deleta(Usuario t) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			return QueryStatementFactory.deleta(t, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int atualizaPontos(String user, int pontos ) {
		try (Connection conn = ConnectionFactory.getConnection();) {
			PreparedStatement psmt = conn.prepareStatement("update usuario set pontos = pontos + ? where login like ? " );
			psmt.setInt(1, pontos);
			psmt.setString(2, user);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
