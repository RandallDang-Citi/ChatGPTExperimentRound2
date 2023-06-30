package org.tailei.domain.model;


import java.util.List;
import java.util.stream.Collectors;

public class TradeResp {

	String clientId;
	String matched;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMatched() {
		return matched;
	}

	public void setMatched(String matched) {
		this.matched = matched;
	}
}
