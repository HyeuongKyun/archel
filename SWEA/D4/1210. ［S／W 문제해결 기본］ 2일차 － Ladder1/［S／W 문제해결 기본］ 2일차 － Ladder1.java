import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int sPoint,ePoint; //시작점과 끝점의 x인덱스를 저장할 변수
    static int[] dr = {0,0,-1}; //3방 탐색 좌우상
    static int[] dc = {-1,1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=10;tc++){
            br.readLine();//테스트 케이스 들어오는거 안받아 줘도됌
            map = new int[100][100];

            for(int i=0;i<100;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<100;j++) {
                    int temp =  Integer.parseInt(st.nextToken());
                    map[i][j] = temp;
                    if(temp==2) ePoint = j;
                }
            }
            //입력값 받기 끝

            dfs(99,ePoint);

            sb.append("#"+tc+" "+ sPoint +"\n");
        }//테스트 케이스
        System.out.println(sb.toString());
        br.close();
    }//main

    private static void dfs(int row, int col) {
        if(row==0){
            sPoint = col;
        }//기저조건
        //방문기록을 남기지 않는 대신 지나온곳을 0으로 바꿔버린다.
        map[row][col] = 0;
        for(int d=0;d<3;d++){
            int nr = row + dr[d];
            int nc = col + dc[d];
            boolean bound = (nr>=0&&nr<100) && (nc>=0&&nc<100);
            if(!bound) continue;
            if(map[nr][nc]==1){
//                System.out.println(row);
                dfs(nr,nc);
                //break 하는 이유는 미로 찾기는 한 방향만 찾으면 되기 때문에 모든 경로를
                //탐색 해줄 필요 없다.
                break;
            }
        }
    }//dfs
}//class
