package iticbcn.xifratge;

import java.security.*;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public String ALGORISME_XIFRAT = "AES";
    public String ALGORISME_HASH = "SHA-256";
    public String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private final int MIDA_IV = 16;
    private final byte[] iv = new byte[MIDA_IV];
    private final String CLAU = "claux";
    private final Random random = new Random();
    // Inicialitza la llavor aleatòria amb la contrasenya
    public void initRandom(String clauSecreta) {
        long seed = 0;
        for (byte b : clauSecreta.getBytes()) {
            seed += b;
        }
        random.setSeed(seed);
    }
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null){
            throw new ClauNoSuportada("Clau no suportada");
        }
        initRandom(clau);
        try {
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

            return new TextXifrat(encryptedWithIv);
            
        } catch (Exception e) {
            System.err.println("Error en el xifratge: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
    
    @Override
    public String desxifra(TextXifrat txt, String clau) throws ClauNoSuportada{
        if (clau == null){
            throw new ClauNoSuportada("Clau no suportada");
        }
        initRandom(clau);
        byte[] bXifrats = txt.getBytes();
        IvParameterSpec IvPa = new IvParameterSpec(Arrays.copyOfRange(bXifrats, 0, MIDA_IV));

        // extrae mensaje xifrat
        byte[] encryptedMsg = Arrays.copyOfRange(bXifrats, MIDA_IV, bXifrats.length);
        try {
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
        } catch (Exception e) {
            System.err.println("Error en el desxifratge: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
