import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 알고리즘 다익스트라
// List<Node>[]를 활용해서 메모리 용량을 고려하고 필요한 인덱스만을 사용해여 시간 초과도 관리 nlogn
// 가중치의 최대값이 10이기 때문에 10 * 20,000 = 200,000 을 넘을 수 없어서 INF =200,000으로 잡기
public class Main {
    public static int INF = 200_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Integer start = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        List<Node>[] nodeList = new ArrayList[V+1];
        for(int i=1;i<=V;i++) nodeList[i] = new ArrayList<>();
//        Arrays.fill(nodeList,new ArrayList<>());

        for(int e=1;e<=E;e++){
            st = new StringTokenizer(br.readLine());
            int beforeV = Integer.parseInt(st.nextToken());
            int AfterV = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            nodeList[beforeV].add(new Node(AfterV, val));
        }


        int[] dp = new int[V+1];
        Arrays.fill(dp,INF);

        dijkstra(start, dp, nodeList, V, E);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++) {
            if(dp[i]==INF) sb.append("INF").append("\n");
            else sb.append(dp[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(Integer start, int[] dp, List<Node>[] nodeList, int V, int E){

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        dp[start] = 0;

        while (!pq.isEmpty()){
            Node tmpNode = pq.poll();
            int tmpVertex = tmpNode.vertex;
            int tmpVal = tmpNode.val;
            if(tmpVal > dp[tmpVertex]) continue;

            for(int i=0;i<nodeList[tmpVertex].size();i++){
                Node nextNode = nodeList[tmpVertex].get(i);
                int nextVertex = nextNode.vertex;
                int nextVal = nextNode.val;
                if(dp[nextVertex] > dp[tmpVertex] + nextVal){
                    dp[nextVertex] = dp[tmpVertex] + nextVal;
                    pq.add(new Node(nextVertex, dp[nextVertex]));
                }

            }
        }
    }

    public static class Node implements Comparable<Node>{
        int vertex;
        int val;

        Node(int vertex, int val){
            this.vertex = vertex;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val-o.val;
        }
    }
}
