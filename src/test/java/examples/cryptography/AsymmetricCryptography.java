package examples.cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AsymmetricCryptography {
    private Cipher cipher;

    public AsymmetricCryptography() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
    public PrivateKey getPrivate(String filename) throws Exception {
//        String myKey = "30819F300D06092A864886F70D010101050003818D0030818902818100D4B505858D2E99BC2E0CA26847EF353D4644E7EE7F364D904F8A2E1ACAA38C981AAEB3F525B4BA230026824429BE7C52901AA3EE8500D8A45E835CDFBE8918AE3B68B0D2588EA5F649B37FA528A7FCC645121AB418FD22690D7DFF9482242C5CC010D2535B7A964EE4A43953CC19C56345E2D00A752D7069DA6724CF850968B30203010001";
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(myKey.getBytes());
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
    public PublicKey getPublic(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
//        byte[] keyBytes = "30819F300D06092A864886F70D010101050003818D0030818902818100D4B505858D2E99BC2E0CA26847EF353D4644E7EE7F364D904F8A2E1ACAA38C981AAEB3F525B4BA230026824429BE7C52901AA3EE8500D8A45E835CDFBE8918AE3B68B0D2588EA5F649B37FA528A7FCC645121AB418FD22690D7DFF9482242C5CC010D2535B7A964EE4A43953CC19C56345E2D00A752D7069DA6724CF850968B30203010001".getBytes();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public void encryptFile(byte[] input, File output, PrivateKey key)
            throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    public void decryptFile(byte[] input, File output, PublicKey key)
            throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    private void writeToFile(File output, byte[] toWrite)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    public String encryptText(String msg, PrivateKey key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
    }

    public String decryptText(String msg, PublicKey key)
            throws InvalidKeyException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
//        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "ASCII");
    }

    public byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }

    public static void main(String[] args) throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
        PublicKey publicKey = ac.getPublic("KeyPair/publicKey");

        String msg = "Cryptography is fun!";
        System.out.println("Private key: " + privateKey);
        System.out.println("Public key: " + publicKey);
        String encrypted_msg = ac.encryptText("CI1=4160210822572122|CI2=153|CI3=Raja Evn|CI4=01|CI5=2026", privateKey);
//        String decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
        String decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
        System.out.println("Original Message: " + msg +
                "\nEncrypted Message: " + encrypted_msg
                + "\nDecrypted Message: " + decrypted_msg);

        if (new File("KeyPair/text.txt").exists()) {
            ac.encryptFile(ac.getFileInBytes(new File("KeyPair/text.txt")),
                    new File("KeyPair/text_encrypted.txt"),privateKey);
            ac.decryptFile(ac.getFileInBytes(new File("KeyPair/text_encrypted.txt")),
                    new File("KeyPair/text_decrypted.txt"), publicKey);
        } else {
            System.out.println("Create a file text.txt under folder KeyPair");
        }
    }
}