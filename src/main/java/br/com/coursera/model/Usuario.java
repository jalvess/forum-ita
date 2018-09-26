/**
 * 
 */
package br.com.coursera.model;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.coursera.annotations.Column;

/**
 * @author Jose
 *
 */
public class Usuario {
	@Column(nome = "login", id = true, pk = true)
	private String login;
	@Column(nome = "nome")
	private String nome;
	@Column(nome = "email")
	private String email;
	@Column(nome = "senha", id = true)
	private String senha;
	@Column(nome = "pontos")
	private int pontos;

	public String getLogin() {
		return this.login;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getSenha() {
		return DigestUtils.md5Hex(this.senha);
	}

	public int getPontos() {
		return this.pontos;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setPontos(int i) {
		this.pontos = i;
	}

}
