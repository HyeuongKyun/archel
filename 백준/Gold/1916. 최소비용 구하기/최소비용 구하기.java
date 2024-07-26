import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int[][] valMap = new int[N+1][N+1];
        int INF = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++) Arrays.fill(valMap[i], INF);

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            if(valMap[n][m]>val) valMap[n][m]=val;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        Long[] d = new Long[N+1];
        boolean[] v = new boolean[N+1];
        Arrays.fill(d, (long) INF);

        //////인자 값 받기 끝

        dijkstra(N, start, valMap, d, v);

        System.out.println(d[end]);



    }

    public static int getSmallIdx(Long[] d, boolean[] v){
        Long min = Long.MAX_VALUE;
        int idx=0;
        for(int i=1;i<d.length;i++){
            if(!v[i] && d[i]<min){
                min = d[i];
                idx = i;
            }
        }
        return idx;
    }

    public static void dijkstra(int N, int start, int[][] valMap,Long[] d, boolean[] v){
        for(int i=1;i<=N;i++) d[i] = (long) valMap[start][i];
        v[start] = true;
        d[start] = 0L;

        for(int i=1;i<=N-2;i++){
            int idx = getSmallIdx(d,v);
            v[idx] = true;
            for(int j=1;j<=N;j++){
                if(!v[j] && d[idx]+valMap[idx][j] < d[j])  d[j] = d[idx]+valMap[idx][j];
            }
        }


    }
}