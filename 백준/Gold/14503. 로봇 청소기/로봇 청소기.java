import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //북 동 남 서
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static int N,M;//세로 가로 크기
    static int robRow,robCol,robDir;//로봇의 위치를 나타내는 변수 , 로봇이 바라보는 방향 변수
    static int cnt; // 로봇이 청소한 바닥의 갯수
    static int[][] map;
    static boolean[][] v;//청소를 했음을 기록할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        robRow = Integer.parseInt(st.nextToken());
        robCol = Integer.parseInt(st.nextToken());
        robDir = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }//for j

        //입력값 받기 끝

        cnt = 0;
        v= new boolean[N][M];
        cleaning();

        System.out.println(cnt);

        br.close();
    }//main

    private static void cleaning() {
        //1.현재 위치를 청소한다.(만약 청소가 되어져 있다면 하지 않음)
        label : while(true){
            v[robRow][robCol] = true;
            cnt++;

            //2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행한다.
            while(true){
                //2-1.왼쪽 방향에 아직 청소하지 않은 공간...
                //4방향이 청소가 가능한지 가능한 곳이 있다면 중간에서 continue 하고 그쪽으로 방향 전환
                for(int d=1;d<=4;d++){
                    int nr = robRow + dr[(robDir-d+4)%4];
                    int nc = robCol + dc[(robDir-d+4)%4];
                    boolean bound = (nr>=0&&nr<N) && (nc>=0&&nc<M);
                    if(!bound||map[nr][nc]==1) continue;//벽이면 못가는 곳으로 생각
                    //벽이나 경계가 아닌데 청소할 곳이 있으면 그곳으로 청소를 하러 가야한다.
                    if(!v[nr][nc]){
                        robDir=(robDir-d+4)%4;//방향 전환
                        robRow = nr;//로봇좌표
                        robCol = nc;//변환
                        continue label;
                    }

                }//for d

                //여기까지 왔다는 뜻은 사방이 벽 or 이미 청소한 구역이라는 의미이다.
                //그럼 이제 뒤로 갈 수 있으면 2번을 한칸뒤로 가서 실행하고 뒤로 갈 수 없다면 기계 작동을 중지시킨다.

                //한칸 뒤로 물리기
                int nr = robRow + dr[(robDir+2)%4];
                int nc = robCol + dc[(robDir+2)%4];
                boolean bound = (nr>=0&&nr<N) && (nc>=0&&nc<M);
                //만약 벽이거나 못가면 동작 중지
                if(!bound||map[nr][nc]==1) return;

                //뒤로 갈 수 있으면 뒤로 간 뒤에 다시 2번으로 실행
                robRow = nr;
                robCol = nc;

            }//while
        }//while label
    }//cleaning
}//class
