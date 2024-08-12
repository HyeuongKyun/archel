import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int minCityDist;
    public static class Node {
        int r, c, d;// 좌표
        public Node(int r, int c,int d){
            this.r=r; this.c=c; this.d=d;
        }

    }

    static boolean[][] v;
    static int[][] map;
    static List<Node> rest, home;
    static boolean[] restV;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        rest = new ArrayList<>();
        home = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) rest.add(new Node(i,j,0));
                else if (map[i][j]==1) home.add(new Node(i,j,0));
            }
        }
        restV = new boolean[rest.size()];


        minCityDist = 10_000;

        combination(rest.size(), M, 0, 0);

        System.out.println(minCityDist);
    }

    public static void combination(int n, int r, int idx, int cnt){
        if(cnt==r){
            for(int i=0;i<restV.length;i++)
                if(!restV[i]) map[rest.get(i).r][rest.get(i).c]=0;

            int nowDist = calDist();
            if(nowDist<minCityDist) minCityDist = nowDist;

            for(int i=0;i<restV.length;i++)
                if(!restV[i]) map[rest.get(i).r][rest.get(i).c]=2;
            return;
        }

        for(int i=idx;i<n;i++){
            restV[i]=true;
            combination(n,r,i+1,cnt+1);
            restV[i]=false;
        }
    }

    public static int calDist(){
        int totalDist = 0;
        int N = map.length;
        for(int i=0;i<home.size();i++){
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(home.get(i).r,home.get(i).c,0));
            v = new boolean[N][N];
            v[home.get(i).r][home.get(i).c] = true;

            label : while(!q.isEmpty()){
                Node nowNode = q.poll();
                v[nowNode.r][nowNode.c]=true;

                for(int d=0; d<4; d++){
                    int nr = nowNode.r + dr[d];
                    int nc = nowNode.c + dc[d];
                    int nd = nowNode.d + 1;
                    boolean bound = nc>=0 && nc <N && nr>=0 && nr<N;
                    if (bound && !v[nr][nc]){
                        v[nr][nc]=true;
                        if(map[nr][nc]==2){
                            totalDist += nd;
                            break label;
                        }
                        else {
                            q.add(new Node(nr,nc,nd));
                        }
                    }
                }
            }

            if(totalDist > minCityDist) return 10_000;
        }
        return totalDist;
    }

}
