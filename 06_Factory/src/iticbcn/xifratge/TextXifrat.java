package iticbcn.xifratge;

public class TextXifrat {
    private final byte[] textXifrat;

    // Constructor que inicialitza l'array de bytes
    public TextXifrat(byte[] textXifrat) {
        this.textXifrat = textXifrat;
    }

    // Mètode per obtenir l'array de bytes
    public byte[] getBytes() {
        return textXifrat;
    }

    // Mètode toString sobreescrit per retornar una representació del text xifrat
    @Override
    public String toString() {
        try {
            // Converteix els bytes a una cadena utilitzant UTF-8
            return new String(textXifrat, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // O bé retornar una cadena buida
        }
    }
}
