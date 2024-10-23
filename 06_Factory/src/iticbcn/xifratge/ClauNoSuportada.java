package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {

    // Constructor que accepta un missatge personalitzat
    public ClauNoSuportada(String missatge) {
        super(missatge);
    }

    // Constructor per defecte
    public ClauNoSuportada() {
        super("La clau proporcionada no és suportada.");
    }
}