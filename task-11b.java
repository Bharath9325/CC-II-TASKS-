public class DP_MaxSubarray {
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxSoFar = nums[0];
        int curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            maxSoFar = Math.max(maxSoFar, curr);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1})); // 6
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));       // 23
    }
}
