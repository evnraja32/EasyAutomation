package examples.cryptography;

import javax.crypto.Cipher;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Pattern;

public class PublicKeyEncryption {
    //https://gustavopeiretti.com/rsa-encrypt-decrypt-java/
    public static void main(String[] args) {
        try {
            System.out.println(new PublicKeyEncryption().encode("CI1=4160210822572122|CI2=153|CI3=ELLURU|CI4=01|CI5=2026"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PublicKey loadPublicKey_org() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = getClass().getResourceAsStream("/key.pub").readAllBytes();
        KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = publicKeyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }

    private PublicKey loadPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // reading from resource folder
//        byte[] publicKeyBytes = getClass().getResourceAsStream("/key.pub").readAllBytes();
        String hex = "30819F300D06092A864886F70D010101050003818D0030818902818100D4B505858D2E99BC2E0CA26847EF353D4644E7EE7F364D904F8A2E1ACAA38C981AAEB3F525B4BA230026824429BE7C52901AA3EE8500D8A45E835CDFBE8918AE3B68B0D2588EA5F649B37FA528A7FCC645121AB418FD22690D7DFF9482242C5CC010D2535B7A964EE4A43953CC19C56345E2D00A752D7069DA6724CF850968B30203010001";
        String pemText = convertHexToPEM(hex);
//        byte[] publicKeyBytes = pemText.getBytes();
//        String pemFormat = "-----BEGIN PUBLIC KEY-----\n" + pemText + "\n-----END PUBLIC KEY-----";
//        byte[] publicKeyBytes = pemFormat.getBytes();


        KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");

        BigInteger exp = new BigInteger("10001", 16);
        BigInteger modulus = new BigInteger(hex, 16);

        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exp);
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(pemText));
        PublicKey publicKey = publicKeyFactory.generatePublic(spec);
        return publicKey;
    }


    //    Now, with this public key you can encrypt. In this case, you are going to encrypt a text.
    public String encode(String toEncode) throws Exception {

        PublicKey publicKey = loadPublicKey();

//        Cipher cipher = Cipher.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(bytes));
    }

    private static String convertHexToPEM(String hexPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {

        String buffer = "";
        BigInteger hex;
        String output = null;

        for (int i = 0; i < hexPublicKey.length(); i++) {
            if (isHex(String.valueOf(hexPublicKey.charAt(i)))) {
                buffer += hexPublicKey.charAt(i);
            }

            if (buffer.length() == 2) {
                hex = new BigInteger(buffer, 16);

                if (output == null){
                    output = (char)hex.intValue()+"";
                } else {
                    output +=  (char)hex.intValue();
                }
                buffer = "";
            }


        }
        output = Base64.getEncoder().encodeToString(output.getBytes());
        return output;
    }

    private static boolean isHex(String publicKey) {
        return Pattern.matches("^[0-9a-fA-F]+$", publicKey);
    }

}
