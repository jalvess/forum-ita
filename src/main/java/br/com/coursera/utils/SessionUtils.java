/**
 * 
 */
package br.com.coursera.utils;

import java.util.Date;

import javax.servlet.http.HttpSession;

import br.com.coursera.async.SessionTimer;

/**
 * @author Jose
 */
public class SessionUtils {
	/**
	 * Retorna o tempo restante da sessão em segundos
	 * 
	 * @param sessao
	 * @return long
	 */
	public static long segundosRestantes(HttpSession sessao) {
		return (sessao.getCreationTime() + (1000 * sessao.getMaxInactiveInterval()) - new Date().getTime()) / 1000;
	}

	/**
	 * @param session
	 * @param login   void
	 */
	public static void activateSession(HttpSession session, String login) {
		SessionTimer timer = new SessionTimer(session);
		Thread thread = new Thread(timer);
		thread.setName("USER: " + login + "ID:" + session.getId());
		session.setAttribute("user", login);
		session.setMaxInactiveInterval(3600);
		session.setAttribute("thread", thread);
		thread.start();
	}
}
