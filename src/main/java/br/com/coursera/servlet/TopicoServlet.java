package br.com.coursera.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.coursera.dao.ComentarioDAO;
import br.com.coursera.dao.TopicoDAO;
import br.com.coursera.model.Comentario;
import br.com.coursera.model.Topico;

/**
 * Servlet implementation class TopicoServlet
 */
@WebServlet("/topico")
public class TopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean novo = request.getParameter("novo") != null;
		String topicoId = request.getParameter("id");
		if (novo) {
			request.setAttribute("title", "Novo tópico");
			request.getRequestDispatcher("WEB-INF/views/topico/novo.jsp").forward(request, response);
		} else if (topicoId != null) {
			TopicoDAO dao = new TopicoDAO();
			Topico t = new Topico();
			t.setId(Integer.valueOf(topicoId));
			t = dao.seleciona(t);
			ComentarioDAO cDao = new ComentarioDAO();
			List<Comentario> comentarios = cDao.selecionaPorTopico(t.getId());
			request.setAttribute("title", t.getTitulo());
			request.setAttribute("topico", t);
			request.setAttribute("comentarios", comentarios);
			request.getRequestDispatcher("WEB-INF/views/topico/topico.jsp").forward(request, response);
		} else {
			TopicoDAO dao = new TopicoDAO();
			List<Topico> lista = dao.lista();
			request.setAttribute("topicos", lista);
			request.setAttribute("title", "Tópicos");
			request.getRequestDispatcher("WEB-INF/views/topico/topicos.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		TopicoDAO dao = new TopicoDAO();
		Topico t = new Topico();
		t.setTitulo(titulo);
		t.setConteudo(conteudo);
		t.setUsuarioLogin((String) request.getSession().getAttribute("user"));
		boolean adicionado = dao.adiciona(t) > 0;
		request.setAttribute("adicionado", adicionado);
		if (adicionado) {
			request.setAttribute("title", t.getTitulo());
			request.setAttribute("topico", t);
			request.getRequestDispatcher("WEB-INF/views/topico/topico.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/views/topico/novo.jsp").forward(request, response);
		}

	}

}
