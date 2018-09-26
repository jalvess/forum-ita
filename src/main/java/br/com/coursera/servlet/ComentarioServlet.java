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

import br.com.coursera.dao.ComentarioDAO;
import br.com.coursera.dao.UsuarioDAO;
import br.com.coursera.model.Comentario;

/**
 * @author Jose
 *
 */
@WebServlet("/comentario")
public class ComentarioServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2248628154418710917L;

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
		String comentario = req.getParameter("comentario");
		String topicoId = req.getParameter("id_topico");
		String user = req.getParameter("user");
		Comentario com = new Comentario();
		com.setComentario(comentario);
		com.setTopicoId(Integer.valueOf(topicoId));
		com.setUsuarioLogin(user);
		ComentarioDAO dao = new ComentarioDAO();
		boolean adicionado = dao.adiciona(com) > 0;
		if (adicionado) {
			UsuarioDAO uDao = new UsuarioDAO();
			uDao.atualizaPontos(user, 3);
		}
		resp.sendRedirect(req.getContextPath() + "/topico?id=" + topicoId);
//		req.getRequestDispatcher("WEB-INF/views/topico/topico.jsp").forward(req, resp);;
	}
}
