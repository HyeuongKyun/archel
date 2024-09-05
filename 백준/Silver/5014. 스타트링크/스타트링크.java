import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static class Node{
        int p; int cnt;
        Node(int p, int cnt){
            this.p=p; this.cnt=cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int F = sc.nextInt(); //F층 건물 1_000_000
        int S = sc.nextInt(); //시작점 1_000_000
        int G = sc.nextInt(); //목적지 1_000_000
        int U = sc.nextInt(); //위로이동 1_000_000
        int D = sc.nextInt(); //아래이동 1_000_000

        //1-3-5-7-9-8-10 번

        boolean[] v = new boolean[F+1];
        //bfs
        bfs(F,S,G,U,D,v);

    }

    public static void bfs(int F, int S, int G, int U, int D, boolean[] v){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(S,0));
        v[S] = true;
        while(!q.isEmpty()){
            Node nowN = q.poll();
            if(nowN.p==G) {
                System.out.println(nowN.cnt);
                return;
            }
            int nextP1 = nowN.p + U;
            int nextP2 = nowN.p - D;
            if(nextP1 >0 && nextP1<=F && !v[nextP1]){
                v[nextP1]=true;
                q.add(new Node(nextP1, nowN.cnt+1));
            }
            if(nextP2 >0 && nextP2<=F && !v[nextP2]){
                v[nextP2]=true;
                q.add(new Node(nextP2, nowN.cnt+1));
            }
        }
        System.out.println("use the stairs");
    }
}
