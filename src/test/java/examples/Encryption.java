package examples;


import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Encryption {

    private static final String X509_PEM_HEADER = "-----BEGIN PUBLIC KEY-----";
    private static final String X509_PEM_FOOTER = "-----END PUBLIC KEY-----";
    public final static String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("RSA Public Key to Node-Forge Public Key");

        String myPublicKey = "30819F300D06092A864886F70D010101050003818D0030818902818100D4B505858D2E99BC2E0CA26847EF353D4644E7EE7F364D904F8A2E1ACAA38C981AAEB3F525B4BA230026824429BE7C52901AA3EE8500D8A45E835CDFBE8918AE3B68B0D2588EA5F649B37FA528A7FCC645121AB418FD22690D7DFF9482242C5CC010D2535B7A964EE4A43953CC19C56345E2D00A752D7069DA6724CF850968B30203010001";
        String data = "CI1=4761360074185165|CI2=797|CI3=Raja Evn|CI4=2023|CI5=12";

        try {
            // rsa key generation
//            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
//            kpGen.initialize(2048, new SecureRandom());
//            KeyPair keyPair = kpGen.generateKeyPair();
//            PublicKey publicKey = keyPair.getPublic();
//            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//            System.out.println("publicKeyBase64:\n" + publicKeyBase64);
//
//            // encode public key to PEM format
//            String publicKeyInPemFormat = formatPublicKey(Base64.getDecoder().decode(publicKeyBase64));
//            System.out.println("\npublicKeyInPemFormat:\n" + publicKeyInPemFormat);
//
//            // casting to RSAPublicKey to get the modulus & exponent of the key
//            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
//            System.out.println("\nmodulus:  " + rsaPublicKey.getModulus() + " \nexponent: " + rsaPublicKey.getPublicExponent());
//
//
//            KeyFactory f = KeyFactory.getInstance("RSA");
//            BigInteger exp = new BigInteger("10001", 16);
//            BigInteger modulus = new BigInteger(myPublicKey, 16);
////
//            RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exp);
////        new RSAPublicKeySpec(new BigInteger(Constants.publicKey, 16), new BigInteger("10001", 16))
//            PublicKey pub = f.generatePublic(spec);
////
//            byte[] keyData = pub.getEncoded();
////
//            String encodedString = Base64.getEncoder().encodeToString(keyData);
//            System.out.println("Encoded string: " + encodedString);
//
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(encodedString.getBytes()));
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            PublicKey publicKey = keyFactory.generatePublic(keySpec);
//
//            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
////            new SecureRandom(myPublicKey.getBytes());
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
////            byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
//            byte[] bytes = cipher.doFinal(data.getBytes("ASCII"));
//            String myEcoded = Base64.getEncoder().encodeToString(bytes);
//            System.out.println("output:" + myEcoded);
//
////            for (byte b : bytes){
////                System.out.print(b);
//
//            }
//            System.out.println();


            //Creating a Signature object
//            Signature sign = Signature.getInstance("SHA256withRSA");
//
//            //Creating KeyPair generator object
//            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//
//            //Initializing the key pair generator
//            SecureRandom random = new SecureRandom(myPublicKey.getBytes());
//            keyPairGen.initialize(2048, random);
////            keyPairGen.initialize(2048);
//
//            //Generating the pair of keys
//            KeyPair pair = keyPairGen.generateKeyPair();
//
//            //Creating a Cipher object
////            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            // WD+gDGC7e8p4X9qoCXg7s+DXfN5gq69vPzbwLIlTxkEjOkUn7Xb4rwq+UC+Z4e9HbMNAE3jC1IbLSE1cwZ0m1j/O5AMr0Q4yzbPTBhpNJHI2qtxKHseqM/Tx1qduox0wzhdQs3NZ5Ps37OmyhpR83AhizCcPi27+NlMqKm2ia/KmzjR9i9kDYovBLVPqQhAShiEIoX9o/jOvpHkSTzBxmy7lgD51HgIyZxbpBqu8nXZ/aXRAIVBE9wJJzG3U9RKsJ3SCq2+S5cxUhACIeNw1QM0oPzSKjjAt/ZKZyNOAgi+oUmjVqo0sfP8wb7wdpncKkm1JynmHVhh+hycy0KlgXw==
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//
//            //Initializing a Cipher object
//            cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
//
//            //Adding data to the cipher
//            byte[] input = data.getBytes();
//            cipher.update(input);
//
//            //encrypting the data
//            byte[] cipherText = cipher.doFinal();
////            System.out.println(new String(cipherText, "ASCII"));
//            System.out.println();
//            System.out.println(Base64.getEncoder().encodeToString(cipherText));


            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = data.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
//            FileInputStream fin = new FileInputStream(new File("/test.cer"));
            CertificateFactory f = CertificateFactory.getInstance("X.509");
//            X509Certificate certificate = (X509Certificate)f.generateC
//            PublicKey pk = certificate.getPublicKey();
//            cipher.init(Cipher.ENCRYPT_MODE, pk, new SecureRandom());
            byte[] cipherText = cipher.doFinal(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatPublicKey(byte[] encodedKey)  {
        final Base64.Encoder encoder = Base64.getMimeEncoder(64, LINE_SEPARATOR.getBytes());
        final String encodedText = new String(encoder.encode(encodedKey));
        final String prettified_key = X509_PEM_HEADER + LINE_SEPARATOR + encodedText + LINE_SEPARATOR + X509_PEM_FOOTER;
        return prettified_key;
    }
}
