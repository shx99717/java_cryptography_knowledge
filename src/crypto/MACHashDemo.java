package crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class MACHashDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");

        SecretKey key = kg.generateKey();

        Mac mac = Mac.getInstance("HmacMD5");

        mac.init(key);

        byte[] output = mac.doFinal("this is hash test".getBytes());
        Utils.printByte("hmacmd5", output);
    }

}
