import java.util.*;

class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        // Generate all sequential-digit numbers (from 12 up to 123456789)
        for (int length = 2; length <= 9; length++) {
            for (int start = 1; start <= 9 - length + 1; start++) {
                int num = 0;
                for (int d = 0; d < length; d++) {
                    num = num * 10 + (start + d);
                }
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        SequentialDigits obj = new SequentialDigits();
        int low = 100, high = 300;
        System.out.println("Sequential digits in range: " + obj.sequentialDigits(low, high));
    }
}
