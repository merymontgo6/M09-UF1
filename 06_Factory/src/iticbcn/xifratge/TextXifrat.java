package iticbcn.xifratge;

public class TextXifrat {
    private byte [] bytes;

    // Constructor que accepta un array de bytes
    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    // Mètode toString que retorna una representació en String dels bytes
    @Override
    public String toString() {
        // Convertim l'array de bytes a una cadena hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b)); // Formateja cada byte com a hexadecimal
        }
        return sb.toString();
    }

    // Mètode que retorna l'array de bytes
    public byte[] getBytes() {
        return bytes;
    }
}
