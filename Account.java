package com.swadesibank.transaction.bean;

public class Account {

	private Integer accountno;
	private Double balance;
	
	public Integer getAccountno() {
		return accountno;
	}
	public void setAccountno(Integer accountno) {
		this.accountno = accountno;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public void debit(Double amount){
		this.balance=balance-amount;
	}
	
	public void credit(Double amount){
		this.balance=balance+amount;
	}
}
