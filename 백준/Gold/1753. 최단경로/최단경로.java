import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int V, E, K;
	static ArrayList<Node>[] list;
	static int[] dist;
	static int INF = 9999_9999;
	
	public static void main(String[] args) {
		// 정점의 개수 V
		// 간선의 개수 E
		// 정점은 1 ~ V
		// 시작 정점 K
		// u v w
		// u 에서 v 로 가는 가중치 w
		
		int u, v, w;
		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt(); // 정점 개수
		E = sc.nextInt(); // 간선 개수
		K = sc.nextInt(); // 시작 정점
		list = new ArrayList[V+1]; // 그래프
		dist = new int[V+1]; // 방문
		for(int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		Arrays.fill(dist, INF);
		dist[K] = 0;
		
		for(int i = 0; i < E; i++) { // 간선 개수만큼 반복
			u = sc.nextInt(); //출발
            v = sc.nextInt(); //도착지
            w = sc.nextInt(); //가중치
            list[u].add(new Node(v, w));
		}
		
		dijkstra();
		
		for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(K, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int vertex = node.vertex;
            int weight = node.weight;
            if (dist[vertex] < weight) { //지금께 더 가중치가 크면 갱신할 필요가 없다.
                continue;
            }
            for (int i = 0; i < list[vertex].size(); i++) {//해당 정점과 연결된 것들 탐색
                int vertex2 = list[vertex].get(i).vertex;
                int weight2 = list[vertex].get(i).weight + weight;
                if (dist[vertex2] > weight2) { //지금께 더 최단경로라면 갱신해준다.
                	dist[vertex2] = weight2;
                    queue.add(new Node(vertex2, weight2));
                }
            }
        }
	}
}

class Node implements Comparable<Node> {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}