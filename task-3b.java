class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        int left = 0;
        int right = cleanedString.length() - 1;

        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String str1 = "A man, a plan, a canal: Panama";
        String str2 = "race a car";
        System.out.println("'" + str1 + "' is a palindrome: " + sol.isPalindrome(str1));
        System.out.println("'" + str2 + "' is a palindrome: " + sol.isPalindrome(str2));
    }
}
