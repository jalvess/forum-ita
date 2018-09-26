/**
 * 
 */
package br.com.coursera.teste;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import br.com.coursera.dao.UsuarioDAO;
import br.com.coursera.factory.ConnectionFactory;
import br.com.coursera.model.Usuario;

/**
 * @author Jose
 *
 */
public class UsuarioDAOTeste {
	JdbcDatabaseTester jdt;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(ConnectionFactory.DRIVER, ConnectionFactory.URL, ConnectionFactory.USER,
				ConnectionFactory.SENHA);
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load("/usuariodb.xml");
		jdt.getConnection().getConfig().setFeature(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true); 
		jdt.setDataSet(dataSet);
		jdt.onSetup();
	}

	@Test
	public void lista() {
		UsuarioDAO dao = new UsuarioDAO();
		assertEquals(5, dao.lista().size());
	}

	@Test
	public void adiciona() throws SQLException, Exception {
		Usuario u = new Usuario();
		u.setLogin("jimmy");
		u.setEmail("jimmy@gmail.com");
		u.setNome("James");
		u.setSenha("1234");
		UsuarioDAO dao = new UsuarioDAO();
		dao.adiciona(u);
		assertEquals(6, jdt.getConnection().createDataSet().getTable("usuario").getRowCount());
	}

	@Test
	public void atualizaPontos() throws SQLException, Exception {
		UsuarioDAO dao = new UsuarioDAO();
		dao.atualizaPontos("peter", 10);
		ITable table = jdt.getConnection().createDataSet().getTable("usuario");
		assertEquals(Integer.valueOf((String) jdt.getDataSet().getTable("usuario").getValue(1, "pontos")) + 10, table.getValue(4, "pontos"));
	}
	
	@Test
	public void deleta() throws SQLException, Exception {
		UsuarioDAO dao = new UsuarioDAO();
		assertEquals(5, jdt.getDataSet().getTable("usuario").getRowCount());
		Usuario u = new Usuario();
		u.setLogin("peter");
		dao.deleta(u);
		assertEquals(4, jdt.getConnection().createDataSet().getTable("usuario").getRowCount());
	}
	
	@Test
	public void selecionaUsuario() throws DatabaseUnitException, SQLException, Exception {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = new Usuario();
		u.setLogin("peter");
		u.setSenha("1234");
		dao.seleciona(u);
		assertEquals(dao.seleciona(u) != null, true);
	}
}
