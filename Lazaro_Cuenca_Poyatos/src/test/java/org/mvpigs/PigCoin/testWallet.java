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
		Wallet wallet;
		
		@Test
		public void testAddress() {
			KeyPair pair = GenSig.generateKeyPair();
			PublicKey address = pair.getPublic();
			wallet.setAddress(address);
			assertEquals(address,wallet.getAddress());
		}
		@Test
		public void testSk() {
			KeyPair pair = GenSig.generateKeyPair();
			PrivateKey sKey = pair.getPrivate();
			wallet.setSk(sKey);
			assertEquals(sKey,wallet.getsKey());
		}
	}
}
