package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    private final char[] minus = "aàáâãäåbcçdèéêëfgìíîïjklmnñòóôõöpqrstuùúûüvwxyýz".toCharArray();
    private final char[] majus = "AÀÁÂÃÄÅBCÇDÈÉÊËFGÌÍÎÏJKLMNÑÒÓÔÕÖPQRSTÙÚÛÜVWXYÝZ".toCharArray();
    private char[] alfabetP;
    private final Random random = new Random();

    // Inicialitza la llavor aleatòria amb la contrasenya
    public void initRandom(String clauSecreta) {
        long seed = 0;
        for (byte b : clauSecreta.getBytes()) {
            seed += b;
        }
        random.setSeed(seed);
    }

    //mètode activitat anterior per permutar l'alfabet
    public char[] permutaAlfabet(){
        List<Character> canvi = new ArrayList<>();
        for(int i = 0; i < minus.length; i++){
            canvi.add(minus[i]);
        }
        Collections.shuffle(canvi, random);

        alfabetP = new char[canvi.size()];
        for(int i = 0; i < canvi.size(); i++){
            alfabetP[i] = canvi.get(i);
        }
        return alfabetP;
    }

    public String xifraPoliAlfa( String msg ){
        StringBuilder resultat = new StringBuilder();
        
        for (char lletra : msg.toCharArray()) {
            if (Character.isLowerCase(lletra)) {
                alfabetP = permutaAlfabet();  // Permuta per a cada lletra
                int index = findIndex(minus, lletra);
                if (index != -1) {
                    resultat.append(alfabetP[index]);
                } else {
                    resultat.append(lletra);  // Manté el caràcter si no es troba a l'alfabet
                }
            } else if (Character.isUpperCase(lletra)) {
                alfabetP = permutaAlfabet();  // Permuta per a cada lletra
                int index = findIndex(majus, lletra);
                if (index != -1) {
                    resultat.append(alfabetP[index]);
                } else {
                    resultat.append(lletra);  // Manté el caràcter si no es troba a l'alfabet
                }
            } else {
                resultat.append(lletra); // Manté els caràcters no alfabètics
            }
        }
        return resultat.toString();
    }

    private int findIndex(char[] alP, char lletra) {
        for (int i = 0; i < alP.length; i++) {
            if (alP[i] == lletra) {
                return i;
            }
        }
        return -1; // Si no es troba, retorna -1 (no s'hauria de donar)
    }

    public String desxifraPoliAlfa( String msgXifrat ){
        StringBuilder resultat = new StringBuilder();
        
        for (char lletra : msgXifrat.toCharArray()) {
            if (Character.isLowerCase(lletra)) {
                alfabetP = permutaAlfabet();  // Permuta per a cada lletra
                int index = findIndex(alfabetP, lletra);
                if (index != -1) {
                    resultat.append(alfabetP[index]);
                } else {
                    resultat.append(lletra);  // Manté el caràcter si no es troba a l'alfabet
                }
            } else if (Character.isUpperCase(lletra)) {
                alfabetP = permutaAlfabet();  // Permuta per a cada lletra
                int index = findIndex(alfabetP, lletra);
                if (index != -1) {
                    resultat.append(alfabetP[index]);
                } else {
                    resultat.append(lletra);  // Manté el caràcter si no es troba a l'alfabet
                }
                
            } else {
                resultat.append(lletra); // Manté els caràcters no alfabètics
            }
        }
        return resultat.toString();
    }

    // Funció auxiliar per trobar la posició d'una lletra a l'alfabet
    
    public void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                    "Test 02 Taüll, DÍA, año",
                    "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats [] = new String[msgs.length];
        String clauSecreta = "ClauSecreta"; 
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta );
        msgsXifrats [i] = xifraPoliAlfa (msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats [i]);
        }
        System.out.println("Desxifratge: \n-----------" );
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta );
        String msg = desxifraPoliAlfa (msgsXifrats [i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats [i], msg);
        }
    }

    @Override
    public String encripta(String missatge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String desxifra(String missatgeXifrat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}