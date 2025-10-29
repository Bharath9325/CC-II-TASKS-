class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        
        long left = 1;
        long right = x;
        int result = 0;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (mid <= x / mid) {
                result = (int) mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int number = 8;
        System.out.println("The square root of " + number + " is: " + sol.mySqrt(number));
        
        number = 4;
        System.out.println("The square root of " + number + " is: " + sol.mySqrt(number));
    }
}
