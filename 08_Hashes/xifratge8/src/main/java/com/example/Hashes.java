package com.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
//S'HA DE FER UN FOR PER CADA LLETRA DEL ARRAY CHAR PASSWORD
//VA A PROVAR AMB A I DESPRES AMB LA CONTRSSENYA QUE ELL DONA
public class Hashes {
    public int npass = 0;
    
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            String saltedPassword = pw + salt; // Combina contrassenya amb salt
            MessageDigest digest = MessageDigest.getInstance("SHA-512"); // Crea MessageDigest para SHA-512
            byte[] hashBytes = digest.digest(saltedPassword.getBytes()); // aquest es el hashing
            HexFormat hex = HexFormat.of(); //Converteix el byte[] a hexadecimal
            return hex.formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return  e.getMessage();
        }
    }
    
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            byte[] abSalt = salt.getBytes(StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128); //max vegades que es pot fer
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] abHash = factory.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            String hash = hex.formatHex(abHash); // Retorna el hash generat en format hexadecimal
            return hash; // Retorna el hash generatetorna el hash generat en format hexadecimal
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("Error al generar el hash PBKDF2: " + e.getMessage()); // Registre d'error
            return null; // Retorna null en cas d'error
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        String charset = "abcdefABCDEF1234567890!";
        char[] password = new char[6];
        String foundPassword = null;
    
        // Bucle anidat per provar totes les combinacions
        for (int i = 0; i < charset.length(); i++) {
            for (int j = 0; j < charset.length(); j++) {
                for (int k = 0; k < charset.length(); k++) {
                    for (int l = 0; l < charset.length(); l++) {
                        for (int m = 0; m < charset.length(); m++) {
                            for (int n = 0; n < charset.length(); n++) { // per cada combinació de 6 caràcters MAX PQ PETA
                                password[0] = charset.charAt(i); // s'afegeixen els caràcters a la contrasenya
                                password[1] = charset.charAt(j);
                                password[2] = charset.charAt(k);
                                password[3] = charset.charAt(l);
                                password[4] = charset.charAt(m);
                                password[5] = charset.charAt(n);
                                String trialPw = new String(password);
                                npass++;
                                    
                                String trialHash = null;
                                try {
                                    if (alg.equals("SHA-512")) {
                                        trialHash = getSHA512AmbSalt(trialPw, salt);
                                    } else if (alg.equals("PBKDF2")) {
                                        trialHash = getPBKDF2AmbSalt(trialPw, salt);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
    
                                if (trialHash != null && trialHash.equals(hash)) {
                                    foundPassword = trialPw;
                                    return foundPassword;
                                }
                            }
                        }
                    }
                }
            }
        }
        return foundPassword;
    }

    public String getInterval(long t1, long t2) {
        // Calcular la diferència en mil·lisegons
        long diff = t2 - t1;  //prendre valor absolut
        // Convertir la diferència a hores, minuts i segons
        long millis = diff % 1000;
        long segons = (diff / 1000) % 60;
        long minutes = (diff / (1000 * 60)) % 60;
        long hores = (diff / (1000 * 60 * 60)) % 24;
        long dies = diff / (1000 * 60 * 60 * 24);

        // Retornar el resultat en format llegible
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d mil·lisegons", dies, hores, minutes, segons, millis);
    }
}
