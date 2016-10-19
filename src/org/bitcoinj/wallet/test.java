package org.bitcoinj.wallet;

import org.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Address;

public class test {
	public static void main(String[] args){
		Wallet wallet = new Wallet(params);
		Address a = wallet.currentReceiveAddress();
		
	}
}
