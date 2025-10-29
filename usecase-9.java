import java.util.*;

public class TrafficSignalSimulator {
    // Each approach (incoming road) has a density and a waitFactor
    static class Approach {
        int density;
        int waitFactor;
        Queue<Integer> q; // queue of vehicles (represented by arrival timestamps or IDs)
        int greenTime;    // allocated green time for current cycle (seconds)

        Approach(int density, int waitFactor) {
            this.density = density;
            this.waitFactor = waitFactor;
            this.q = new LinkedList<>();
            this.greenTime = 5; // minimum baseline
        }

        // simple enqueue based on density as a proxy for arrivals
        void simulateArrivals(int cycles, int cycleSeconds) {
            // fill some vehicles each cycle proportional to density
            int arrivalsPerCycle = Math.max(0, density / Math.max(1, cycles));
            for (int i = 0; i < arrivalsPerCycle; i++) q.add(0);
        }

        int queueSize() { return q.size(); }

        // advance one second of green time (very rough)
        void transportOneVehicle() {
            if (!q.isEmpty()) q.poll();
        }
    }

    public static void main(String[] args) {
        // Example: 4 approaches with varying density and waitFactor
        Approach[] approaches = new Approach[] {
            new Approach( density(1), wait(1) ),
            new Approach( density(3), wait(2) ),
            new Approach( density(2), wait(3) ),
            new Approach( density(4), wait(1) )
        };

        int n = approaches.length;
        int totalCycle = 300; // seconds
        int baselineMin = 5;  // minimum green per approach
        for (Approach a : approaches) a.greenTime = baselineMin;

        // Simple greedy allocation: in each cycle, give more green time to higher
        // density * waitFactor * currentQueue (or a proxy if queues are small)
        // Then bound by totalCycle
        int cyclesToSimulate = 10;
        Random rand = new Random(42);

        for (int cycle = 0; cycle < cyclesToSimulate; cycle++) {
            // simulate arrivals for this cycle
            for (Approach a : approaches) a.simulateArrivals(cyclesToSimulate, totalCycle);

            // compute scores and allocate greedily
            long sumScores = 0;
            long[] scores = new long[n];
            for (int i = 0; i < n; i++) {
                Approach a = approaches[i];
                long s = (long)a.density * (long)a.waitFactor * (long)Math.max(1, a.queueSize());
                scores[i] = s;
                sumScores += s;
            }

            // avoid division by zero
            if (sumScores == 0) sumScores = 1;

            // allocate green times proportionally, with a cap per cycle
            int remaining = totalCycle;
            int allocated = 0;
            for (int i = 0; i < n; i++) {
                // proportion
                int alloc = (int)Math.round((scores[i] * totalCycle) / (double)sumScores);
                // enforce minimum
                alloc = Math.max(baselineMin, alloc);
                // cap so total doesn't exceed
                approaches[i].greenTime = alloc;
                allocated += alloc;
            }

            // if over totalCycle, scale down proportionally
            if (allocated > totalCycle) {
                double scale = totalCycle / (double)allocated;
                for (Approach a : approaches) a.greenTime = Math.max(baselineMin, (int)Math.round(a.greenTime * scale));
            }

            // simulate traffic during this cycle with the allocated greens
            int secondsUsed = 0;
            // naive: for each approach, allow greenTime seconds of service
            for (Approach a : approaches) {
                int t = a.greenTime;
                secondsUsed += t;
                // serve up to t vehicles from queue
                for (int s = 0; s < t; s++) a.transportOneVehicle();
            }

            // add a tiny random fluctuation to mimic variation
            for (Approach a : approaches) {
                if (!a.q.isEmpty() && rand.nextBoolean()) a.q.add(0);
            }

            // print snapshot
            System.out.print("Cycle " + (cycle+1) + ": ");
            for (int i = 0; i < n; i++) {
                System.out.print("A" + i + "[g=" + approaches[i].greenTime + ",q=" + approaches[i].queueSize() + "] ");
            }
            System.out.println("(cycleTime=" + totalCycle + ")");
        }

        // Final state summary
        System.out.println("Final green allocations:");
        for (int i = 0; i < approaches.length; i++) {
            System.out.println("Approach " + i + ": greenTime=" + approaches[i].greenTime + "s, queue=" + approaches[i].queueSize());
        }
    }

    // helper to provide plausible densities and waits if you want defaults
    private static int density(int v) { return v; }
    private static int wait(int v) { return v; }
}
