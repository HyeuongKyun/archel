import java.util.*;

class Solution {
    static int[][] map;
    static int N,M;//답,행,렬
    static int[] dr={0,1,0,-1};
    static int[] dc={1,0,-1,0};
    static boolean[][] v;
    static Queue<int[]> q;
    public int solution(int[][] maps) {
        this.map = maps;
        N=map.length;
        M=map[0].length;
        v = new boolean[N][M];
        q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        v[0][0]=true;
        
        int answer = bfs();
        
        return answer;
        
    }//main
    
    private static int bfs(){
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int row = temp[0];
            int col = temp[1];
            int moveCnt = temp[2];
            if(row==N-1&&col==M-1)
                return moveCnt;
            
            for(int d=0;d<4;d++){
                int nr = row+dr[d];
                int nc = col+dc[d];
                boolean bound = (nr>=0&&nr<N)  && (nc>=0&&nc<M);
                if(!bound||map[nr][nc]==0||v[nr][nc]) continue;
                v[nr][nc] = true;
                q.add(new int[]{nr,nc,moveCnt+1});
            }
            
        }
        return -1;
    }
    
    
//     private static void dfs(int row, int col, int moveCnt){
//         // v[row][col]=true;
//         if(row==N-1 && col==M-1){
//             if(moveCnt<minCnt) minCnt = moveCnt;
//             return;
//         }//if
        
//         for(int d=0;d<4;d++){
//             int nr= row+dr[d];
//             int nc= col+dc[d];
//             boolean bound = (nr>=0&&nr<N) && (nc>=0&&nc<M);
//             if(!bound||map[nr][nc]==0||v[nr][nc]) continue;
//             v[nr][nc]=true;
//             dfs(nr,nc,moveCnt+1);
//             v[nr][nc]=false;
            
//         }//for d
        
//     }//dfs
}//class