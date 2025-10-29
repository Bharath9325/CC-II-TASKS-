import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    public void push(int x) {
        q2.add(x);
        top = x;
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        int val = q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
        return val;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println("Top element is: " + stack.top());   // returns 2
        System.out.println("Popped element is: " + stack.pop());    // returns 2
        System.out.println("Is stack empty: " + stack.empty()); // returns false
    }
}
