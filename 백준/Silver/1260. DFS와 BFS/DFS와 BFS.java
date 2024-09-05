import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static boolean[] v;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N+1];
         v = new boolean[N+1];
        for(int i=1;i<=N;i++) edges[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            edges[v1].add(v2);
            edges[v2].add(v1);
        }
        for(int i=1;i<edges.length;i++) Collections.sort(edges[i]);

        v[V] = true;
        dfs(V);
        sb.append("\n");
        v = new boolean[N+1];
        bfs(V);
        System.out.println(sb.toString());

    }

    static void dfs(int V){
        sb.append(V).append(" ");

        List<Integer> list = edges[V];
        for(int i=0;i<list.size();i++){
            int nextV = list.get(i);
            if(!v[nextV]) {
                v[nextV] = true;
                dfs(nextV);
            }
        }
    }

    static void bfs(int V){
        sb.append(V).append(" ");
        v[V] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        while(!q.isEmpty()){
            int nowV = q.poll();
            List<Integer> list = edges[nowV];
            for(int i=0;i<list.size();i++){
                int nextV = list.get(i);
                if(!v[nextV]){
                    v[nextV] = true;
                    sb.append(nextV).append(" ");
                    q.add(nextV);
                }
            }
        }
    }
}
