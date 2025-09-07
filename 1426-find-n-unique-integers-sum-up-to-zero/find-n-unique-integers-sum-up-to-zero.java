class Solution {
    public int[] sumZero(int n) {
        int arr[]=new int[n];
        
            int i=0;
           while(i<n-1){
                arr[i]=i+1;
                arr[i+1]=0-arr[i];
                i=i+2;
            }
            if(n%2 !=0) arr[i]=0;
        return arr;
    }
}