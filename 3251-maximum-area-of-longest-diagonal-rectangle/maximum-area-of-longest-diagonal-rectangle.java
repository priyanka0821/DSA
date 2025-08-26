
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxArea=0;
        double maxD=0;
        for(int i=0;i<dimensions.length;i++){
            double dL=Math.sqrt((dimensions[i][0]*dimensions[i][0])+(dimensions[i][1]*dimensions[i][1]));
            if(maxD<dL){
                maxD=dL;
                maxArea=dimensions[i][0]*dimensions[i][1];
            }if(maxD==dL){
                if(maxArea<(dimensions[i][0]*dimensions[i][1])){
                    maxD=Math.sqrt((dimensions[i][0]*dimensions[i][0])+(dimensions[i][1]*dimensions[i][1]));
                    maxArea=dimensions[i][0]*dimensions[i][1];
                }
            }

        }
        return maxArea;
    }
}