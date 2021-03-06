import java.io.*;
import java.security.*;
import java.math.*;
import cryptix.util.core.BI;
import cryptix.util.core.ArrayUtil;
import cryptix.util.core.Hex;
import cryptix.provider.key.*;
import xjava.security.*;

class testDES {

public static void main (String[] args) {

	try {
	FileOutputStream outFile1 = new FileOutputStream("DES.out");
	// Note: PrintStream is deprecated, but still works fine in jdk1.1.7b
	PrintStream output1 = new PrintStream(outFile1);

	// convert a string to a DES key and print out the result
	RawSecretKey key2 = new RawSecretKey("DES",Hex.fromString("3812A419C63BE771"));
	RawKey rkey = (RawKey) key2;
	byte[] yval = rkey.getEncoded();
	BigInteger Bkey = new BigInteger(yval);
	String w = cryptix.util.core.BI.dumpString(Bkey);
	output1.println("The Encryption Key = " + w);
	
	// use the DES key to encrypt a string
	Cipher des=Cipher.getInstance("DES/ECB/NONE","Cryptix");
	des.initEncrypt(key2);	
	byte[] ciphertext = des.crypt(Hex.fromString("01010101010101010102030405060708090A0B0C0D0E0F101112131415161718"));

	// print out length and representation of ciphertext 
	output1.print("\n");
	output1.println("ciphertext.length = " + ciphertext.length);

	BigInteger Bciph = new BigInteger(ciphertext);
	w = cryptix.util.core.BI.dumpString(Bciph);
	output1.println("Ciphertext for DES encryption = " + w);
	
	// decrypt ciphertext 
	des.initDecrypt(key2);
	ciphertext = des.crypt(ciphertext);
	output1.print("\n");
	output1.println("plaintext.length = " + ciphertext.length);

	// print out representation of decrypted ciphertext 
	Bciph = new BigInteger(ciphertext);
	w = cryptix.util.core.BI.dumpString(Bciph);
	output1.println("Plaintext for DES encryption = " + w);

	output1.println(" ");
	output1.close();		

      } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
      }

	}}


