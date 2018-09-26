/**
 * 
 */
package br.com.coursera.async;

import javax.servlet.http.HttpSession;

/**
 * @author Jose
 *
 */
public class SessionTimer implements Runnable {
	private HttpSession sessao;

	/**
	 * 
	 */
	public SessionTimer(HttpSession sessao) {
		this.sessao = sessao;
	}

	/**
	 * @see java.lang.Runnable#run()
	 **/
	@Override
	public void run() {
		try {
			Thread.sleep(sessao.getMaxInactiveInterval() * 1000);
			sessao.invalidate();
		} catch (InterruptedException e) {
			System.out.println("Sessão interrompida pelo usuário");
		}
	}
}
