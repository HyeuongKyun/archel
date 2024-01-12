// import java.util.*;

// class Solution {
//     static int[][] map;
//     static int N,M;//답,행,렬
//     static int[] dr={0,1,0,-1};
//     static int[] dc={1,0,-1,0};
//     static boolean[][] v;
//     static Queue<int[]> q;
//     public int solution(int[][] maps) {
//         this.map = maps;
//         N=map.length;
//         M=map[0].length;
//         v = new boolean[N][M];
//         q = new LinkedList<>();
//         q.add(new int[]{0,0,1});
//         v[0][0]=true;
        
//         int answer = bfs();
        
//         return answer;
        
//     }//main
    
//     private static int bfs(){
//         while(!q.isEmpty()){
//             int[] temp = q.poll();
//             int row = temp[0];
//             int col = temp[1];
//             int moveCnt = temp[2];
//             if(row==N-1&&col==M-1)
//                 return moveCnt;
            
//             for(int d=0;d<4;d++){
//                 int nr = row+dr[d];
//                 int nc = col+dc[d];
//                 boolean bound = (nr>=0&&nr<N)  && (nc>=0&&nc<M);
//                 if(!bound||map[nr][nc]==0||v[nr][nc]) continue;
//                 v[nr][nc] = true;
//                 q.add(new int[]{nr,nc,moveCnt+1});
//             }
            
//         }
//         return -1;
//     }
// }

import java.util.*;

class Solution {
    static int[] dr={0,1,0,-1};
    static int[] dc={1,0,-1,0};
    static boolean[][] v;
    static Queue<int[]> q;
    public int solution(int[][] maps) {
        int mapsR=maps.length;
        int mapsC=maps[0].length;
        v= new boolean[mapsR][mapsC];
        q= new LinkedList<>();
        q.add(new int[]{0,0,1}); //마지막 1은 시작점으로 부터의 거리
        
        
        int answer = bfs(mapsR, mapsC, maps);;
        return answer;
    }
    
    int bfs(int mapsR, int mapsC, int[][] maps){
        
        while(!q.isEmpty()){
            
            int[] nP= q.poll();
            int r = nP[0];
            int c = nP[1];
            // v[r][c]=true;
            
            if(r==mapsR-1 && c==mapsC-1)
                return nP[2];

            for(int d=0;d<4;d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                boolean bound = nr>=0 && nr<mapsR && nc>=0 && nc<mapsC;
                // if(bound && !v[nr][nc] && maps[nr][nc]==1) {
                if(!bound||maps[nr][nc]==0||v[nr][nc]) continue;
                v[nr][nc]=true;
                q.add(new int[]{nr,nc,nP[2]+1});
                // }
            }
        }//while
            return -1; // 출구가 없을 시
    
    }
}