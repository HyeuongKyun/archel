import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int maxVal = 100_000;
    public static int minVal = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] v = new boolean[maxVal+1];

        완전탐색(v, N,K);

        System.out.println(minVal);

    }

    public static void 완전탐색(boolean[] v, int N, int K){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N,0));

        while (!q.isEmpty()){
            Node nowNode = q.poll();
            v[nowNode.vertex] = true;
            if (nowNode.vertex==K && nowNode.dist<minVal) minVal = nowNode.dist;
            if (nowNode.vertex*2 <= maxVal && !v[nowNode.vertex*2]) q.add(new Node(nowNode.vertex*2,nowNode.dist));
            if (nowNode.vertex+1 <= maxVal && !v[nowNode.vertex+1]) q.add(new Node(nowNode.vertex+1,nowNode.dist+1));
            if (nowNode.vertex-1 >= 0 && !v[nowNode.vertex-1]) q.add(new Node(nowNode.vertex-1,nowNode.dist+1));
        }
    }

    public static class Node{
        int vertex;
        int dist;

        Node(int vertex, int dist){
            this.vertex = vertex;
            this.dist = dist;
        }
    }

}

