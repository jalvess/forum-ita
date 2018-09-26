/**
 * 
 */
package br.com.coursera.teste;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import br.com.coursera.dao.TopicoDAO;
import br.com.coursera.factory.ConnectionFactory;
import br.com.coursera.model.Topico;

/**
 * @author Jose
 *
 */
public class TopicoDAOTeste {
	JdbcDatabaseTester jdt;

	@Before
	public void onSetUp() throws Exception {
		jdt = new JdbcDatabaseTester(ConnectionFactory.DRIVER, ConnectionFactory.URL, ConnectionFactory.USER,
				ConnectionFactory.SENHA);
		FlatXmlDataFileLoader xml = new FlatXmlDataFileLoader();
		jdt.setDataSet(xml.load("/topicoDb.xml"));
		jdt.onSetup();
	}

	@Test
	public void selecionaAll() throws DataSetException {
		assertEquals(2, jdt.getDataSet().getTable("topico").getRowCount());
	}

	@Test
	public void adiciona() throws DataSetException, SQLException, Exception {
		Topico topico = new Topico();
		topico.setTitulo("Continunand a saga dos amendoins");
		topico.setConteudo("Por falar em amendoins...etc etc etc loreipsin ");
		topico.setUsuarioLogin("peter");
		TopicoDAO dao = new TopicoDAO();
		dao.adiciona(topico);
		assertEquals(jdt.getConnection().createDataSet().getTable("topico").getRowCount(), 3);
	}
}
