/**
 * 
 */
package br.com.coursera.model;

import br.com.coursera.annotations.Column;

/**
 * @author Jose
 *
 */
public class Comentario {
	@Column(nome = "id_comentario", pk = true, id = true, generated = true)
	private int id;
	@Column(nome = "comentario")
	private String comentario;
	@Column(nome = "login")
	private String usuarioLogin;
	@Column(nome="id_topico")
	private int topicoId;

	public int getId() {
		return this.id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public String getUsuarioLogin() {
		return this.usuarioLogin;
	}

	public int getTopicoId() {
		return this.topicoId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public void setTopicoId(int topicoId) {
		this.topicoId = topicoId;
	}

}
