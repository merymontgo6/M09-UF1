package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] minus = "aàáâãäåbcçdèéêëfgìíîïjklmnñòóôõöpqrstuùúûüvwxyýz".toCharArray();
    private final char[] majus = "AÀÁÂÃÄÅBCÇDÈÉÊËFGÌÍÎÏJKLMNÑÒÓÔÕÖPQRSTÙÚÛÜVWXYÝZ".toCharArray();
    private char[] alfabetP;
    private final Random random = new Random();

    public void initRandom(String clauSecreta) {
        long seed = 0;
        for (byte b : clauSecreta.getBytes()) {
            seed += b;
        }
        random.setSeed(seed);
    }

    public char[] permutAlfabet() {
        List<Character> canvi = new ArrayList<>();
        for(int i = 0; i < minus.length; i++){
            canvi.add(minus[i]);
        }
        Collections.shuffle(canvi);

        alfabetP = new char[canvi.size()];
        for(int i = 0; i < canvi.size(); i++){
            alfabetP[i] = canvi.get(i);
        }
        return alfabetP;
    }

    private int findIndex(char[] canvi, char lletra) {
        for (int i = 0; i < canvi.length; i++) {
            if (canvi[i] == lletra) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada {
        if (clau == null){
            throw new ClauNoSuportada("Clau no suportada");
        }
        if (alfabetP == null) {
            permutAlfabet();
        }
        StringBuilder cadenaXifrada = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)) {
                int index = findIndex(minus, lletra);
                if (index != -1) {
                    cadenaXifrada.append(alfabetP[index]);
                } else {
                    cadenaXifrada.append(lletra);
                }
            } else if (Character.isUpperCase(lletra)) {
                int index = findIndex(majus, lletra);
                if (index != -1) {
                    cadenaXifrada.append(Character.toUpperCase(alfabetP[index]));
                } else {
                    cadenaXifrada.append(lletra);
                }
            } else {
                cadenaXifrada.append(lletra);
            }
        }
        return new TextXifrat(cadenaXifrada.toString().getBytes());
        }

    @Override
    public String desxifra(TextXifrat msgXifrat, String clau) throws ClauNoSuportada {
        if (clau == null){
            throw new ClauNoSuportada("Clau no suportada");
        }

        initRandom(clau);
        byte[] bytes = msgXifrat.getBytes();
        StringBuilder cadenaDesxifrada = new StringBuilder();

        for (byte b : bytes) {
            char lletra = (char) b;
            if (Character.isLowerCase(lletra)) {
                int index = findIndex(alfabetP, lletra);
                if (index != -1) {
                    cadenaDesxifrada.append(minus[index]);
                } else {
                    cadenaDesxifrada.append(lletra);
                }
            } else if (Character.isUpperCase(lletra)) {
                int index = findIndex(alfabetP, Character.toLowerCase(lletra));
                if (index != -1) {
                    cadenaDesxifrada.append(majus[index]);
                } else {
                    cadenaDesxifrada.append(lletra);
                }
            } else {
                cadenaDesxifrada.append(lletra);
            }
        }
        return cadenaDesxifrada.toString();
    }
}