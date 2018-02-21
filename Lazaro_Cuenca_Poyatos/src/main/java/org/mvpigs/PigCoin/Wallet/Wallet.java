package org.mvpigs.PigCoin.Wallet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Iterator;

import org.mvpigs.PigCoin.BlockChain.BlockChain;
import org.mvpigs.PigCoin.Main.GenSig;
import org.mvpigs.PigCoin.Transaction.Transaction;


public class Wallet {
	private PublicKey address;
	private PrivateKey sKey;
	private double total_input=0;
	private double total_output=0;
	private double balance=0;
	private ArrayList<Transaction> inputTransactions = new ArrayList<Transaction>();;
	private ArrayList<Transaction> outputTransactions = new ArrayList<Transaction>();
	
	
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
	public void loadCoins(BlockChain bChain) {
		for (Transaction t : bChain.getBlockChain()) {
			if (t.getP_Key_recipient().equals(address)) {//Transaccion que recibimos
				total_input = total_input + t.getPigcoins();

			} else if (t.getpKey_sender().equals(address)) {//Transaccion que enviamos nosotros
				total_output = total_output + t.getPigcoins();
				if (total_output > total_input) {
					total_output = total_output - t.getPigcoins();
				}


			}
			setBalance();
		}
	}

	public Map collectCoins(double pigcoins){
		double pig_aux=0d; //variable auxiliar de pigcoins
		Map<String,Double> consumidas=new HashMap<>();
		ArrayList<Transaction> trx=new ArrayList<>(); //Guardaremos aquí las transacciones usadas y posteriormente poderlas marcar
		Iterator<Transaction> it=inputTransactions.iterator();
		while(it.hasNext()&&pig_aux<pigcoins){
			Transaction t=it.next();
			//Miramos que no haya sido consumida con anterioridad
			if(!t.isConsumida()) {
				pig_aux += t.getPigcoins();
				trx.add(t); //Añadimos la transaccion a nuestra lista de transacciones utilizadas
			}
		}
		// Caso especial, tenemos que dividir las transacciones
		if(pig_aux>pigcoins){
			Transaction ultima=trx.get(trx.size()-1); //Miramos solo la última, que es la que ha desbordado(o tiene mas pigcoins de los que vamos a enviar)
			Transaction dest=new Transaction("hashing","prev_hashing_34",this.address,ultima.getP_Key_recipient(),pigcoins,"Transacion");
			//Nos mandamos una transaccion a nosotros mismos
			Transaction mia=new Transaction("hashing_2","prev_hashing_2",this.address,this.address,pig_aux-pigcoins,"Transacion de vuelta");
			//Eliminamos la transaccion que hemos dividido
			inputTransactions.remove(trx.indexOf(ultima)); 
			//Añadimos la transaccion de lo que resta
			inputTransactions.add(mia);
			//Añadimos la transaccion de salida
			outputTransactions.add(dest);
		}else if(pig_aux<pigcoins){
			return null; //No hacemos nada
		}
		// Aqui solo entraremos si el envio de pigcoins se puede realizar
		for(Transaction t:trx){
			t.setConsumida(true); //Marcamos la transaccion como consumida
			consumidas.put(t.toString(),t.getPigcoins());//Guardamos la transaccion y los picoins de la misma
		}

		return consumidas;


	}
	public void loadInputTransactions(BlockChain bChain) {
		for(Transaction t:bChain.getBlockChain()) {
			if(t.getP_Key_recipient().equals(address)) {//Transacción que recibimos
				inputTransactions.add(t);
			}
		}
	}
	public void loadOutputTransactions(BlockChain bChain) {
		for(Transaction t:bChain.getBlockChain()) {
			if(t.getpKey_sender().equals(address)) {//Transaccion que enviamos
				outputTransactions.add(t);
			}
		}
	}
	@Override
	public String toString() {
		return "\n"+"Wallet [address=" + address.hashCode() +"\n"+ " "
				+ "total_input=" + total_input +"\n"+ 
				" total_output="+ total_output +"\n"+
				" balance=" + balance + "]"+"\n";
	}
	public ArrayList<Transaction> getInputTransactions() {
		return inputTransactions;
	}
	public void setInputTransactions(ArrayList<Transaction> inputTransactions) {
		this.inputTransactions = inputTransactions;
	}
	public ArrayList<Transaction> getOutputTransactions() {
		return outputTransactions;
	}
	public void setOutputTransactions(ArrayList<Transaction> outputTransactions) {
		this.outputTransactions = outputTransactions;
	}
	public byte[] signTransaction(String message) {
		return GenSig.sign(sKey, message);
	}
	public void sendCoins(PublicKey pKey_recipient, double coins, String message, BlockChain bChain){
	       Transaction trx=new Transaction();
	       trx.setMessage(message);
	       trx.setPigcoins(coins);
	       trx.setP_Key_recipient(pKey_recipient);
	       trx.setpKey_sender(address);
	       trx.setSignature(GenSig.sign(sKey,message));
	       bChain.processTransactions(address,pKey_recipient,coins,trx);
	       total_output=total_output+coins; //Actualizamos total de enviados
	   }
	
	
	
	
	

}
