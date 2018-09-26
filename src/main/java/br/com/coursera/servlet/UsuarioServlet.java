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
import br.com.coursera.utils.SessionUtils;

/**
 * @author Jose
 *
 */
@WebServlet({ "/auth", "/" })
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 7155124082350887704L;

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
		if (req.getParameter("logout") != null) {
			req.getSession().invalidate();
			req.getSession().removeAttribute("user");
		}
		req.setAttribute("title", "Forum");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 **/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("user") != null) {
			req.setAttribute("title", "T&oacute;picos");
			resp.sendRedirect(req.getContextPath() + "/topico");
//			req.getRequestDispatcher("WEB-INF/views/topico/topicos.jsp").forward(req, resp);
		} else if (req.getParameter("login") != null && !"".equals(req.getParameter("login"))
				&& req.getParameter("senha") != null && !"".equals(req.getParameter("senha"))) {
			String login = req.getParameter("login");
			String senha = req.getParameter("senha");
			Usuario u = new Usuario();
			u.setLogin(login);
			u.setSenha(senha);
			UsuarioDAO dao = new UsuarioDAO();
			u = dao.seleciona(u);
			if (u != null) {
				req.setAttribute("title", "T&oacute;picos");
				SessionUtils.activateSession(req.getSession(), u.getLogin());
				resp.sendRedirect(req.getContextPath() + "/topico");
			} else {
				req.setAttribute("erro", "Login e/ou senha n&atilde;o encontrados");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("erro", "Login e/ou senha n&atilde;o encontrados");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}

}
