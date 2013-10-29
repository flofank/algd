package ch.fhnw.algd.u2;

public class Strings {
    public static void main(String[] args) {
        String s = "\u0041\uD800\uDF84\u0042\u03A9";
        byte[] latin = utfToLatin1(s);
        for (byte b : latin) {
            System.out.println(b);
        }
        char[] utf16 = new char[4];
        int pos = utf32to16(122344, utf16, 0);
        utf32to16(65, utf16, pos);
        System.out.println(utf16[0] + " " + utf16[1] + " " + utf16[2] + " " + utf16[3] );
    }
    
    public static byte[] utfToLatin1(String s) {
        int ignores = 0;
        byte[] out = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // Parse character 
            char c = s.charAt(i);
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
    
    public static int utf32to16(int c, char[] chars, int pos) {
        if (c > 0x10000) c = c - 0x10000;
        int part1 = c / 0x300;
        int part2 = c % 0x300;
        
        if (part1 == 0) {
            chars[pos] = (char) part2;
            return 1;
        } else {
            chars[pos] = (char) (part1 + 0xD800);
            chars[pos+1] = (char) (part2 + 0xDC00);
            return 2;
        }
    }
}
