import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class DiskTower {
    public static void constructTower(int n, int[] diskSizes) {
        Set<Integer> availableDisks = new HashSet<>();
        int nextDiskToPlace = n;

        for (int i = 0; i < n; i++) {
            availableDisks.add(diskSizes[i]);
            StringBuilder dailyOutput = new StringBuilder();

            while (availableDisks.contains(nextDiskToPlace)) {
                dailyOutput.append(nextDiskToPlace).append(" ");
                availableDisks.remove(nextDiskToPlace);
                nextDiskToPlace--;
            }
            System.out.println(dailyOutput.toString().trim());
        }
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println("Example 1:");
        int n1 = 3;
        int[] disks1 = {3, 1, 2};
        constructTower(n1, disks1);

        System.out.println("\nExample 2:");
        // Example 2
        int n2 = 5;
        int[] disks2 = {4, 5, 1, 2, 3};
        constructTower(n2, disks2);
    }
}
