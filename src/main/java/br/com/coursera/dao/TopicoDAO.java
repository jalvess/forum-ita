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
import br.com.coursera.model.Topico;

/**
 * @author Jose
 *
 */
public class TopicoDAO implements IDAO<Topico> {

	/**
	 * @see br.com.coursera.interfaces.IDAO#lista()
	 * @return
	 **/
	@Override
	public List<Topico> lista() {
		try (Connection conn = ConnectionFactory.getConnection();
				ResultSet rs = QueryStatementFactory.selectAll(Topico.class, " order by titulo ", conn)
						.executeQuery();) {
			List<Topico> topicos = new ArrayList<>();
			while (rs.next()) {
				Topico t = new Topico();
				t.setTitulo(rs.getString("titulo"));
				t.setConteudo(rs.getString("conteudo"));
				t.setId(rs.getInt("id_topico"));
				t.setUsuarioLogin(rs.getString("login"));
				topicos.add(t);
			}
			return topicos;
		} catch (Exception e) {
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
	public Topico seleciona(Topico t) {
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement psmt = QueryStatementFactory.selecionaPorIdentificador(t, conn);
				ResultSet rs = psmt.executeQuery();) {
			if (rs.next()) {
				t.setConteudo(rs.getString("conteudo"));
				t.setTitulo(rs.getString("titulo"));
				t.setUsuarioLogin(rs.getString("login"));
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see br.com.coursera.interfaces.IDAO#adiciona(java.lang.Object)
	 * @param t
	 * @return
	 **/
	@Override
	public int adiciona(Topico t) {
		try (Connection conn = ConnectionFactory.getConnection();) {
			int qtdAdicionado = QueryStatementFactory.adiciona(t, conn);
			if (qtdAdicionado > 0) {
				UsuarioDAO dao = new UsuarioDAO();
				dao.atualizaPontos(t.getUsuarioLogin(), 10);
			}
			return qtdAdicionado;
		} catch (Exception e) {
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
	public int deleta(Topico t) {
		return 0;
	}

}
