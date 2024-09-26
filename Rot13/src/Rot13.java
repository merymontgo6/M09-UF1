import java.util.Scanner;

public class Rot13 {
    private static final char[] minus = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] majus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String xifraRot13(String text) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textXifrat = text.charAt(i);

            if (Character.isLowerCase(textXifrat)) {
                int pos = (textXifrat - 'a' + 13) % 26; // pos 13 adelante en minus
                str.append(minus[pos]);}

            else if (Character.isUpperCase(textXifrat)) {
                int pos = (textXifrat - 'A' + 13) % 26;
                str.append(majus[pos]);
            } else {
                str.append(textXifrat);
                }
        } return str.toString(); 
    }

    public static String desxifraRot13(String text) {
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < text.length();i++){
            char textDesxifrat = text.charAt(i);
            if(Character.isLowerCase(textDesxifrat)){
                int pos = (textDesxifrat - 'a' - 13 +26)%26;
                str.append((minus[pos]));
            } 
            else if (Character.isUpperCase(textDesxifrat)){
                int pos = (textDesxifrat - 'A' - 13+ 26)%26;
                str.append(majus[pos]);
            }
            else {
                str.append(textDesxifrat);
            }
        } return str.toString();
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Vols xifrar o desxifrar el text? (xifra/desxifra): ");
            String opcio = scanner.nextLine();
            System.out.println("Escriu el text a processar:");
            String text = scanner.nextLine();
            
            if (opcio.equals("xifra")) {
                String textXifrat = xifraRot13(text);//envia la variable
                System.out.println("Text xifrat: " + textXifrat);
            } else if (opcio.equals("desxifra")) {
                String textDesxifrat = desxifraRot13(text);//envia la variable
                System.out.println("Text desxifrat: " + textDesxifrat);
            } else {
                System.out.println("'xifra' o 'desxifra'.");
            }
        }
    }
}