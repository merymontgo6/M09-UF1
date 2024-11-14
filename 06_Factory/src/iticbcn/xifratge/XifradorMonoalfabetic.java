package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] minus = "aàáâãäåbcçdèéêëfgìíîïjklmnñòóôõöpqrstuùúûüvwxyýz".toCharArray();
    private final char[] majus = "AÀÁÂÃÄÅBCÇDÈÉÊËFGÌÍÎÏJKLMNÑÒÓÔÕÖPQRSTÙÚÛÜVWXYÝZ".toCharArray();
    private char[] alfabetP;

    public char[] permutAlfabet() {
        if (alfabetP == null) {  // Generate only if null
            List<Character> canvi = new ArrayList<>();
            for (char c : minus) {
                canvi.add(c);
            }
            Collections.shuffle(canvi);
            
            alfabetP = new char[canvi.size()];
            for (int i = 0; i < canvi.size(); i++) {
                alfabetP[i] = canvi.get(i);
            }
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
    if (clau != null) {
        throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
    }

    // Generate the permuted alphabet if it's not already created
    permutAlfabet();

    StringBuilder cadenaXifrada = new StringBuilder();

    // Encrypt the string character by character
    for (int i = 0; i < cadena.length(); i++) {
        char lletra = cadena.charAt(i);
        
        if (Character.isLowerCase(lletra)) {
            // Find index in the lowercase alphabet and replace with permuted alphabet
            int index = findIndex(minus, lletra);
            if (index != -1) {
                cadenaXifrada.append(alfabetP[index]); // Append the encrypted letter
            } else {
                cadenaXifrada.append(lletra); // Append non-alphabetic characters unchanged
            }
        } else if (Character.isUpperCase(lletra)) {
            // Find index in the uppercase alphabet and replace with permuted alphabet
            int index = findIndex(majus, lletra);
            if (index != -1) {
                cadenaXifrada.append(Character.toUpperCase(alfabetP[index])); // Append encrypted uppercase letter
            } else {
                cadenaXifrada.append(lletra); // Append non-alphabetic characters unchanged
            }
        } else {
            // Preserve non-alphabetic characters
            cadenaXifrada.append(lletra);
        }
    }

    // Return the encrypted message as a TextXifrat object
    return new TextXifrat(cadenaXifrada.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat msgXifrat, String clau) throws ClauNoSuportada {
        if (clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        byte[] bytes = msgXifrat.getBytes();
        StringBuilder cadenaDesxifrada = new StringBuilder();
        try {
            String cadena = new String(bytes, "UTF-8");
            for (char lletra : cadena.toCharArray()) {
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
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cadenaDesxifrada.toString();
    }
}