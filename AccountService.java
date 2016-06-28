package com.swadesibank.transaction.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.dao.IAccountDao;

public class AccountService {

	private IAccountDao accountDao;

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public void createAccount(Account account){
		accountDao.insert(account);
	}
	
	public Account getAccount(Integer accountno){
		
		return accountDao.getAccount(accountno);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void fundTransfer(final Account fromAccount,final Account toAccount,Double transferAmount){

			System.out.println("tranferring amount:"+transferAmount+" to the accountno:"+toAccount.getAccountno());
			fromAccount.debit(transferAmount);
			toAccount.credit(transferAmount);
			accountDao.update(fromAccount);
			accountDao.update(toAccount);
			
			System.out.println("Tranfer completed...");
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
	public void fundTransferWithException(final Account fromAccount,final Account toAccount,Double transferAmount) throws Exception{

			System.out.println("tranferring amount:"+transferAmount+" to the accountno:"+toAccount.getAccountno());
			fromAccount.debit(transferAmount);
			toAccount.credit(transferAmount);
			accountDao.update(fromAccount);
			accountDao.update(toAccount);
			System.out.println("Tranfer completed...");//put here debugger point.
			
			//assume exception occurs at below line [before exception you may see result in table in uncommitted mode]
			throw new Exception("Opps!! some exception occurs!!");
			
	}
	
	public void printAccountInfo(Account fromAccount,Account toAccount){
		
		System.out.println("Debited Account No:"+fromAccount.getAccountno()+" Balance:"+fromAccount.getBalance());
		System.out.println("Credited Account No:"+toAccount.getAccountno()+" Balance:"+toAccount.getBalance());
	}
}
