class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Solution {
    int maxLen = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLen;
    }
    int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int leftPath = (node.left != null && node.left.val == node.val) ? left + 1 : 0;
        int rightPath = (node.right != null && node.right.val == node.val) ? right + 1 : 0;
        maxLen = Math.max(maxLen, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
