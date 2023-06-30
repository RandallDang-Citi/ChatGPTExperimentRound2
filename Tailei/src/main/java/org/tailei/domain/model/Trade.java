package org.tailei.domain.model;


import java.util.List;
import java.util.stream.Collectors;

public class Trade {

	String clientId;
	String buySell;
	Integer quantity;
	String aon;

	public static List<Trade> filterTrades(List<Trade> trades, String buySell) {
		//filter trades by buySell
		return trades.stream().filter(trade -> trade.getBuySell().equals(buySell)).collect(Collectors.toList());
	}

	//generate get set method
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getBuySell() {
		return buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getAon() {
		return aon;
	}

	public void setAon(String aon) {
		this.aon = aon;
	}

}
