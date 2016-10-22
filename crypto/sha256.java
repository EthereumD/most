package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class sha256 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// 輸入
		System.out.print("輸入參數 : ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String password = in.next();
		//前置處理
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //主要
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
    	//輸出
    	System.out.println("輸出 SHA-256 : " + sb.toString());
	}
}
