import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findPattern(String text, String pattern) {
        List<Integer> foundIndices = new ArrayList<>();
        if (pattern.isEmpty() || text.length() < pattern.length()) {
            return foundIndices;
        }

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                foundIndices.add(i);
            }
        }
        return foundIndices;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String text = "abracadabra";
        String pattern = "abra";
        List<Integer> indices = sol.findPattern(text, pattern);
        System.out.println("Pattern '" + pattern + "' found at indices: " + indices);
    }
}
