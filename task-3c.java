import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        Pattern hasUppercase = Pattern.compile("[A-Z]");
        Pattern hasLowercase = Pattern.compile("[a-z]");
        Pattern hasDigit = Pattern.compile("[0-9]");
        Pattern hasSpecialChar = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");

        Matcher upperMatcher = hasUppercase.matcher(password);
        Matcher lowerMatcher = hasLowercase.matcher(password);
        Matcher digitMatcher = hasDigit.matcher(password);
        Matcher specialMatcher = hasSpecialChar.matcher(password);

        return upperMatcher.find() && lowerMatcher.find() && digitMatcher.find() && specialMatcher.find();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String pass1 = "Password@123";
        String pass2 = "weakpass";
        String pass3 = "NoSpecial1";
        
        System.out.println("'" + pass1 + "' is valid: " + sol.isValidPassword(pass1));
        System.out.println("'" + pass2 + "' is valid: " + sol.isValidPassword(pass2));
        System.out.println("'" + pass3 + "' is valid: " + sol.isValidPassword(pass3));
    }
}
