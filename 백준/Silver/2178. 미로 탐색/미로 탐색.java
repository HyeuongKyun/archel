import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        char[][] map = new char[N][M];
        for(int n=0;n<N;n++)
            map[n] = br.readLine().toCharArray();

        boolean[][] v = new boolean[N][M];
        System.out.println(bfs(N, M, map, v));

    }

    public static int bfs(int N, int M, char[][] map, boolean[][] v){
        int[] start = new int[]{0,0,1};
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        v[0][0]=true;

        while(!q.isEmpty()) {
            int[] nowTarget = q.poll();
            if(nowTarget[0]==N-1 && nowTarget[1]==M-1) return nowTarget[2];
            for(int d=0; d<4;d++){
                int nr = nowTarget[0] + dr[d];
                int nc = nowTarget[1] + dc[d];
                int nv = nowTarget[2] + 1;
                boolean bound = nr>=0 && nr<N && nc>=0 && nc<M;
                if(bound && map[nr][nc]=='1' && !v[nr][nc]){
                    v[nr][nc] = true;
                    q.add(new int[]{nr,nc,nv});
                }
            }
        }
        return 0;//항상 도착할 수 있는 경우만 나오니 무슨 값을 넣어도 상관 없다.
    }
}
