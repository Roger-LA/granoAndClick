package com.granoAndClick.granoAndClick.dto;

public class Token {
	private String accessToken;

	public Token(String accessToken) {
		super();
		this.accessToken = accessToken;
	}//Constructor

	public String getAccessToken() {
		return accessToken;
	}//GetAccess
}
