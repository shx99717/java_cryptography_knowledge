package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

import javax.crypto.SecretKey;

public class ListProviders {
    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException
    {
        Provider[] installedProvs = Security.getProviders();

        for (int i = 0; i != installedProvs.length; i++)
        {
            System.out.print(installedProvs[i].getName());
            System.out.print(": ");
            System.out.print(installedProvs[i].getInfo());
            System.out.println();
            for(Service service:installedProvs[i].getServices()) {
                System.out.print(service.getAlgorithm()+" ");
            }
            System.out.println();
        }
        Provider provider = Security.getProvider("BC"); 
        System.out.println(provider); 
        //Utils.printByte("digest",computeDigest("RipeMD160", "Hello World!".getBytes()));
    }
    public static byte[] computeDigest(String digestName, byte[] data)
            throws NoSuchProviderException, NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(digestName, "BC");

        digest.update(data);

        return digest.digest();
    }
    
}
