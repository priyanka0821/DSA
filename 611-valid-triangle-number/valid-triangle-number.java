import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums); // Step 1: sort
        int n = nums.length;
        int count = 0;

        // Step 2: fix largest side
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i); // all pairs between i..j-1 are valid
                    j--;
                } else {
                    i++;
                }
            }
        }

        return count;
    }
}
