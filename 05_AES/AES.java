import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "claux";

    public static byte[] xifraAES(String msg, String CLAU) throws Exception {
        //obtenir byte en string
        byte[] xifra = msg.getBytes("UTF-8");
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(iv);
        //genera IvParameterSpec
        IvParameterSpec IvPa= new IvParameterSpec(iv);
        //genera hash
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = sha.digest(CLAU.getBytes("UTF-8"));
        key = Arrays.copyOf(key,32);  // Use the first 32 bytes for AES-256
        
        //encrypt
        SecretKeySpec secretkey = new SecretKeySpec(key, ALGORISME_XIFRAT);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretkey, IvPa);

        byte[] encrypted = cipher.doFinal(xifra);
        byte[] encryptedWithIv = new byte[MIDA_IV + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, MIDA_IV);
        System.arraycopy(encrypted, 0, encryptedWithIv, MIDA_IV, encrypted.length);

        return encryptedWithIv;

    }
    
    public static String desxifraAES(byte[] bXifrats, String CLAU) throws Exception{

    byte[] iv = Arrays.copyOfRange(bXifrats, 0, MIDA_IV);
    IvParameterSpec IvPa = new IvParameterSpec(iv);

    // extrae mensaje xifrat
    byte[] encryptedMsg = Arrays.copyOfRange(bXifrats, MIDA_IV, bXifrats.length);
    MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
    byte[] key = sha.digest(CLAU.getBytes("UTF-8"));
    key = Arrays.copyOf(key, 32);

    SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);
    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, IvPa);

    // desxifrar
    byte[] decryptedMsg = cipher.doFinal(encryptedMsg);

    // convierte decrypted byte array a string
    return new String(decryptedMsg, "UTF-8");
    }

    public static void main (String[] args) throws Exception {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
    
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            
            // Convert the encrypted byte array to Base64 for readable output
            String encryptedBase64 = Base64.getEncoder().encodeToString(bXifrats);
            System.out.println("Enc: " + encryptedBase64);
            
            System.out.println("DEC: " + desxifrat);
        }
    }
    
}
