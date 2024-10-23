package iticbcn.xifratge;
import java.util.Scanner;

public class XifradorRotX {
    private final char[] minus = "aàáâäãbcçdeèéêëfghiìíîïjklmnñopqrstuùúûüvwxyz".toCharArray();
    private final char[] majus = "AÀÁÂÄÃBCÇDEÈÉÊËFGHIÌÍÎÏJKLMNÑOPQRSTUÙÚÛÜVWXYZ".toCharArray();


    public String xifraRotX( String text, Integer num) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textXifrat = text.charAt(i);

            if (Character.isLowerCase(textXifrat)) {
                int pos = (textXifrat - 'a' + num) % minus.length;
                str.append(minus[pos]);}

            else if (Character.isUpperCase(textXifrat)) {
                int pos = (textXifrat - 'A' + num) % majus.length;
                str.append(majus[pos]);
            } else {
                str.append(textXifrat);
                }
        } return str.toString();
    }

    public String desxifraRotX (String text, Integer num) {
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < text.length();i++){
            char textDesxifrat = text.charAt(i);
            if(Character.isLowerCase(textDesxifrat)){
                int pos = (textDesxifrat - 'a' - num)%minus.length;
                str.append((minus[pos]));
            } 
            else if (Character.isUpperCase(textDesxifrat)){
                int pos = (textDesxifrat - 'A' - num)%majus.length;
                str.append(majus[pos]);
            }
            else {
                str.append(textDesxifrat);
            }
        } return str.toString();
    }

// Ha de provar tots els desplaçaments possibles i mostrar el missatge
// resultant de desxifrar amb desplaçaments de 1,2,3,... fins la longitud de l’abecedari.
public String forcaBrutaRotX(String text) {
    StringBuilder str = new StringBuilder();

    for (int i = 1; i <= minus.length; i++) { // 0 no pq seria lo mismo xd 
        StringBuilder str2 = new StringBuilder();
        
        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);

            if (Character.isLowerCase(c)) {
                for (int k = 0; k < minus.length; k++) {
                    if (minus[k] == c) {
                        str2.append(minus[(k + i) % minus.length]);
                        break;
                    }
                }
            } else if (Character.isUpperCase(c)) {
                for (int h = 0; h < majus.length; h++) {
                    if (majus[h] == c) {
                        str2.append(majus[(h + i) % majus.length]);
                        break;
                    }
                }
            } else {
                str2.append(c);
            }
        }
        str.append("Desplaçament " + i + ": " + str2.toString() + "\n");
    } return str.toString();
}

    @SuppressWarnings("ConvertToStringSwitch")
    public void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Vols xifrar o desxifrar el text? (xifra/desxifra):");
            String opcio = scanner.nextLine();
            System.out.println("Escriu el text a desxifrar:");
            String text = scanner.nextLine();
            System.out.println("Quants desplaçaments vols fer?");
            int num = scanner.nextInt();
            scanner.nextLine();

            if (opcio.equals("xifra")) {
                String textXifrat = xifraRotX(text, num);
                System.out.println("Text xifrat:" + textXifrat);
            } else if(opcio.equals("desxifra")) {
                String textDesxifrat = desxifraRotX(text, num);
                System.out.println("Text desxifrat: "+ textDesxifrat);
            } else {
                System.out.println("'xifra' o 'desxifra'");
            }
        }
    }
}