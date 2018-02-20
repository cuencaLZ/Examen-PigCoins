package org.mvpigs.PigCoin;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Before;
import org.junit.Test;
import org.mvpigs.PigCoin.Main.GenSig;
import org.mvpigs.PigCoin.Wallet.Wallet;

public class testWallet {

	public class testClaseWallet {
		Wallet wallet_1;
		Wallet wallet_2;
		
		@Before
		public void testCostructor() {
			wallet_1=new Wallet();
			wallet_1.generateKeyPair();
			
			wallet_2=new Wallet();
			wallet_2.generateKeyPair();
			
		}
		
		@Test
		public void testAddress() {
			KeyPair pair = GenSig.generateKeyPair();
			PublicKey address = pair.getPublic();
			wallet_1.setAddress(address);
			assertEquals(address,wallet_1.getAddress());
		}
		@Test
		public void testSk() {
			KeyPair pair = GenSig.generateKeyPair();
			PrivateKey sKey = pair.getPrivate();
			wallet_1.setSK(sKey);
			assertEquals(sKey,wallet_1.getsKey());
		}
		@Test
		public void testMostrarWallets() {
			System.out.println("wallet_1:" + wallet_1.toString());
			System.out.println("wallet_2:" + wallet_2.toString());
		}
	}
}
