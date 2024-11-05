package iticbcn.xifratge;

import java.util.Random;

public class XifradorRotX implements Xifrador{
    public static final char[] minus = "aàáâäãbcçdeèéêëfghiìíîïjklmnñopqrstuùúûüvwxyz".toCharArray();
    public static final char[] majus = "AÀÁÂÄÃBCÇDEÈÉÊËFGHIÌÍÎÏJKLMNÑOP QRSTUÙÚÛÜVWXYZ".toCharArray();
    public static final Random random = new Random();

    // Inicialitza la llavor aleatòria amb la contrasenya
    public void initRandom(String clauSecreta) {
        long seed = 0;
        for (byte b : clauSecreta.getBytes()) {
            seed += b;
        }
        random.setSeed(seed);
    }

    public static int posicioEnArray(char[] llista, char c) {
        for (int i = 0; i < llista.length; i++) {
            if (llista[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static char xifraCaracterRotX(char[] llista, int pos, int rot) {
        int posChar = (pos + rot) % llista.length;
        return llista[posChar];
    }

    public static char desXifraCaracterRotX(char[] llista, int pos, int rot) {
        int posChar = ((pos - rot) < 0) ? (pos - rot) + llista.length : (pos - rot);
        return llista[posChar];
    }

    public static String rotaX(String msg, int rot, boolean dreta) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char cOrig = msg.charAt(i);
            int pos = posicioEnArray(majus, cOrig);
            char cRotat;
            if (pos != -1) {
                cRotat = dreta ? xifraCaracterRotX(majus, pos, rot) : desXifraCaracterRotX(majus, pos, rot);
            } else {
                pos = posicioEnArray(minus, cOrig);
                if (pos != -1) {
                    cRotat = dreta ? xifraCaracterRotX(minus, pos, rot) : desXifraCaracterRotX(minus, pos, rot);
                } else {
                    cRotat = cOrig; // character not found in both arrays
                }
            }
            sb.append(cRotat);
        }
        return sb.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String rot) throws ClauNoSuportada {
        int rotation;
        try {
            rotation = Integer.parseInt(rot);
            if (rotation < 0 || rotation > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String encryptedMsg = rotaX(msg, rotation, true);
        return new TextXifrat(encryptedMsg.getBytes());
    }
    
    @Override
    public String desxifra(TextXifrat txt, String rot) throws ClauNoSuportada {
        int rotation;
        try {
            rotation = Integer.parseInt(rot);
            if (rotation < 0 || rotation > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String decryptedMsg = desxifraRorX(txt.toString(), rotation);
        return decryptedMsg;
    }

    public static String desxifraRorX(String msg, int rot) {
        return rotaX(msg, rot, false);
    }
}