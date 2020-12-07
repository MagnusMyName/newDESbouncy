
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Main {

    public static void main(String[] args) {
        String yourOwnKey = "ASDFGHJKLZXCVBNMQWERTYUI", originalText = "Give your text to encrypt.";
        encrypt(encrypt(originalText, yourOwnKey), yourOwnKey);
    }

    public static String encrypt(String originalText, String yourOwnKey) {
        String encryptedText = null;
        byte[] bytes = null;

        try {
            bytes = originalText.getBytes();
            bytes = Arrays.copyOf(bytes, ((bytes.length + 7) / 8) * 8);

            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            SecretKey key = new SecretKeySpec(yourOwnKey.getBytes(), "DESede");
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);

            cipher.init(Cipher.ENCRYPT_MODE, (Key) key, iv);
            byte[] encryptByte = cipher.doFinal(bytes);
            encryptedText = new String(Hex.encodeHex(encryptByte));

            System.out.println("originalText: " + originalText + " --> encryptedText: " + encryptedText);

        } catch (Exception e) {
            //throw || log
        }
        return encryptedText;
    }
}
