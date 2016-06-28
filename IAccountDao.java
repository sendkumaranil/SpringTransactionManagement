package com.swadesibank.transaction.dao;

import com.swadesibank.transaction.bean.Account;

public interface IAccountDao {

	public void insert(Account account);
	public void update(Account account);
	public Account getAccount(Integer accountno);
}
