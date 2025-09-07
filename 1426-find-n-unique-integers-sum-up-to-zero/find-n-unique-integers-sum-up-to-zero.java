class Solution {
    public int[] sumZero(int n) {
        int arr[]=new int[n];
        if(n%2==0){
            int i=0;
           while(i<n){
                arr[i]=i+1;
                arr[i+1]=0-arr[i];
                i=i+2;
            }
        }else{
            arr[0]=0;
            int i=1;
            while(i<n){
                arr[i]=i+1;
                arr[i+1]=0-arr[i];
                i=i+2;
            }
        }
        return arr;
    }
}