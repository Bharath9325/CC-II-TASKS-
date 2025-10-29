class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int nextMultipleOf2 = uglyNumbers[p2] * 2;
            int nextMultipleOf3 = uglyNumbers[p3] * 3;
            int nextMultipleOf5 = uglyNumbers[p5] * 5;

            int nextUgly = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            uglyNumbers[i] = nextUgly;

            if (nextUgly == nextMultipleOf2) p2++;
            if (nextUgly == nextMultipleOf3) p3++;
            if (nextUgly == nextMultipleOf5) p5++;
        }

        return uglyNumbers[n - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        System.out.println("The " + n + "th ugly number is: " + sol.nthUglyNumber(n));
    }
}
