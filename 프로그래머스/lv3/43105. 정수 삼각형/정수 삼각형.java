class Solution {
    static int[][] triangle;
    static int maxVal;
    
    public int solution(int[][] triangle) {
        this.triangle = triangle;
        int N = triangle.length;
        
        for(int i=1;i<N;i++){//0인덱스는 덧셈이 발생 안하니까 pass
            sumBigger(i);
        }
        maxVal = 0;
        for(int i=0;i<N;i++){
            int temp = triangle[N-1][i];
            if(maxVal<temp) maxVal = temp;
        }
        // for(int i=0;i<N;i++){
        //     for(int j=0;j<triangle[i].length;j++){
        //         System.out.printf("%d ",triangle[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return maxVal;
    }//solution
    
    static void sumBigger(int level){
        triangle[level][0] += triangle[level-1][0];
        for(int i=1;i<level;i++){
            int temp1 = triangle[level-1][i-1];
            int temp2 = triangle[level-1][i];
            int temp = Math.max(temp1,temp2);
            triangle[level][i] += temp;
        }
        triangle[level][level] += triangle[level-1][level-1];
        
    }//sumBigger
}