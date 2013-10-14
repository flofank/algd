package ch.fhnw.algd.u2;

public class Strings {
    public static void main(String[] args) {
        String s = "\u0041\uD800\uDF84\u0042\u03A9";
        byte[] latin = utfToLatin1(s);
        for (byte b : latin) {
            System.out.println(b);
        }
    }
    
    public static byte[] utfToLatin1(String s) {
        int ignores = 0;
        byte[] out = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // Parse character 
            char c = s.charAt(i);
            int val = c;
            if (c >= 0xD800) { // is surrogate
                out[i - ignores] = 99; // ?
                i++;
                ignores++;
            } else if (c > 0xFF) { // still out of range
                out[i - ignores] = 99;
            } else { // valid latin-1
                out[i - ignores] = (byte) c;
            }      
        }
        return out;
    }
}
