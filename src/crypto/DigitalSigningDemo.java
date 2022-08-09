package crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DigitalSigningDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SignatureException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Utils.printByte("public key", keyPair.getPublic().getEncoded());
        Utils.printByte("private key", keyPair.getPrivate().getEncoded());
        
        byte[] text = "rsa test".getBytes();//text cannot be larger than 117 bytes
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(text);
        byte[] signatureText = signature.sign();
        Utils.printByte("signatureText", signatureText);

        Signature verifySignature = Signature.getInstance("SHA256WithRSA");
        verifySignature.initVerify(keyPair.getPublic());
        verifySignature.update(text);
        boolean match = verifySignature.verify(signatureText);
        System.out.println("signature match "+match);
    }
}
