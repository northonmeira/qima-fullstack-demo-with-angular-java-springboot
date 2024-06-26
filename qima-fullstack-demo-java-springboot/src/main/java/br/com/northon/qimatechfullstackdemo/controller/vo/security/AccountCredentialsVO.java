package br.com.northon.qimatechfullstackdemo.controller.vo.security;

import java.io.Serializable;

public class AccountCredentialsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public AccountCredentialsVO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AccountCredentialsVO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
