class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        Set<Integer> problemUsers = new HashSet<>();

        // Step 1: Find friendships where communication is not possible
        for (int[] f : friendships) {
            int u = f[0] - 1;
            int v = f[1] - 1;
            boolean canCommunicate = false;

            for (int lu : languages[u]) {
                for (int lv : languages[v]) {
                    if (lu == lv) {
                        canCommunicate = true;
                        break;
                    }
                }
                if (canCommunicate) break;
            }

            if (!canCommunicate) {
                problemUsers.add(u);
                problemUsers.add(v);
            }
        }

        // Step 2: Find minimum users to teach
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int teachCount = 0;
            for (int user : problemUsers) {
                boolean knows = false;
                for (int l : languages[user]) {
                    if (l == lang) {
                        knows = true;
                        break;
                    }
                }
                if (!knows) teachCount++;
            }
            minTeach = Math.min(minTeach, teachCount);
        }

        return minTeach;
    }
}
