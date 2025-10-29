// USE CASE
// Cloud Data Center Task Scheduling Optimization

// Aim:
// To develop a Cloud Data Center Task Scheduling system that assigns multiple computational tasks to available servers efficiently to maximize resource utilization and minimize total execution time, using Greedy and Dynamic Programming techniques.
// Algorithm:
// 1.	Step 1: Input total number of tasks (n) and servers (m).
// 2.	Step 2: For each task, store its CPU, Memory, and Time requirements.
// 3.	Step 3: For each server, store its CPU and Memory capacities.
// 4.	Step 4: Assign each task to the least loaded server using a Greedy approach.
// 5.	Step 5: If multiple servers can handle the task, choose the one with the minimum total time.
// 6.	Step 6: Apply Dynamic Programming or rebalancing to minimize total execution time.
// 7.	Step 7: Display all server allocations and total resource usage.
// Program:

import java.util.*;

public class CloudScheduler {
    static class Server {
        int cpuCap, memCap, usedCpu = 0, usedMem = 0, time = 0;
        List<Integer> tasks = new ArrayList<>();
        Server(int c, int m) { cpuCap = c; memCap = m; }
        boolean canAssign(int cpu, int mem) {
            return usedCpu + cpu <= cpuCap && usedMem + mem <= memCap;
        }
        void assignTask(int id, int cpu, int mem, int t) {
            usedCpu += cpu; usedMem += mem; time += t; tasks.add(id);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();
        System.out.print("Enter number of servers: ");
        int m = sc.nextInt();

        int[] cpu = new int[n], mem = new int[n], time = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Task " + (i+1) + " CPU, Memory, Time:");
            cpu[i] = sc.nextInt(); mem[i] = sc.nextInt(); time[i] = sc.nextInt();
        }

        Server[] servers = new Server[m];
        for (int i = 0; i < m; i++) {
            System.out.println("Server " + (i+1) + " CPU cap, Mem cap:");
            servers[i] = new Server(sc.nextInt(), sc.nextInt());
        }

        for (int i = 0; i < n; i++) {
            int best = -1, minTime = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (servers[j].canAssign(cpu[i], mem[i]) && servers[j].time < minTime) {
                    minTime = servers[j].time; best = j;
                }
            }
            if (best != -1) servers[best].assignTask(i+1, cpu[i], mem[i], time[i]);
            else System.out.println("Task " + (i+1) + " cannot be scheduled!");
        }

        for (int i = 0; i < m; i++) {
            System.out.println("\nServer " + (i+1) + " handles tasks: " + servers[i].tasks);
            System.out.println("CPU Used: " + servers[i].usedCpu +
                               ", Memory Used: " + servers[i].usedMem +
                               ", Total Time: " + servers[i].time);
        }
    }
}
