package crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class md5 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		System.out.print("輸入參數 : ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String msg = in.next() ;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(msg.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        System.out.println("輸出 MD5 : " + hashtext);
	}
}
