import java.util.*;

class Solution {
    static int[][] map;//경우의 수를 기록할 2차원배열
    public int solution(int m, int n, int[][] puddles) {
        map = new int[n+1][m+1];
        // for(int i=1;i<=m;i++){
        //     map[1][i]=1;//첫행에서 가로로 뻗어나갈 수 있는 경우는 1뿐이다.
        // }//가로세로 갈 수 있는 경우 우선 표기
        // for(int j=1;j<=n;j++){
        //     map[j][1]=1;//첫열에서 세로로 뻗어나갈 수 있는 경우는 1뿐이다.
        // }
        for(int k=0;k<puddles.length;k++){//웅덩이 있는 곳은 못지나가는 곳으로 -1 표기
            int tempI = puddles[k][0];
            int tempJ = puddles[k][1];
            map[tempJ][tempI] = -1;
        }//for
        map[1][1]=1;
        //이제 이차원 배열의 갈 수 있는 경우를 하나하나 더해간다.
        for(int i=1;i<=n;i++){//1은 이미 위에서 처리해주었으므로 생각 안해도 된다.
            for(int j=1;j<=m;j++){
                
                if(map[i][j]==-1) continue;//구하고자 하는 값이 웅덩이일때는 그냥 넘어간다.
                int tempJ = map[i][j-1];//구하고자 하는 값보다 왼쪽
                int tempI = map[i-1][j];//구하고자 하는 값보다 위쪽
                // if(tempI!=-1 && tempJ!=-1){//위, 왼쪽 모두 지나올 수 있는 경로일 때 
                    // map[i][j] = (tempI+tempJ)%1000000007;
                // }else if(tempI!=-1 && tempJ==-1){//왼쪽은 웅덩이 그럼 위로만 올 수 있다.
                    // map[i][j] = tempI;                    
                // }else if(tempI==-1 && tempJ!=-1){
                    // map[i][j] = tempJ;
                if(tempI>0) map[i][j] += tempI % 1000000007;
                if(tempJ>0) map[i][j] += tempJ % 1000000007;
                
                
                // }//둘 다 -1 일 때는 올 수 있는 경로가 없으므로 그냥 계산 안해도된다.
            }//for
        }//for
        
        // for(int i=1;i<=n;i++){
        //     for(int j=1;j<=m;j++){
        //         System.out.printf("%d ",map[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return map[n][m]%1000000007;
    }
}