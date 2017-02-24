package br.com.nx.tickets.componente;

import javax.enterprise.inject.Model;

import br.com.nx.tickets.util.Util;

@Model
public class Credencial {

	private String username;
	private String password;

	public Credencial() {
		username = "NXADMIN";
		password = "NXADMIN";
	}

	public Credencial(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		// if (password != null) {/*
		// return Util.criptografarString(password);
		// } else {*/
		return password;
		// }
	}

	public String getPasswordDescriptografado() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}