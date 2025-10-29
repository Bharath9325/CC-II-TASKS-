import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left, right;
    public Node(int item) {
        data = item;
        left = right = null;
    }
}
class BinaryTree {
    Node root;
    void levelOrder() {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
            if (tempNode.left != null) queue.add(tempNode.left);
            if (tempNode.right != null) queue.add(tempNode.right);
        }
    }
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(8);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(4);
        tree.root.right.left = new Node(7);
        tree.root.right.right = new Node(9);
        System.out.println("Level Order traversal of binary tree is ");
        tree.levelOrder();
    }
}
