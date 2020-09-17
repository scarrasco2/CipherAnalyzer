package Main;

public class VigenereCipher {
	static String generateKey(String str, String key) 
	{ 
	    int x = str.length(); 
	  
	    for (int i = 0; ; i++) 
	    { 
	        if (x == i) 
	            i = 0; 
	        if (key.length() == str.length()) 
	            break; 
	        key+=(key.charAt(i)); 
	    } 
	    return key.toUpperCase(); 
	} 
	
	//Assumes Cipher Text is UpperCase 
	static String decryption(String cipherText, String key) {
		String plainText = "";

		for (int i = 0; i < cipherText.length() && i < key.length(); i++) {

			int x = (cipherText.charAt(i) - key.charAt(i) + 26) % 26;

			x += 'A';
			plainText += (char) (x);
		}
		return plainText.toLowerCase();
	}

}
