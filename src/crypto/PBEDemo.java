package crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PBEDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        byte[] salt = "12345618".getBytes();
        int iterations = 1000;
        PBEKeySpec pbeKeySpec = new PBEKeySpec("password".toCharArray()) ;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES") ;//PBEWITHSHAAND256BITAES-CBC-BC
        SecretKey key = factory.generateSecret(pbeKeySpec) ; 
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES") ;
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, iterations);
        cipher.init(Cipher.ENCRYPT_MODE, key,pbeps);
        byte[] cipherText = cipher.doFinal("this is message".getBytes());
        Utils.printByte("bcp cipher text", cipherText);
        cipher.init(Cipher.DECRYPT_MODE, key,pbeps);
        byte[] decipherText = cipher.doFinal(cipherText);
        Utils.printByte("bcp decipher text", decipherText);
    }

}
