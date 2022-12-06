import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
    static int[] dr1 = {-1,0,1,0} , dc1 = {0,1,0,-1};
    static int[] dr2 = {-1,1} , dc2 = {0,0};
    static int[] dr3 = {0,0} , dc3 = {1,-1};
    static int[] dr4 = {-1,0} , dc4 = {0,1};
    static int[] dr5 = {0,1} , dc5 = {1,0};
    static int[] dr6 = {0,1} , dc6 = {-1,0};
    static int[] dr7 = {-1,0} , dc7 = {0,-1};

    static int rowN,colM;
    static int[][] map;
    static boolean[][] v;
    static int cnt;
    static Queue<Node> q;
    static class Node {
        public Node(int row, int col, int times) {
            this.row = row;
            this.col = col;
            this.times = times;
        }
        int row,col,times;

        public int getRow() {
            return row;
        }
        public void setRow(int row) {
            this.row = row;
        }
        public int getCol() {
            return col;
        }
        public void setCol(int col) {
            this.col = col;
        }
        public int getTimes() {
            return times;
        }
        public void setTimes(int times) {
            this.times = times;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            rowN = Integer.parseInt(st.nextToken());//행렬 크기의 행
            colM = Integer.parseInt(st.nextToken());//행렬 크기의 열
            int rowS = Integer.parseInt(st.nextToken());//도둑 첫 위치
            int colS = Integer.parseInt(st.nextToken());//도둑 첫 위치
            int times = Integer.parseInt(st.nextToken());//도둑이 도망갈 수 있는 시간

            map = new int[rowN][colM];
            for(int i=0;i<rowN;i++){
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<colM;j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            //입력값 받기 끝

            q = new LinkedList<>();
            q.add(new Node(rowS,colS,times));
            v = new boolean[rowN][colM];
            cnt =0;
            bfs(tc);//처음 bfs를 시작하는 값을 대입,그리고 시간
            sb.append("#" + tc + " " + cnt + "\n");
        }//테스트 케이슨
        System.out.println(sb);
        br.close();
    }//main
    static void bfs(int tc){
        while(!q.isEmpty()){
            Node tempNode = q.poll();

            int tempRow = tempNode.row;
            int tempCol = tempNode.col;
            int tempTimes = tempNode.times;
            if(tempTimes==0) return;//0인 애들도 다 담아줘야하니까 -1이 발견되면 return;
            if(v[tempRow][tempCol]) continue;//밑에서 q에 안넣게끔 처리를 하지만 여기 또 해야하는 이유는 같은 횟수에 동시에 같은 노드가 들어가는 경우를 밑에서 다 걸러 줄 수가 없어서
          //  System.out.printf("tempRow:%d tempCol:%d tempTimse:%d\n",tempRow,tempCol,tempTimes);
            cnt++;
            v[tempRow][tempCol]=true;
            int val = map[tempRow][tempCol];
            int[] dr,dc;
            if(val==1) {
                dr=dr1;
                dc=dc1;
            }else if(val==2){
                dr=dr2;
                dc=dc2;
            }else if(val==3){
                dr=dr3;
                dc=dc3;
            }else if(val==4){
                dr=dr4;
                dc=dc4;
            }else if(val==5){
                dr=dr5;
                dc=dc5;
            }else if(val==6){
                dr=dr6;
                dc=dc6;
            }else{
                dr = dr7;
                dc = dc7;
            }

            int dLen = dr.length;
            for(int d=0;d<dLen;d++){
                int nr = tempRow+dr[d];
                int nc = tempCol+dc[d];
                boolean bound = (nr>=0&&nr<rowN) && (nc>=0&&nc<colM);
                if(!bound||v[nr][nc]) continue;
                int nVal = map[nr][nc];
                Node nextNode = null;

                if(dr[d]==-1){//dc[d]가 0
                    if(nVal==1||nVal==2||nVal==5||nVal==6) nextNode = new Node(nr,nc,tempTimes-1);
                }else if(dr[d]==1){//dc[d]가 0
                    if(nVal==1||nVal==2||nVal==4||nVal==7) nextNode = new Node(nr,nc,tempTimes-1);
                }else{//dr[d]==0일 때
                    if(dc[d]==1){
                        if(nVal==1||nVal==3||nVal==6||nVal==7) nextNode = new Node(nr,nc,tempTimes-1);
                    }else{//dr[c]==-1일때
                        if(nVal==1||nVal==3||nVal==4||nVal==5) nextNode = new Node(nr,nc,tempTimes-1);
                    }
                }

                if(nextNode!=null){
                    q.add(nextNode);
                }

            }
        }
    }//bfs
}//class
