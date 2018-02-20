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
	private double balance;
	
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
	public double getTotal_input() {
		return total_input;
	}
	public void setTotal_input(double total_input) {
		this.total_input = total_input;
	}
	public double getTotal_output() {
		return total_output;
	}
	public void setTotal_output(double total_output) {
		this.total_output = total_output;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Wallet [Address=" + getAddress() + 
				", Total_input="+ getTotal_input() + 
				", Total_output=" + getTotal_output() + 
				", Balance=" + getBalance()
				+ "]";
	}
	
	
	
	

}
