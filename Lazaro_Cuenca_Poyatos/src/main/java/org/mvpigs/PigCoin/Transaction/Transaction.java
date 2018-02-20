package org.mvpigs.PigCoin.Transaction;

import java.security.PublicKey;
import java.util.Arrays;

public class Transaction {
	private String hash;
	private String prev_hash;
	private PublicKey pKey_sender;
	private PublicKey p_Key_recipient;
	private double pigcoins;
	private String message;
	private byte[] signature;
	
	public Transaction() {
		
	}
	public Transaction(String hash,String prev_hash,PublicKey p_KeySender,PublicKey p_Key_recipient,int pigcoins,String message) {
		this.hash=hash;
		this.prev_hash=prev_hash;
		this.pKey_sender=p_KeySender;
		this.p_Key_recipient=p_Key_recipient;
		this.pigcoins=pigcoins;
		this.message=message;
		
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPrev_hash() {
		return prev_hash;
	}
	public void setPrev_hash(String prev_hash) {
		this.prev_hash = prev_hash;
	}
	public PublicKey getpKey_sender() {
		return pKey_sender;
	}
	public void setpKey_sender(PublicKey pKey_sender) {
		this.pKey_sender = pKey_sender;
	}
	public PublicKey getP_Key_recipient() {
		return p_Key_recipient;
	}
	public void setP_Key_recipient(PublicKey p_Key_recipient) {
		this.p_Key_recipient = p_Key_recipient;
	}
	public double getPigcoins() {
		return pigcoins;
	}
	public void setPigcoins(double pigcoins) {
		this.pigcoins = pigcoins;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "Transaction [hash=" + hash + 
				", prev_hash=" + prev_hash + 
				", pKey_sender=" + pKey_sender.hashCode()
				+ ", p_Key_recipient=" + p_Key_recipient.hashCode() + 
				", pigcoins=" + pigcoins + 
				", message=" + message
				+ ", signature=" + Arrays.toString(signature) + "]";
	}
	
	
	

}
