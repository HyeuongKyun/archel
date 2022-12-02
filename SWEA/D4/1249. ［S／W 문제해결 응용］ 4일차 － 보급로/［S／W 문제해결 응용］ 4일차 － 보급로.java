import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
    static class Node implements Comparable<Node> {
        Node(int myRow,int myCol,int value){
            this.myRow = myRow;
            this.myCol = myCol;
            this.value = value;
        }
        //다잌스트라를 하면서 누적값 중 최소값으로 갱신된다.
        int myMinDis = Integer.MAX_VALUE;
        //이 아래값은 노드의 인덱스를 기록하기 위함인데 이걸 굳이하는 이유는
        //pq를 써서 최소값을 사용할꺼라 인덱스가 나중에 다시 필요해져서이다.
        int myRow = -1;
        int myCol = -1;
        int value = 0;

        @Override
        public int compareTo(Node o) {//같을 땐 바뀌어도 안바뀌어도 상관없다.
            if(this.myMinDis>o.myMinDis) return 1;
            else return -1;
        }
    }
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    //방문한 곳을 또 방문하지 않기 위해서
    static boolean[][] v;
    static Node[][] map;
    static int minDis,N;//최소이동경로,2차원 배열의 크기
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=TC;tc++){

            N = Integer.parseInt(br.readLine());
            map = new Node[N][N];

            for(int i=0;i<N;i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    int value = str.charAt(j)-'0';
                    Node tempNode = new Node(i, j, value);
                    map[i][j] = tempNode;
                }
            }
            //입력값 받기 끝
            pq = new PriorityQueue<>();
            minDis = Integer.MAX_VALUE;
            v = new boolean[N][N];
            map[0][0].myMinDis = 0;

            pq.add(map[0][0]);

            bfs();//굳이 모듈화 안해줘도 되지만 그냥

            sb.append("#"+tc+" "+minDis+"\n");


        }//테스트 케이스

        System.out.println(sb);

        br.close();
    }

    private static void bfs() {

        while(!pq.isEmpty()){
            Node tempNode = pq.poll();
            int row = tempNode.myRow;
            int col = tempNode.myCol;
            v[row][col] = true;
            for(int d=0;d<4;d++){
                int nr = row + dr[d];
                int nc = col + dc[d];
                //while문 다 돌 필요 없이 G에 도착하면 끝 bfs함수를 끝내준다.
                if (nr==N-1 && nc==N-1) {
                    //G에는 항상 0이기 때문에 아래 과정 할 필요 없이 그냥 무조건 끝내면 된다.
                    minDis = map[row][col].myMinDis;
                    return;
                }
                boolean bound = (nr>=0&&nr<N)&&(nc>=0&&nc<N);
                if(!bound || v[nr][nc]) continue;

                Node nextNode = map[nr][nc];
                if(nextNode.myMinDis>tempNode.myMinDis+nextNode.value)
                    nextNode.myMinDis = tempNode.myMinDis+nextNode.value;
                pq.add(nextNode);


            }//4방 탐색
        }//while
    }//bfs

}//class
