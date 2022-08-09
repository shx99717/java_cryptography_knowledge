package crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Utils.printByte("public key", keyPair.getPublic().getEncoded());
        Utils.printByte("private key", keyPair.getPrivate().getEncoded());
        
        
        byte[] text = "rsa test".getBytes();//text cannot be larger than 117 bytes
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        
        byte[] encriptText = cipher.doFinal(text);
        Utils.printByte("encriptText", encriptText);
        
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decriptText = cipher.doFinal(encriptText);
        Utils.printByte("decriptText", decriptText);

    }

}
