import java.util.ArrayList;
import java.util.List;

class BagOfNumbers {
    private List<Integer> bag;

    public BagOfNumbers() {
        bag = new ArrayList<>();
    }

    public void add(int number) {
        bag.add(number);
    }

    public boolean remove(Integer number) {
        return bag.remove(number);
    }

    public int size() {
        return bag.size();
    }

    public boolean isEmpty() {
        return bag.isEmpty();
    }
    
    @Override
    public String toString() {
        return "Bag: " + bag.toString();
    }

    public static void main(String[] args) {
        BagOfNumbers myBag = new BagOfNumbers();
        myBag.add(5);
        myBag.add(10);
        myBag.add(5); // Adding a duplicate
        System.out.println(myBag);
        System.out.println("Size of the bag: " + myBag.size());
        
        myBag.remove(Integer.valueOf(10));
        System.out.println("After removing 10: " + myBag);
        System.out.println("Is the bag empty? " + myBag.isEmpty());
    }
}
