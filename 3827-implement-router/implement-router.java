import java.util.*;

class Router {
    private int memoryLimit;
    private Queue<int[]> queue;  
    private Set<String> seen;   
    private Map<Integer, List<int[]>> destMap; 
    // dest -> list of [timestamp, cumulativeCount]

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new LinkedList<>();
        this.seen = new HashSet<>();
        this.destMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;
        if (seen.contains(key)) return false; // duplicate

        // If full → evict oldest
        if (queue.size() == memoryLimit) {
            int[] old = queue.poll();
            String oldKey = old[0] + "#" + old[1] + "#" + old[2];
            seen.remove(oldKey);
            removeFromDestMap(old[1], old[2]);
        }

        queue.offer(new int[]{source, destination, timestamp});
        seen.add(key);

        addToDestMap(destination, timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[]{};
        int[] pkt = queue.poll();
        String key = pkt[0] + "#" + pkt[1] + "#" + pkt[2];
        seen.remove(key);

        removeFromDestMap(pkt[1], pkt[2]);
        return pkt;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) return 0;
        List<int[]> list = destMap.get(destination);

        // binary search: prefix sum up to endTime
        int endIdx = upperBound(list, endTime);
        int startIdx = lowerBound(list, startTime);

        if (endIdx < 0 || startIdx < 0) return 0;
        return list.get(endIdx)[1] - (startIdx > 0 ? list.get(startIdx - 1)[1] : 0);
    }

    // ---- Helper functions ----

    private void addToDestMap(int dest, int timestamp) {
        destMap.putIfAbsent(dest, new ArrayList<>());
        List<int[]> list = destMap.get(dest);

        int newCount = (list.isEmpty() ? 0 : list.get(list.size()-1)[1]) + 1;
        if (!list.isEmpty() && list.get(list.size()-1)[0] == timestamp) {
            // same timestamp → just increase last cumulative
            list.get(list.size()-1)[1] = newCount;
        } else {
            list.add(new int[]{timestamp, newCount});
        }
    }

    private void removeFromDestMap(int dest, int timestamp) {
        List<int[]> list = destMap.get(dest);
        if (list == null || list.isEmpty()) return;

        // Find index of timestamp (binary search)
        int idx = lowerBound(list, timestamp);
        if (idx == -1 || list.get(idx)[0] != timestamp) return;

        // decrease counts from idx onwards
        for (int i = idx; i < list.size(); i++) {
            list.get(i)[1] -= 1;
        }
        // cleanup if last count == 0
        if (list.get(list.size()-1)[1] == 0) list.remove(list.size()-1);
        if (list.isEmpty()) destMap.remove(dest);
    }

    // first index with timestamp >= target
    private int lowerBound(List<int[]> list, int target) {
        int lo = 0, hi = list.size()-1, ans = -1;
        while (lo <= hi) {
            int mid = (lo+hi)/2;
            if (list.get(mid)[0] >= target) {
                ans = mid; hi = mid-1;
            } else lo = mid+1;
        }
        return ans;
    }

    // last index with timestamp <= target
    private int upperBound(List<int[]> list, int target) {
        int lo = 0, hi = list.size()-1, ans = -1;
        while (lo <= hi) {
            int mid = (lo+hi)/2;
            if (list.get(mid)[0] <= target) {
                ans = mid; lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }
}
