import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 50_000_001; //2*2,500*10,000 = 50,000,000 (양방향, 간선 갯수, 가중치)
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());


        for(int tc=0;tc<TC;tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[][] edges = new int[M+W][3];
            for(int mw=0;mw<M+W;mw++){
                st = new StringTokenizer(br.readLine());
                if(mw<M) edges[mw] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                else edges[mw] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt("-"+st.nextToken())};
            }

            long[] dist = new long[N+1];
            Arrays.fill(dist,INF);

            boolean cycle = bellmanford(1,N,M,W,edges,dist);
            
            if(cycle)sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }

    public static boolean bellmanford(int start, int N, int M, int W, int[][] edge, long[] dist){
        dist[start]=0;
        for(int n=1;n<=N;n++){
            for(int mw=0;mw<M+W;mw++){
                int S =edge[mw][0];
                int E =edge[mw][1];
                int T =edge[mw][2];
                if(T>=0){ //도로(양방향)
                    for(int i=0;i<2;i++){
                        int s = S; int e = E;
                        if (i==1){s = E; e = S;}
                        if(dist[s]==INF) continue;
                        if (dist[s]+T<dist[e]) {
                            dist[e] = dist[s] + T;
                            if(n==N) {
                                return true;
                            }
                        }
                    }
                } else { //웜홀(단방향)
                    if (dist[S]+T<dist[E]) {
                        dist[E] = dist[S] + T;
                        if(n==N) {
                            return true;
                        }
                    }
                }
            }


        }
        return false;
    }
}

