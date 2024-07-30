import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Integer N = 0;
    static int INF = 10000;
    static int[][][] map;
    static StringBuilder sb = new StringBuilder();
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static class Node implements Comparable<Node>{
        int r; int c; int total;

        public Node(int r, int c, int total){
            this.r=r; this.c=c; this.total=total;
        }
        @Override
        public int compareTo(Node o){
            return this.total-o.total;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N==0) break;

            map = new int[N][N][2]; // 비용, 가중치
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    map[i][j][0] = Integer.parseInt(st.nextToken());
                    map[i][j][1] = INF;
                }
            }

            dijkstra(idx++);

        }

        System.out.println(sb);

    }

    public static void dijkstra(int idx){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,map[0][0][0]));

        while(!pq.isEmpty()){
            Node nowNode = pq.poll();
            int temp = 0;
            if(nowNode.r==N-1 && nowNode.c==N-1){
                sb.append("Problem ").append(idx).append(": ").append(nowNode.total).append("\n");
                return;
            }
            for(int d=0;d<4;d++){
                int nr = nowNode.r +dr[d];
                int nc = nowNode.c +dc[d];
                boolean boundary = nr>=0 && nr<N && nc>=0 && nc<N;
                if(boundary) {
                    int val = nowNode.total + map[nr][nc][0];
                    if(val < map[nr][nc][1]){
                        map[nr][nc][1] = val;
                        pq.add(new Node(nr,nc,val));
                    }
                }
            }
        }

    }
}
