import java.io.*;
import java.util.*;

public class CheapestFlightPath {
    static class Edge { int to, w; Edge(int t, int w) { this.to = t; this.w = w; } }
    static class Node implements Comparable<Node> { int u, d; Node(int u, int d) { this.u = u; this.d = d; } public int compareTo(Node o) { return Integer.compare(this.d, o.d); } }

    public static int dijkstra(List<List<Edge>> g, int s, int t) {
        int n = g.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d != dist[cur.u]) continue;
            if (cur.u == t) return dist[t];
            for (Edge e : g.get(cur.u)) {
                int nd = dist[cur.u] + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new Node(e.to, nd));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        // Example usage: build graph with n cities
        // Replace with real data as needed
        int n = 5;
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());

        // add edges: from, to, weight
        // g.get(from).add(new Edge(to, weight));
        g.get(0).add(new Edge(1, 100));
        g.get(0).add(new Edge(2, 300));
        g.get(1).add(new Edge(2, 50));
        g.get(1).add(new Edge(3, 200));
        g.get(2).add(new Edge(3, 100));
        g.get(3).add(new Edge(4, 50));

        int src = 0, dest = 4;
        int cost = dijkstra(g, src, dest);
        System.out.println(cost); // prints the cheapest cost or -1 if unreachable
    }
}
