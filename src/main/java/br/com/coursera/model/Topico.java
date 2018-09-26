/**
 * 
 */
package br.com.coursera.model;

import br.com.coursera.annotations.Column;

/**
 * @author Jose
 *
 */
public class Topico {
	@Column(nome = "id_topico", generated = true, id = true, pk = true)
	private int id;
	@Column(nome = "titulo")
	private String titulo;
	@Column(nome = "conteudo")
	private String conteudo;
	@Column(nome = "login")
	private String usuarioLogin;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getConteudo() {
		return this.conteudo;
	}

	public String getUsuarioLogin() {
		return this.usuarioLogin;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	@Override
	public String toString() {
		return "Topico [id=" + this.id + ", titulo=" + this.titulo + ", usuarioLogin=" + this.usuarioLogin + "]";
	}

}
