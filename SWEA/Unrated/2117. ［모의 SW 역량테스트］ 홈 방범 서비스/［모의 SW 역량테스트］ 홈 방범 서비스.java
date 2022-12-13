//풀이 방법 : 그리디하게 접근이 불가하므로 완전 탐색으로 돌린다.
//모든 격자점에서 길이 1애서 N까지 만큼의 방범 서비스를 설치해보고 그 중
//손해를 보지 않으면서 가장 많이 설치할 수 있는 경우를 찾는다
//N이 최대 20이기에 모든 격자점은 20*20 , 2*N만큼의 방범 서비스를 확인할 것이기에
//20*20*20*2 , 거기에 테스트 케이스 50개 이므로 20*20*20*2*50 이므로 가능
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N,M,maxCnt;//지도의 사이즈, 한 집에 내는 비용,가장 많이 설치할 수 있는 경우 갯수

    static int[][] map;//지도
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
            
            //입력값 받기 끝


            maxCnt = 0;

            //각각의 장소마다 K=2*N까지 검사를 해봐야한다. 
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    checkVal(i,j);
                }
            }

            sb.append("#"+tc+" "+maxCnt+"\n");
        }//테스트 케이스
        System.out.println(sb);
        br.close();
    }//main

    //방범 사이즈를 1부터 2*N사이즈 까지 확인해본다
    private static void checkVal(int row, int col) {
        //2*N까지 해봐야 모든 경우에서 전체를 덮는 경우를 체크할 수 있다.
        for(int k=1;k<2*N;k++){
            //k는 서비스 영역 크기
            int kVal = k*k + (k-1)*(k-1);
            int profit = 0;
            for(int i=-k+1;i<k;i++){
                for(int j=-k+1;j<k;j++){
                    int nr = row + i;
                    int nc = col + j;
                    boolean bound = (nr>=0&&nr<N)&&(nc>=0&&nc<N);
                    if(!bound) continue;
                    int dis = Math.abs(i)+Math.abs(j);
                    //마름모에 들어오는 값인지 체크 마름모 안에 안들어 오는 값이면 다시 continue;
                    if(dis>k-1) continue;
                    if(map[nr][nc]==0) continue;
                    //여기까지 왔으면 집이 있다는 뜻
                    profit += M;
                }//for j
            }//for i
            //여기까지 돌았으면 그 현재 k에 해당하는 영역만큼 설치 했을 때 이익이 산출됌
            if(profit>=kVal){
                int cnt = profit/M;
                if(cnt>maxCnt) {
                    maxCnt = cnt;
                }
            }//if
        }//for k
    }//checkVal
}//class
