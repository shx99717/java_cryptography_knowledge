package crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class AesDemo {
    public static void main(String[] args) throws Exception {
       
        cfb();
    }
    public static void ecb() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        
        byte[] key = "1234567890abcdef".getBytes();
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        Utils.printByte("keySpec",keySpec.getEncoded());
        byte[] input = "this is AES test".getBytes();

        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        
        byte[] encriptOutput = cipher.doFinal(input);
        Utils.printByte("encriptOutput",encriptOutput);
        
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decriptOutput = cipher.doFinal(encriptOutput);
        Utils.printByte("decriptOutput",decriptOutput);

    }
    public static void cbc() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        

        byte[] key = "1234567890abcdef".getBytes();
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        Utils.printByte("keySpec",keySpec.getEncoded());
        byte[] input = "this is AES test".getBytes();
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecureRandom secureRandom =SecureRandom.getInstanceStrong();
        byte[] random = new byte[16];
        secureRandom.nextBytes(random);
        Utils.printByte("random", random);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(random);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
        
        byte[] encriptOutput = cipher.doFinal(input);
        Utils.printByte("encriptOutput",encriptOutput);
        
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] decriptOutput = cipher.doFinal(encriptOutput);
        Utils.printByte("decriptOutput",decriptOutput);

    }
    public static void cfb() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException {
        byte[] key = "1234567890abcdef".getBytes();
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        Utils.printByte("keySpec",keySpec.getEncoded());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        CipherInputStream cis = new CipherInputStream(new FileInputStream("ciphertext.txt"),cipher);
        FileOutputStream fos = new FileOutputStream(new File("decrypted_ciphertext. txt"));
        
        byte[] plainTextBytes = new byte[8]; 
        int i=0; 
        while( (i = cis.read(plainTextBytes )) !=-1) 
        { 
        fos.write(plainTextBytes, 0, i) ; 
        } 
        fos.close(); 
    }
    
}
