package org.mvpigs.PigCoin.Wallet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.mvpigs.PigCoin.Main.GenSig;


public class Wallet {
	private PublicKey address;
	private PrivateKey sKey;
	private double total_input;
	private double total_output;
	private double blance;
	
	public void generateKeyPair() {
		KeyPair Claves= GenSig.generateKeyPair();
		address=Claves.getPublic();
		sKey=Claves.getPrivate();
	}
	public void setAddress(PublicKey pKey) {
		address=pKey;
	}
	public void setSk(PrivateKey sKey) {
		this.sKey=sKey;
	}
	public PublicKey getAddress() {
		return address;
	}
	public PrivateKey getsKey() {
		return sKey;
	}
	

}
