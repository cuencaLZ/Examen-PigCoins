package org.mvpigs.PigCoin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mvpigs.PigCoin.Transaction.Transaction;
import org.mvpigs.PigCoin.Wallet.Wallet;

public class TransactionTest {
	Wallet wallet_1;
	Wallet wallet_2;
	Transaction trx;
	
	@Before
	public void Testcostructores(){
		wallet_1=new Wallet();
		wallet_1.generateKeyPair();
		
		wallet_2=new Wallet();
		wallet_2.generateKeyPair();
		
		Transaction trx =new Transaction();
		trx = new Transaction("hash_1", "0", wallet_1.getAddress(), wallet_2.getAddress(), 20, "a flying pig!");
		
	}
	@Test
	public void TestCrearTransaccion() {
		System.out.println(trx.toString());
		
	}
	
}
