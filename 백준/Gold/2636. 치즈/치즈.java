import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static class Node{
        int r; int c;
        Node(int r, int c){
            this.r=r; this.c=c;
        }
    }
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int cheezCnt = 0;


        int[][] map = new int[R][C];
        for(int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<C;c++){
                int isCheeze = Integer.parseInt(st.nextToken());
                if(isCheeze==1){
                    map[r][c] = 1;
                    cheezCnt++;
                }
            }
        }

        boolean[][] v;

        int qSize = 0;
        int cnt=0;
        while(cheezCnt>0){
            cnt++;
            v = new boolean[R][C];
            v[0][0] = true;
            dfs(0,0,R,C,map,v); //가장자리는 치즈가 안 놓인다.
            qSize = q.size();
            for(int i=0; i<qSize; i++){
                Node meltedCheeze = q.poll();
                map[meltedCheeze.r][meltedCheeze.c] = 0;
            }
            cheezCnt-=qSize;

        }

        System.out.printf("%d\n%d\n",cnt,qSize);

    }

    public static void dfs(int r, int c, int R, int C, int[][] map, boolean[][] v){

        for(int d=0;d<4;d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            boolean bound = nr>=0 && nr<R && nc>=0 && nc<C;
            if(bound && !v[nr][nc]){
                if(map[nr][nc]==0){
                    v[nr][nc] = true;
                    dfs(nr,nc,R,C,map,v);
                } else {
                    v[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }

            }

        }
    }
}
