package org.tailei.domain.model;


public class TokenValidateResult extends BaseModel{

	String status;
	Token token;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
}
