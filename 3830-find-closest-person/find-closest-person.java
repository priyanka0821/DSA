class Solution {
    public int findClosest(int x, int y, int z) {
        int dX=Math.abs(x-z);
        int dY=Math.abs(y-z);
        if(dX<dY){
            return 1;
        }else if(dX>dY){
            return 2;
        }else{
            return 0;
        }
    }
}