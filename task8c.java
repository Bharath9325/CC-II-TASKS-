import java.util.*;

public class DecodeString {
    public static String decode(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> outStack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(num);
            } else if (c == '[') {
                outStack.push(current);
                current = new StringBuilder();
                i++;
            } else if (c == ']') {
                StringBuilder prev = outStack.pop();
                int repeat = countStack.pop();
                StringBuilder tmp = new StringBuilder();
                for (int k = 0; k < repeat; k++) tmp.append(current);
                current = prev.append(tmp);
                i++;
            } else {
                current.append(c);
                i++;
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        // Examples
        System.out.println(decode("3[a]2[bc]"));       // aaabcbc
        System.out.println(decode("2[3[x]y]z"));      // xxxxyyyz
        // Add more tests as needed
    }
}
