package org.mvpigs.PigCoin.BlockChain;

import java.security.PublicKey;
import java.util.ArrayList;

import org.mvpigs.PigCoin.Main.GenSig;
import org.mvpigs.PigCoin.Transaction.Transaction;

public class BlockChain {
	private ArrayList<Transaction> blockChain = new ArrayList<Transaction>();
	
	public BlockChain() {
	}
	
	public void addOrigin(Transaction transaction) {
		blockChain.add(transaction);
	}
	public void processTransactions(PublicKey pKey_sender,PublicKey pKey_recipient,double consumedCoins,Transaction signedTransaction) {
		Transaction trasferencia= new Transaction();
		if (GenSig.verify(pKey_sender, signedTransaction.getMessage(), signedTransaction.getSignature())) {
			blockChain.add(signedTransaction);
		}
	}
	public void loadWallet(PublicKey address) {
		
	}
	public ArrayList<Transaction> getBlockChain(){
		return blockChain;
	}
	public void summarize() {
		System.out.println(blockChain);
	}
	public void summarize(int index) {
		System.out.println(blockChain.get(index));
	}

}
