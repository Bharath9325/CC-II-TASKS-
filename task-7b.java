class Solution {
    int count = 0;
    public int numberOfPaths(TreeNode root) {
        dfs(root);
        return count;
    }
    void dfs(TreeNode node) {
        if (node == null) return;
        count += pathCount(node);
        dfs(node.left);
        dfs(node.right);
    }
    int pathCount(TreeNode node) {
        if (node == null) return 0;
        return 1 + pathCount(node.left) + pathCount(node.right);
    }
}
