class RegexMatching {
    public boolean isMatch(String s, String p) {
        return matchHelper(s, p, 0, 0);
    }

    private boolean matchHelper(String s, String p, int i, int j) {
        // If we have reached the end of both strings, return true
        if (j == p.length()) return i == s.length();

        // Check if current characters match or pattern has '.'
        boolean firstMatch = (i < s.length()) && 
            (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // Handle '*' wildcard case
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Either skip this pattern part (zero occurrences) OR consume character from 's'
            return matchHelper(s, p, i, j + 2) || (firstMatch && matchHelper(s, p, i + 1, j));
        } else {
            // If no '*', move both pointers forward if there is a match
            return firstMatch && matchHelper(s, p, i + 1, j + 1);
        }
    }

    public static void main(String[] args) {
        RegexMatching regex = new RegexMatching();
        // Test cases
        System.out.println(regex.isMatch("aa", "a*"));  // Output: true
        System.out.println(regex.isMatch("mississippi", "mis*is*p*."));  // Output: false
        System.out.println(regex.isMatch("ab", ".*"));  // Output: true
        System.out.println(regex.isMatch("aab", "c*a*b"));  // Output: true
    }
}
