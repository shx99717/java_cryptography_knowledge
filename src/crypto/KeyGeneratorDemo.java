package crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyGeneratorDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
      //option1 KeyGenerator
      KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");      
      SecretKey key1 = keyGenerator.generateKey();
      Utils.printByte("key1",key1.getEncoded());
      
      //option2 SecretKeySpec
      byte[] key2 = "123".getBytes();
      SecretKey keySpec = new SecretKeySpec(key2, "DES");
      Utils.printByte("keySpec",keySpec.getEncoded());
      
      //option3 factory class
      byte[] key3 = "1F1F1F1F0E0E0E0E".getBytes();
      DESKeySpec desKeySpec = new DESKeySpec(key3); 
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DES"); 
      SecretKey keySpec3 = factory.generateSecret(desKeySpec) ; 
      Utils.printByte("desKeySpec",keySpec3.getEncoded());

    }

}
