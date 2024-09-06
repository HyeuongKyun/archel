import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for(int tc=0;tc<TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            boolean[][] v = new boolean[N][M];
            int[][] map = new int[N][M];

            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
            }

            int cnt = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(v[i][j] || map[i][j]==0) continue;
                    dfs(i,j,v,map,N,M);
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());

    }

    public static void dfs(int r, int c, boolean[][] v, int[][] map, int N, int M){

        for(int d=0;d<4;d++){
            int nr = dr[d] + r;
            int nc = dc[d] + c;
            boolean bound = nr >= 0 && nr<N && nc >= 0 && nc<M;
            if(bound && !v[nr][nc] && map[nr][nc]==1){
                v[nr][nc] = true;
                dfs(nr,nc,v,map,N,M);
            }
        }

    }
}
