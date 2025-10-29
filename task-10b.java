import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }

        // Min-heap where each entry is [sum, i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Initialize with the first element from nums2 paired with up to k elements from nums1
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        while (k > 0 && !minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int i = cur[1], j = cur[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
            k--;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> result = sol.kSmallestPairs(nums1, nums2, k);
        System.out.println(result);  // Expected: [[1,2], [1,4], [1,6]]
    }
}
