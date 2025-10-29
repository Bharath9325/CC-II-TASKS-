import java.util.*;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k <= 0) return new int[0][2];
        
        // Max-heap by distance to origin; store point arrays directly
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = a[0]*a[0] + a[1]*a[1];
            int distB = b[0]*b[0] + b[1]*b[1];
            // max-heap: larger distance first
            return Integer.compare(distB, distA);
        });

        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        // Extract in reverse order; order doesn't matter, but fill from end for consistency
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] points = { {3,3}, {5,-1}, {-2,4} };
        int k = 2;
        int[][] res = sol.kClosest(points, k);
        for (int[] p : res) {
            System.out.println(Arrays.toString(p));
        }
    }
}
