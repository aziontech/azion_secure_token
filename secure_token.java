package azion_secure_token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class secure_token {

	
	public static void main(String[] args) throws NoSuchAlgorithmException {
    	
		String secretKey = "mysecret";	
		String uri = "/my/uri";
		long expire = 1470055000;
		
		
		String aValidar = secretKey + uri + expire;
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(aValidar.getBytes());
		
		String token = new BASE64Encoder().encode(md.digest()).replace("=","").replace("+","-").replace("/","_");
		
		System.out.printf("http://www.example.org%s?st=%s&e=%s",uri,token,expire);
	}
}
