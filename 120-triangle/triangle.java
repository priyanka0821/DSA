class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // Start from the second last row and move upward
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int down = triangle.get(i + 1).get(j);
                int diagonal = triangle.get(i + 1).get(j + 1);
                int updated = triangle.get(i).get(j) + Math.min(down, diagonal);
                triangle.get(i).set(j, updated);
            }
        }
        
        // The top element now contains the minimum path sum
        return triangle.get(0).get(0);
    }
}
