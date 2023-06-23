import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text to be encrypted - ");

        String text = sc.nextLine();

        System.out.print("Enter secret key - ");
        String secretKey = sc.nextLine();

        try{
            encrypt(text, secretKey);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param text
     * @param secretKey
     * @apiNote "Encrypt given text with encryption key provided"
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void encrypt(String text, String secretKey) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        // Key is converted to byte array
        byte[] keyByteArray = new Base64().decode(secretKey.getBytes("UTF-8"));

        // SecretKeySpec is used to construct a SecretKey from a byte array
        SecretKeySpec secretkeySpec = new SecretKeySpec(keyByteArray, "AES");
        Cipher cipher = Cipher.getInstance("aes/ecb/pkcs5padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretkeySpec);

        // pass plain text that is to be encrypt
        String encrypt = (new Base64()).encodeAsString(cipher.doFinal(text.getBytes()));

        // actual key in base64 format
        System.out.println("encrypted string:" + encrypt);
    }
}