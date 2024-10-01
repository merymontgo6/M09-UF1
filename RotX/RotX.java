import java.util.Scanner;

public class RotX {
    private static final char[] minus = "aàáâäãbcçdeèéêëfghiìíîïjklmnñopqrstuùúûüvwxyz".toCharArray();
    private static final char[] majus = "AÀÁÂÄÃBCÇDEÈÉÊËFGHIÌÍÎÏJKLMNÑOPQRSTUÙÚÛÜVWXYZ".toCharArray();


    public static String xifraRotX( String text, Integer num) {
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

    public static String desxifraRotX (String text, Integer num) {
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < text.length();i++){
            char textDesxifrat = text.charAt(i);
            if(Character.isLowerCase(textDesxifrat)){
                int pos = (textDesxifrat - 'a' - num)%26;
                str.append((minus[pos]));
            } 
            else if (Character.isUpperCase(textDesxifrat)){
                int pos = (textDesxifrat - 'A' - num)%26;
                str.append(majus[pos]);
            }
            else {
                str.append(textDesxifrat);
            }
        } return str.toString();
    }

    //public static String forcaBrutaRotX(String textXifrat) {

    //}

    public static void main(String[] args) throws Exception {
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
