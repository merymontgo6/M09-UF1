package iticbcn.xifratge;
import java.util.Arrays;

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
        return Arrays.toString(textXifrat);
    }
}
