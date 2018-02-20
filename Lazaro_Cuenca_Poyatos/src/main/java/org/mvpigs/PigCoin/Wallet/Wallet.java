package org.mvpigs.PigCoin.Wallet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.mvpigs.PigCoin.Main.GenSig;


public class Wallet {
	private PublicKey address;
	private PrivateKey sKey;
	private double total_input=0;
	private double total_output=0;
	private double balance=0;
	
	public void generateKeyPair() {
		KeyPair Claves= GenSig.generateKeyPair();
		address=Claves.getPublic();
		sKey=Claves.getPrivate();
	}
	public void setAddress(PublicKey pKey) {
		address=pKey;
	}
	public void setSK(PrivateKey sKey) {
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
	public void setBalance() {
		double balance = getTotal_input() - getTotal_output();
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Wallet [address=" + address + ", "
				+ "total_input=" + total_input + 
				", total_output="+ total_output + 
				", balance=" + balance + "]";
	}
	
	
	
	
	

}
