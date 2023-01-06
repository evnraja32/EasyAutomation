package examples.cryptography;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionManager {
    private PublicKey publicKey;

    private static final String PUBLIC_KEY_STRING =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYQYM3FwZuQNLK7xRbuBbAcviy1vl9LB//Ubz+NFSBjgrrGNPwqgOuWF1qskBOY0AnoAZpwHlWEryvtz1OGNX5q9boqrhOrGQebJfek9JGvjysz3+KCqAIup8C1Enp4+cUxYy7BRorjF6wqTAjyJn/SQVZPPUyVeN17Nw0jSqI9QIDAQAB";


    public static void mainMethod(String[] args) {
        // Base64.getEncoder().encodeToString(hex.getBytes())

        /*
        -----BEGIN PUBLIC KEY-----\nMzA4MTlGMzAwRDA2MDkyQTg2NDg4NkY3MEQwMTAxMDEwNTAwMDM4MThEMDAzMDgxODkwMjgxODEwMEQ0QjUwNTg1OEQyRTk5QkMyRTBDQTI2ODQ3RUYzNTNENDY0NEU3RUU3RjM2NEQ5MDRGOEEyRTFBQ0FBMzhDOTgxQUFFQjNGNTI1QjRCQTIzMDAyNjgyNDQyOUJFN0M1MjkwMUFBM0VFODUwMEQ4QTQ1RTgzNUNERkJFODkxOEFFM0I2OEIwRDI1ODhFQTVGNjQ5QjM3RkE1MjhBN0ZDQzY0NTEyMUFCNDE4RkQyMjY5MEQ3REZGOTQ4MjI0MkM1Q0MwMTBEMjUzNUI3QTk2NEVFNEE0Mzk1M0NDMTlDNTYzNDVFMkQwMEE3NTJENzA2OURBNjcyNENGODUwOTY4QjMwMjAzMDEwMDAx\n-----END PUBLIC KEY-----
         */
    }

    public void initFromStrings(){
        try{
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpecPublic);
        }catch (Exception ignored){}
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }
}
//    view raw