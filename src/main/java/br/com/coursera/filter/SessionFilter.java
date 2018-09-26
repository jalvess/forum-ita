/**
 * 
 */
package br.com.coursera.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jose
 *
 */
@WebFilter(displayName = "Session Filter", urlPatterns = { "/topico", "/ranking", "/auth" })
public class SessionFilter implements Filter {

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * @param filterConfig
	 * @throws ServletException
	 **/
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 **/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest) request).getSession().getAttribute("user") != null
				|| "/auth".equals(((HttpServletRequest) request).getServletPath())) {
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		} else {
			request.setAttribute("title", "Fórum");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 * 
	 **/
	@Override
	public void destroy() {
	}

}
