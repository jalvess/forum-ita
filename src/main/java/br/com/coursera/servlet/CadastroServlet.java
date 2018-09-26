/**
 * 
 */
package br.com.coursera.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.coursera.dao.UsuarioDAO;
import br.com.coursera.model.Usuario;

/**
 * @author Jose
 *
 */
@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

	private static final long serialVersionUID = -2427713661843266486L;

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 **/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "Cadastro");
		req.getRequestDispatcher("WEB-INF/views/cadastro.jsp").forward(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 **/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario u = new Usuario();
		u.setLogin(req.getParameter("login"));
		u.setEmail(req.getParameter("email"));
		u.setNome(req.getParameter("nome"));
		u.setSenha(req.getParameter("senha"));
		UsuarioDAO dao = new UsuarioDAO();
		boolean adicionado = dao.adiciona(u) > 0;
		resp.reset();
		req.setAttribute("adicionado", adicionado);
		req.setAttribute("title", "Cadastro");
		req.getRequestDispatcher("WEB-INF/views/cadastro.jsp").forward(req, resp);
	}
}
