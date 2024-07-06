import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][]  v; // 벽
    static int cnt; // 분리된 땅 갯수
    static List<Integer> width;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken()); //y좌표, row
        int N = Integer.parseInt(st.nextToken()); //x좌표, col
        int K = Integer.parseInt(st.nextToken());

        v = new boolean[M][N];
        width = new ArrayList<>();

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int[] xy1 = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            int[] xy2 = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

            for(int x=xy1[0];x<xy2[0];x++)
                for(int y=xy1[1]; y<xy2[1]; y++) // y축 대칭이든 아니든 결과에는 상관 x
                    v[y][x] = true;
        }
        cnt = 0;

        for(int x=0;x<N;x++) {
            for (int y = 0; y < M; y++)
                if (!v[y][x]) {
                    v[y][x]=true;
                    cnt++;
                    width.add(0); //cnt 갯수만큼 0이 늘어남
                    dfs(x, y ,N, M);
                }
        }

        sb.append(cnt+"\n");
        Collections.sort(width);
        for(int i=0;i<width.size();i++)
            sb.append(width.get(i) + " ");

        System.out.println(sb.toString());
    }

    public static void dfs(int x, int y , int X, int Y){
        width.set(cnt-1,width.get(cnt-1)+1);

        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            boolean bound = nx >= 0 && nx < X && ny >= 0 && ny <Y;
            if(bound && !v[ny][nx]){
                v[ny][nx] = true;
                dfs(nx, ny, X, Y);
            }
        }
    }
}
