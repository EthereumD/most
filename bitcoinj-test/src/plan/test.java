package plan;

import java.io.File;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.Wallet;
public class test {
	public static void main(String[] args) {
		
		 NetworkParameters params = MainNetParams.get();
		 Wallet wallet = new Wallet(params);
		 
		 Address a = wallet.currentReceiveAddress();
		 ECKey b = wallet.currentReceiveKey();
		 Address c = wallet.freshReceiveAddress();

		 System.out.println(a.toString());
		 System.out.println(b.toString());
		 System.out.println(c.toString());
		 
		 
}
