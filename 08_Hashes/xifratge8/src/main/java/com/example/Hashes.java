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
    private int npass = 0;

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
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128);
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
           
    //for ()
    //if((pw = testPw (0)) != null) return null;
        //for ()
        //if((pw = testPw ()) != null) return null; s'ha de comparar amb el hash i ha de sortir una vegada que el pass es correcte
    public String forcaBruta(String alg, String hash, String salt) {
        String CHARSET = "abcdefABCDEF1234567890!";
        npass = 0;
        
        String currentPassword = new String(password);
        // Generar el hash per a la contrasenya actual + salt
        String generatedHash = generateHash(alg, currentPassword, salt);
        char[] password = new char[6];
        for (int i = 0; i < CHARSET.length(); i++) {
            password[0] = CHARSET.charAt(i);
            // Crear la contrasenya actual
             currentPassword = new String(password);
            // Generar el hash per a la contrasenya actual + salt
             generatedHash = generateHash(alg, currentPassword, salt);
            if (generatedHash != null && generatedHash.equals(hash)) {
                return currentPassword; // Retornar la contrasenya trobada
            }
            for (int j = 0; j < CHARSET.length(); j++) {
                password[1] = CHARSET.charAt(j);
                currentPassword = new String(password);
                // Generar el hash per a la contrasenya actual + salt
                 generatedHash = generateHash(alg, currentPassword, salt);
                if (generatedHash != null && generatedHash.equals(hash)) {
                    return currentPassword; // Retornar la contrasenya trobada
                }
                for (int k = 0; k < CHARSET.length(); k++) {
                    password[2] = CHARSET.charAt(k);
                    for (int l = 0; l < CHARSET.length(); l++) {
                        password[3] = CHARSET.charAt(l);
                        for (int m = 0; m < CHARSET.length(); m++) {
                            password[4] = CHARSET.charAt(m);
                            for (int n = 0; n < CHARSET.length(); n++) {
                                password[5] = CHARSET.charAt(n);
                                npass++;// Incrementar el comptador de contrasenyes provades

                                currentPassword = new String(password);
                                // Generar el hash per a la contrasenya actual + salt
                                generatedHash = generateHash(alg, currentPassword, salt);
                                if (generatedHash != null && generatedHash.equals(hash)) {
                                    return currentPassword; // Retornar la contrasenya trobada
                                }
                            }
                        }
                    }
                }
            }
        }
        return null; // en cas de no trobada, retorna null
    }

    private String generateHash(String alg, String password, String salt) {
        if (alg.equalsIgnoreCase("SHA-512")) {
            return getSHA512AmbSalt(password, salt);
        } else if (alg.equalsIgnoreCase("PBKDF2")) {
            return getPBKDF2AmbSalt(password, salt);
        } else {
            System.out.println("Algorisme desconegut: " + alg);
            return null;
        }
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
    public Hashes(){
        
    }
}
