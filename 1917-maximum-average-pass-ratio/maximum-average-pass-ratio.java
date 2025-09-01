import java.util.*;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        
        for (int[] c : classes) {
            int pass = c[0], total = c[1];
            double gain = calcGain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }
        
        while (extraStudents-- > 0) {
            double[] top = pq.poll();
            int pass = (int) top[1], total = (int) top[2];
            pass++;
            total++;
            double gain = calcGain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }
        
        double sum = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            sum += cur[1] / cur[2];
        }
        
        return sum / classes.length;
    }
    
    private double calcGain(int pass, int total) {
        double before = (double) pass / total;
        double after = (double) (pass + 1) / (total + 1);
        return after - before;
    }
}
