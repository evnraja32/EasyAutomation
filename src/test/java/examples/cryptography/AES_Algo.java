package examples.cryptography;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AES_Algo {
    private static final String ALGORITHM = "RSA";

    public static byte[] encrypt(byte[] publicKey, byte[] inputData) throws Exception {

        PublicKey key = KeyFactory.getInstance(ALGORITHM)
                .generatePublic(new X509EncodedKeySpec(publicKey));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(inputData);

        return encryptedBytes;
    }

    public static byte[] decrypt(byte[] privateKey, byte[] inputData) throws Exception {

        PrivateKey key = KeyFactory.getInstance(ALGORITHM)
                .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedBytes = cipher.doFinal(inputData);

        return decryptedBytes;
    }

    public static KeyPair generateKeyPair()
            throws NoSuchAlgorithmException, NoSuchProviderException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // 512 is keysize
        keyGen.initialize(512, random);

        KeyPair generateKeyPair = keyGen.generateKeyPair();
        return generateKeyPair;
    }

    public static void main1(String[] args) throws Exception {

        KeyPair generateKeyPair = generateKeyPair();

        byte[] publicKey = generateKeyPair.getPublic().getEncoded();
        byte[] privateKey = generateKeyPair.getPrivate().getEncoded();

        byte[] encryptedData = encrypt(publicKey, "CI1=4160210822572122|CI2=153|CI3=ELLURU|CI4=01|CI5=2026".getBytes());

        byte[] decryptedData = decrypt(privateKey, encryptedData);

        System.out.println(new String(decryptedData));
    }


//    public static String getEncrypted(String data, String Key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(Key.getBytes())));
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] encryptedbytes = cipher.doFinal(data.getBytes());
//        return new String(Base64.getEncoder().encode(encryptedbytes));
//    }
//
//    public static String getDecrypted(String data, String Key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        PrivateKey pk = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(Key.getBytes())));
//        cipher.init(Cipher.DECRYPT_MODE, pk);
//        byte[] encryptedbytes = cipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
//        return new String(encryptedbytes);
//    }
//
//    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        // TODO code application logic here
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
//        keyGenerator.init(448);
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(1024);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//
//        String pubKey = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
//        String priKey = new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
//        System.out.println("Public Key:" + pubKey);
//        System.out.println("Private Key:" + priKey);
//        String cipherText = getEncrypted("hi this is a string", pubKey);
//
//        System.out.println("CHIPHER:" + cipherText);
//        String decryptedText = getDecrypted(cipherText, priKey);
//        System.out.println("DECRYPTED STRING:" + decryptedText);
//
//    }


}
