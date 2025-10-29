import java.io.*;
import java.util.*;

public class ConnectGroupsMinCost {
    static class Edge implements Comparable<Edge> { int u,v,w; Edge(int u,int v,int w){this.u=u;this.v=v;this.w=w;} public int compareTo(Edge o){ return Integer.compare(this.w, o.w);} }

    static class UnionFind {
        int[] p, r;
        UnionFind(int n){ p=new int[n]; r=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x){ return p[x]==x? x: (p[x]=find(p[x])); }
        boolean union(int a,int b){
            int pa=find(a), pb=find(b);
            if(pa==pb) return false;
            if(r[pa]<r[pb]) { p[pa]=pb; }
            else if(r[pb]<r[pa]) { p[pb]=pa; }
            else { p[pb]=pa; r[pa]++; }
            return true;
        }
    }

    public static int minimumCostToConnectAll(int n, List<Edge> edges) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        int cost = 0, taken = 0;
        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                cost += e.w;
                taken++;
                if (taken == n - 1) break;
            }
        }
        return (taken == n - 1) ? cost : -1;
    }

    public static void main(String[] args) {
        // Example: n points across two groups; add all relevant edges with costs
        int n = 6; // total points
        List<Edge> edges = new ArrayList<>();
        // edges between points with given costs
        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,2));
        edges.add(new Edge(1,2,1));
        edges.add(new Edge(3,4,7));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(2,3,5));
        // Add all necessary edges for your concrete problem

        int minCost = minimumCostToConnectAll(n, edges);
        System.out.println(minCost);
    }
}
