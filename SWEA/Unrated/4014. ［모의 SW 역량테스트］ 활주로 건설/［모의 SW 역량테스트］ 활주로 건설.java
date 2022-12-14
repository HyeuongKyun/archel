//풀이 방법
//1. 우선 높이 차이가 전블럭이 비해 있는지 확인
//2. 높이 차이가 있는 곳에 경사로를 설치할 수 있는지 확인
//3. 높이 차이가 있는데 경사로를 설치 못한다면 break; 더 볼 필요가 없다.
// **경사로를 설치하는 방법과 규칙은 다음과 같다.
// - 각각의 모든 블럭에서 1방을 2번(상하,좌우) 탐색을 한다
// (행렬을 앞뒤로 검사할 필요 없는 이유는 높이가 높아질때와 낮아질 때를 동시에 다룰 것이기 때문에 양방향으로 검사할 필요가 없다.)
// - 탐색은 다음 블럭이 높이가 높아질때와 낮아질 때 두 가지 경우로 나누어서 진행한다.
// - 다음 블럭이 높아질 때는 자신을 포함해 뒤에 자신과 높이가 같은 블럭이 x개가 있는지 확인하고 , 낮아질 때는 자신을 제외한 다음 블럭부터 x개의 블럭의 높이가 같은지 확인한다.
// (확인만 하면 블럭이 설치가 가능하다는 의미이므로 다른 조치는 하지 않는다. 하면 시간 복잡도만 증가한다.)
// - 그리고 추가 사항으로 굳이 안해도 되지만 최적화를 하려면 내려가는 상황에서 통과가 됐다면 다음 블럭 x-1 만큼은 탐색을 안해주어도 된다.
// (v를 써서 할꺼면 2방 탐색의 방향을 바꿀 때 초기화 해줘야한다 .)(올라가는 상황에서는 이미 탐색을 하고 온 상태라 좀 더 방법을 쓰지 않으면 힘들다.)
// 그렇게 행or열 탐색이 끝났으면 열or행 탐색을 한번 더 해준다.

//시간 복잡도 : 모든 node를 돌아다니므로 이중 for 문 20*20 , 탐색을 최대한 한다고 예측 했을 때의 비용은 4(한 블럭 기준 이므로)
//따라서 모든 블럭에서 확인하는데 드는 비용 20*20*4*2*50=160,000 (2가 붙는 이유는  2번 탐색할때 모든 블럭을 2번 탐색해서 ,50은 테케)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N,X,totalCnt;//지도의 사이즈, 경사로 길이,정답
    static int[][] map;//지도
    //열탐색, 행탐색 함수를 따로따로 만들지 않기 위해서 사용할 변수
    static int[] dr =  {0,1};
    static int[] dc =  {1,0};
    //겹치게 경사로가 놓아지는걸 방지하기 위해서
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
            
            //입력값 받기 끝

            totalCnt = 0;
            v = new boolean[N][N];
            for(int n=0;n<N;n++) {
                //열탐색
                runway(n, 0);//0은 runway함수에 열탐색라는 정보를 넘겨준 것
            }
            v = new boolean[N][N];
            for(int n=0;n<N;n++){
                //행탐색
                runway(n,1);//1는 runway함수에 행탐색라는 정보를 넘겨준 것
            }

            sb.append("#" + tc + " " + totalCnt + "\n");
        }//테스트 케이스
        System.out.println(sb);
        br.close();
    }//main

    private static void runway(int idx,int direction) {
        boolean goodRunWay=true;
        line : for(int ij=0;ij<N-1;ij++){
            int row,col;
            if (direction==0){//열탐색
                row = idx;
                col = ij;
            } else {//행탐색
                row = ij;
                col = idx;
            }

            int nowLevel = map[row][col];
            int nr = row + dr[direction];
            int nc = col + dc[direction];
            int nextLevel = map[nr][nc];

            if(nowLevel==nextLevel) continue;
            if(nowLevel+1==nextLevel){// 다음 블럭의 높이가 높을 때
                for(int x=1;x<X;x++){//본인을 포함하여 전의 X개가 높이가 같은지 확인해야한다.
                    int checkRow = row - x * dr[direction];
                    int checkCol = col - x * dc[direction];
                    //bound는 뺴기만 하니까 음수만 신경써주면 된다.
                    boolean bound = checkRow>=0 && checkCol>=0;
                    if(!bound){
                        goodRunWay=false;
                        break line;
                    }
                    int chechLevel = map[checkRow][checkCol];
                    if(chechLevel!=nowLevel || v[checkRow][checkCol]) {
                        goodRunWay=false;
                        break line;
                    }
                    v[checkRow][checkCol] = true;
                }
            }else if(nowLevel-1==nextLevel){//다음 블럭의 높이가 낮을 때
                for(int x=1;x<=X;x++){//자신을 제외한 다음 블럭부터 X개가 높이가 같은지 확인한다.
                    int checkRow = row + x * dr[direction];
                    int checkCol = col + x * dc[direction];
                    //다음 블럭들을 확인하는 것이기 때문에 N안에 들어가는지만 확인하면 된다.
                    boolean bound = checkRow<N && checkCol<N;
                    if(!bound){
                        goodRunWay=false;
                        break line;
                    }
                    int chechLevel = map[checkRow][checkCol];
                    if(chechLevel!=nowLevel-1 || v[checkRow][checkCol]) {
                        goodRunWay=false;
                        break line;
                    }
                    v[checkRow][checkCol] = true;
                }
            }else {//차이가 1이상인 곳들은 어차피 불가능한 곳이라서.
                goodRunWay=false;
                break;
            }//if,elseif,else
        }//for ij
        if (goodRunWay) {
            totalCnt++;
        }

    }//runway`
}//class
