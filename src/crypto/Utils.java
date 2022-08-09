package crypto;

import java.math.BigInteger;
import java.util.Arrays;

public class Utils {
    public static void printByte(String title,byte[] data) {
        System.out.println(title+" "+new String(data));
        String hexString = new BigInteger(1,data).toString(16);
        System.out.print(Arrays.toString(data));
        System.out.println(data.length+" bytes, "+data.length*8+ "bits");
        System.out.println(hexString);
    }

}
