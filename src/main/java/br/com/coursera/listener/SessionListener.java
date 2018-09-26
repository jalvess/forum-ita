/**
 * 
 */
package br.com.coursera.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Jose
 *
 */
@WebListener(value = "Session Listener")
public class SessionListener implements HttpSessionListener {

	/**
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 * @param se
	 **/
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	/**
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 * @param se
	 **/
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		((Thread) se.getSession().getAttribute("thread")).interrupt();
	}

}
