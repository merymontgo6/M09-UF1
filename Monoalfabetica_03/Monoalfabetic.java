import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private static final char[] minus = "aàáâäãbcçdeèéêëfghiìíîïjklmnñopqrstuùúûüvwxyz".toCharArray();
    private static final char[] majus = "AÀÁÂÄÃBCÇDEÈÉÊËFGHIÌÍÎÏJKLMNÑOPQRSTUÙÚÛÜVWXYZ".toCharArray();
    private static char[] alfabetP;

    public static char[] permutAlfabet() {
        List<Character> canvi = new ArrayList<>();
        for(int i = 0; i < minus.length; i++){
            canvi.add(minus[i]);
        }
        System.out.println("Alfabet original: " + Arrays.toString(minus));
        Collections.shuffle(canvi);
        System.out.println("Alfabet permutat: " + canvi);

        alfabetP = new char[canvi.size()];
        for(int i = 0; i < canvi.size(); i++){
            alfabetP[i] = canvi.get(i);
        }
        return alfabetP;
    }

    private static int findIndex(char[] canvi, char lletra) {
        for (int i = 0; i < canvi.length; i++) {
            if (canvi[i] == lletra) {
                return i;
            }
        }
        return -1;
    }

    public static String xifraMonoAlfa(String cadena) {
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
    return cadenaXifrada.toString();
    }

    public static String desxifraMonoAlfa(String cadena) {
    if (alfabetP == null) {
            permutAlfabet();
        }

    StringBuilder cadenaDesxifrada = new StringBuilder();

    for (int i = 0; i < cadena.length(); i++) {
        char lletra = cadena.charAt(i);
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

    public static void main(String[] args) {
        String cadena = "Hola, como estas?";
        String cadenaXifrada = xifraMonoAlfa(cadena);
        System.out.println("Texto original: " + cadena);
        System.out.println("Texto xifrat: " + cadenaXifrada);

        String cadenaDesxifrada = desxifraMonoAlfa(cadenaXifrada);
    System.out.println("Text desxifrat: " + cadenaDesxifrada);
    }
}