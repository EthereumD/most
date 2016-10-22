package crypto;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Scanner;

public class base64 {

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			System.out.print("請輸入預以Base64編碼 :");
			String input = in.next();
	         // Encode using basic encoder
	         String base64encodedString = Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
	         System.out.println("Base64 編碼 :" + base64encodedString);

	         // Decode
	         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
	         System.out.println("Base64 解碼 : "+new String(base64decodedBytes, "utf-8"));

	      }catch(UnsupportedEncodingException e){
	         System.out.println("Error :"+e.getMessage());
	      }

	}

}
