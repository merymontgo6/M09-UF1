import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private static final char[] minus = "aàáâäãbcçdeèéêëfghiìíîïjklmnñopqrstuùúûüvwxyz".toCharArray();
    private static final char[] majus = "AÀÁÂÄÃBCÇDEÈÉÊËFGHIÌÍÎÏJKLMNÑOPQRSTUÙÚÛÜVWXYZ".toCharArray();

    public static char[] permutaAlfabet(String alfabet) {
        List<Character> canvi = new ArrayList<>();
        for(int i = 0; i < minus.length; i++){
            canvi.add(minus[i]);
        }
        System.out.println("Alfabet original: " + minus);
        Collections.shuffle(canvi);
        System.out.println("Alfabet permutat: " + canvi);

        char[] alfabetP = new char[canvi.size()];
        for(int i = 0; i < canvi.size(); i++){
            alfabetP[i] = canvi.get(i);
        }
        return alfabetP;
    }
}
