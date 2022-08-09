package crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CreateNewKeystore {

    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore store = KeyStore.getInstance("BKS") ;//JKS by default
        
        store.load(null, new char[0]);
        
        FileOutputStream outputStream = new FileOutputStream(new File("./jcebook.keystore"));
        
        char[] password = new char[] {'t','e','s','t'};
        
        KeyGenerator deskeyGenerator=KeyGenerator.getInstance("DES");
        SecretKey dessecretKey=deskeyGenerator.generateKey();
        store.setKeyEntry("myDESKey", dessecretKey, password, null) ; 
        
        KeyGenerator aeskeyGenerator=KeyGenerator.getInstance("AES");
        SecretKey aessecretKey=aeskeyGenerator.generateKey();
        store.setKeyEntry("myAESKey", aessecretKey, password, null);
        
        store.store(outputStream, password);
        Arrays.fill(password, '\u0000');
        System.out.println("Key 'myDESKey' and 'myAESKey' stored and written to disk."); 
        
    }

}
