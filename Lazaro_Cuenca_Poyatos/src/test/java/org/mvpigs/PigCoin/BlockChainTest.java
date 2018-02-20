package org.mvpigs.PigCoin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mvpigs.PigCoin.BlockChain.BlockChain;
import org.mvpigs.PigCoin.Transaction.Transaction;
import org.mvpigs.PigCoin.Wallet.Wallet;

import junit.framework.Assert;

public class BlockChainTest {
	@Test
	public void TestBlockChain (){
		 Wallet origin = new Wallet();
	       origin.generateKeyPair();
	       Wallet wallet_1 = new Wallet();
	       wallet_1.generateKeyPair();
	       Wallet wallet_2 = new Wallet();
	       wallet_2.generateKeyPair();

	       BlockChain bChain = new BlockChain();
	       Transaction trx = new Transaction("hash_1", "0", origin.getAddress(), wallet_1.getAddress(), 20.0, "bacon eggs");
	       bChain.addOrigin(trx);
	       trx = new Transaction("hash_2", "1", origin.getAddress(), wallet_2.getAddress(), 10.0, "spam spam spam");
	       bChain.addOrigin(trx);
	       trx = new Transaction("hash_3", "hash_1", wallet_1.getAddress(), wallet_2.getAddress(), 20.0, "a flying pig!");
	       bChain.addOrigin(trx);
	       
	       assertNotNull(bChain.getBlockChain().get(1));//Comprobamos que en la posicion 1 no esta null y por tanto se a agregado trx hash_2

	}
			      
			    

}