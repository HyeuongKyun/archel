import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int dr[] = {0,1,0,-1};
    static int dc[] = {1,0,-1,0};
    public static class Node implements Comparable<Node>{
        int r; int c; int dist;
        Node(int r, int c, int dist){
            this.r=r;
            this.c=c;
            this.dist=dist;
        }

        @Override
        public int compareTo(Node o){
            return this.dist-o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] bMap = new boolean[N][M];

        for(int n=0;n<N;n++){
            String[] line = br.readLine().split("");
            for(int m=0;m<M;m++) map[n][m] = Integer.parseInt(line[m]);
        }

        System.out.println(bfs(M,N,map, bMap));

    }

    public static int bfs(int M, int N, int[][] map, boolean[][] bMap){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));
        bMap[0][0]=true;
        while(!pq.isEmpty()){
            Node nowNode = pq.poll();
            if(nowNode.r==N-1 && nowNode.c==M-1) return nowNode.dist;
            for(int d=0;d<4;d++){
                int nc = nowNode.c + dc[d];
                int nr = nowNode.r + dr[d];
                boolean boundary = nc>=0 && nc<M && nr>=0 && nr<N;
                if(boundary && !bMap[nr][nc]){
                    bMap[nr][nc]=true;
                    if(map[nr][nc]==1) pq.add(new Node(nr,nc,nowNode.dist+1));
                    else pq.add(new Node(nr,nc, nowNode.dist));
                }
            }
        }
        return 0;
    }

}
