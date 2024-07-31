import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 100_000_000; //-60,000,000~60,000,000
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] dist = new long[N+1];
        Arrays.fill(dist,INF);

        int[][] edges = new int[M][3];
        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());
            edges[m] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }

        boolean cycle = bellmanford(N,M,dist,edges);
        StringBuilder sb = new StringBuilder();
        if(cycle) sb.append(-1);
        else {
            for(int n=2;n<=N;n++){
                if(dist[n]==INF) sb.append(-1).append("\n");
                else sb.append(dist[n]).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean bellmanford(int N, int M, long[] dist, int[][] edges){
        dist[1]=0;
        boolean cycle = false;
        for(int n=1;n<=N;n++){
            for(int m=0;m<M;m++){
                int nowV = edges[m][0];
                int nextV = edges[m][1];
                int cost = edges[m][2];
                if(dist[nowV]==INF) continue;
                if(dist[nowV]+cost < dist[nextV]) {
                    dist[nextV] = dist[nowV]+cost;
                    if(n==N) {
                        cycle=true;
                    }
                }
            }
        }

        return cycle;

    }
}

//3 4
//1 2 4
//1 3 3
//2 3 -4
//3 1 -2

//-1