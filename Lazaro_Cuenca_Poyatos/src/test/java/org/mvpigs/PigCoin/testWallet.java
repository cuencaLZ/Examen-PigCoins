package org.mvpigs.PigCoin;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Before;
import org.junit.Test;
import org.mvpigs.PigCoin.BlockChain.BlockChain;
import org.mvpigs.PigCoin.Main.GenSig;
import org.mvpigs.PigCoin.Transaction.Transaction;
import org.mvpigs.PigCoin.Wallet.Wallet;

public class testWallet {
	Wallet wallet_1;
	Wallet wallet_2;
	BlockChain bChain;

	@Before
	public void testCostructor() {
		wallet_1 = new Wallet();
		wallet_1.generateKeyPair();

		wallet_2 = new Wallet();
		wallet_2.generateKeyPair();
		
		bChain = new BlockChain();

	}

	@Test
	public void testAddress() {
		KeyPair pair = GenSig.generateKeyPair();
		PublicKey address = pair.getPublic();
		wallet_1.setAddress(address);
		assertEquals(address, wallet_1.getAddress());
	}

	@Test
	public void testSk() {
		KeyPair pair = GenSig.generateKeyPair();
		PrivateKey sKey = pair.getPrivate();
		wallet_1.setSK(sKey);
		assertEquals(sKey, wallet_1.getsKey());
	}

	@Test
	public void testMostrarWallets() {
		System.out.println("wallet_1:" + wallet_1.toString());
		System.out.println("wallet_2:" + wallet_2.toString());
	}

	@Test
	public void testLoadCoin() {
		Wallet origin = new Wallet();
		origin.generateKeyPair();

		Transaction trx = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 10, "bacon eggs");
		bChain.addOrigin(trx);
		trx = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 15, "spam spam spam");
		bChain.addOrigin(trx);
		trx = new Transaction("hash_3", "hash_1", wallet_1.getAddress(), wallet_2.getAddress(), 17, "a flying pig!");
		bChain.addOrigin(trx);

		wallet_1.loadCoins(bChain);
		System.out.println(wallet_1.toString());

		wallet_2.loadCoins(bChain);
		System.out.println(wallet_2.toString());
	}
}
