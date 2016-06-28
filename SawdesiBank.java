package com.swadesibank.transaction.client;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.service.AccountService;

public class SawdesiBank {

	public static void main(String[] args) {
		
		ApplicationContext appContext=new ClassPathXmlApplicationContext("spring-config.xml");
		AccountService accountService=appContext.getBean("accountService",AccountService.class);
		
		//Create Account:
		Account anilkumar=new Account();
		anilkumar.setAccountno(10001);
		anilkumar.setBalance(15000.0);

		Account rajivverma=new Account();
		rajivverma.setAccountno(10002);
		rajivverma.setBalance(25000.0);
		
		Account priyagill=new Account();
		priyagill.setAccountno(10003);
		priyagill.setBalance(12502.0);
		
		/*accountService.createAccount(anilkumar);
		accountService.createAccount(rajivverma);
		accountService.createAccount(priyagill);*/
		
		//Transferring funds..
		Account fromAccount=null;
		Account toAccount=null;
		
		//----------------------------User Input ------------------
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Debit Account No:");
		Integer debitAccount=scan.nextInt();
		System.out.println("Enter credit Account No:");
		Integer creditAccount=scan.nextInt();
		//---------------------------------------------------------
		
		fromAccount=accountService.getAccount(debitAccount);
		toAccount=accountService.getAccount(creditAccount);
		
		Double Amt=2000.00;
		
		accountService.fundTransfer(fromAccount, toAccount, Amt);
		
		fromAccount=accountService.getAccount(debitAccount);
		toAccount=accountService.getAccount(creditAccount);
		
		accountService.printAccountInfo(fromAccount, toAccount);
		
		
		Double transferAmt=7000.00;
		try {
			accountService.fundTransferWithException(fromAccount, toAccount, transferAmt);
		} catch (Exception e) {
			System.out.println("Fund tranfer failed..transaction rollbacked by transaction manager..");
			e.printStackTrace();
		}
		
		fromAccount=accountService.getAccount(debitAccount);
		toAccount=accountService.getAccount(creditAccount);
		
		accountService.printAccountInfo(fromAccount, toAccount);
		
	}

}
