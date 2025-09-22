class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101]; // since nums[i] ≤ 100
        int maxFreq = 0;
        
        // Count frequencies
        for (int num : nums) {
            freq[num]++;
            maxFreq = Math.max(maxFreq, freq[num]);
        }
        
        // Count total elements with max frequency
        int total = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                total += f;
            }
        }
        
        return total;
    }
}
