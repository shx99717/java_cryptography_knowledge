package crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");//SHA-256，MD5
        String testMessage = "this is hash test";
        byte[] input = testMessage.getBytes();
        long startTime = System.currentTimeMillis();
        byte[] digest = messageDigest.digest(input);
        long endTime = System.currentTimeMillis();
        System.out.println("duration is "+ (endTime-startTime));
        Utils.printByte("input",input);
        Utils.printByte("digest",digest);
    }
    
}
