import java.io.BufferedReader;
import java.util.*;

public class Main {

    static class Node{
        int v; int t;
        Node(int v, int t){
            this.v=v; this.t=t;
        }
    }
    static Map<Integer, Boolean> v;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N,0));
        v = new HashMap<>();
        while(!q.isEmpty()){

            Node nowN = q.poll();
            v.put(nowN.v, true);

            if(nowN.v==K) {
                System.out.println(nowN.t);;
                break;
            } else if(nowN.v<0 || nowN.v> 100_000) continue;
            int nextN1 = nowN.v-1;
            int nextN2 = nowN.v*2;
            int nextN3 = nowN.v+1;

            if(!v.getOrDefault(nextN1,false)) q.add(new Node(nextN1, nowN.t+1));
            if(!v.getOrDefault(nextN2,false)) q.add(new Node(nextN2, nowN.t+1));
            if(!v.getOrDefault(nextN3,false)) q.add(new Node(nextN3, nowN.t+1));

        }

    }
}
