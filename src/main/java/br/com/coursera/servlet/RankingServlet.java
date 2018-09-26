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

/**
 * @author Jose
 *
 */
@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		UsuarioDAO dao = new UsuarioDAO();
		req.setAttribute("usuarios", dao.lista());
		req.setAttribute("title", "Rankings");
		req.getRequestDispatcher("WEB-INF/views/ranking.jsp").forward(req, resp);;
	}
}
