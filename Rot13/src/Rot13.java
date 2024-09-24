import java.util.Scanner;

public class Rot13 {

    private static final char[] MINUSCULES = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Funció que xifra una cadena amb el xifrat ROT13
    public static String xifraRot13(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultat.append(substitueix(c, MINUSCULES));
            } else if (Character.isUpperCase(c)) {
                resultat.append(substitueix(c, MAJUSCULES));
            } else {
                resultat.append(c);
            }
        } return resultat.toString();
    }

    public static String desxifraRot13(String cadena) {
        return xifraRot13(cadena);
    }
    private static char substitueix(char c, char[] abecedari) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == c) {
                return abecedari[(i + 13) % 26];
            }
        }
        return c;
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Vols xifrar o desxifrar el text? (xifra/desxifra): ");
            String opcio = scanner.nextLine();
            System.out.println("Escriu el text a processar:");
            String text = scanner.nextLine();
            
            if (opcio.equalsIgnoreCase("xifra")) {
                String textXifrat = xifraRot13(text);
                System.out.println("Text xifrat: " + textXifrat);
            } else if (opcio.equalsIgnoreCase("desxifra")) {
                String textDesxifrat = desxifraRot13(text);
                System.out.println("Text desxifrat: " + textDesxifrat);
            } else {
                System.out.println("Opció no vàlida. Si us plau, tria entre 'xifra' o 'desxifra'.");
            }
        }
    }
}