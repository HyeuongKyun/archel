//풀이 방법
//역 회전변환 행렬을 이용해서 각 세대에 있는 원소들을 리스트에 넣어서
//리스트의 길이를 변수로 먼저 빼둔다음 그 길이 만큼 반복을 진행
//중심이 원점이 아니기 때문에 각 연산을 할 때 각 커브 마다 중심이 되는 좌표값을
// 빼서 역회전 변환 행렬 연산후 중심만큼 다시 이동
//시간 복잡도는 낮아서 고민안해도됌(하려면 커브때 생기는 점의 갯수를 p라고 했을 때
// ( p*4+2*p ) * N = 6pN ,행렬곱과 중심을 뺐다 더하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    //시계방향 90도 회전 변환
    //{{0,1},{-1,0}}으로 해도 되는데 일반화 가능함을 보이기 위해 sin,cos으로 하였다.
    static int[][] inverseCir = {{(int) Math.cos(Math.PI/2),(int) Math.sin(Math.PI/2)},{(int) (Math.sin(Math.PI/2)*-1),(int) Math.cos(Math.PI/2)}};
    static int N; //드래곤 커브의 갯수
    static int[][] curvInfo;//N개의 드래곤 커브에 대한 정보
    static boolean[][] v;//드래곤 커브가 있는 곳이면 true로 변환해줄
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    //사각형의 갯수를 세기위한 8방탐색 12시부터 시계방향
    static int[] eightDirY = {-1,-1,0,1,1,1,0,-1};
    static int[] eightDirX = {0,1,1,1,0,-1,-1,-1};
    static int rectangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        curvInfo = new int[N][4];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++)
                curvInfo[i][j] = Integer.parseInt(st.nextToken());
        }//for j

        //입력값 받기 끝

        v = new boolean[101][101];
        for(int n=0;n<N;n++) {
            List<int[]> list = checkDragonCur(n);
        }

        //커브를 다 둘렀으니까 이제 가운데가 true인 값을 기준으로 8방탐색을 한다.
        //그리고 나서 만약 문제에서 원하는 사각형의 갯수를 세어주는데 중복이 발생하므로 /4를 해준다.

        rectangle = 0;
        countBox();

        System.out.println(rectangle/4);

        br.close();
    }//main

    private static void countBox() {
        for(int i=0;i<=100;i++){
            for(int j=0;j<=100;j++){
                if(v[i][j]){//가운데가 false이면 모든 꼭짓점이 커브로 둘러싸일 수 없으므로 중앙이 true 것만 8방 탐색을한다.
                    //이 배열을 기준으로 8방 중 몇 개의 사각형이 조건에 부합하는지 거른다.
                    boolean[] arr = new boolean[8];
                    for(int d=0;d<8;d++){
                        int nx = j+eightDirX[d];
                        int ny = i+eightDirY[d];
                        boolean bound = (nx>=0&&nx<=100) && (ny>=0&&ny<=100);
                        if(!bound) continue;
                        if(v[ny][nx]) arr[d] = true;

                    }//for d

                    //8방 탐색을 다 했으니까 네개의 꼭지점이 다 true인 사각형의 갯수를 세준다.
                    label : for(int iter=0;iter<4;iter++) {
                        for (int c = 0; c < 3; c++)
                            if (!arr[(c+iter*2)%8]) continue label;
                        rectangle++;
                    }//for label

                }
            }
        }

    }//countBox

    private static List<int[]> checkDragonCur(int idx) {
        int[] tempArr = curvInfo[idx];
        int x = tempArr[0];//0<=x<=100 //열
        int y = tempArr[1];//0<=y<=100 //행
        int dir = tempArr[2];//0<=dir<=3
        int gen = tempArr[3];//0<=gen<=10

        List<int[]> list = new ArrayList<>();
        v[y][x] = true;

        list.add(new int[]{x,y});//처음 시작점
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        list.add(new int[]{nx,ny});//0세대 넣기.
        v[ny][nx]=true;
        if(gen==0) return list;

        //0세대가 아니라면 이제부터 회전변환으로 회전 후 배열 증가시키기.
        int nowGen = 1;
        while(nowGen<=gen){

            int size = list.size();
            int[] endPoint = list.get(size-1);
            int endX = endPoint[0];
            int endY = endPoint[1];

            //끝점은 빼고 돌려야하기 떄문에 size가 아니라 size-1까지 한다.
            for(int i=size-2;i>=0;i--){
                int[] nowPoint =  list.get(i);
                int nowX = nowPoint[0];
                int nowY = nowPoint[1];
                int beforeX = nowX - endX;
                int beforeY = (-1)*(nowY - endY);//y축이 뒤집어 져있기 때문에 회전변환을 하기 위해서는 위아래를 바꿔줘야한다.
                //원래는 2차원 행렬을 곱하고 중심 만큼을 다시 더하는 과정이 필요하나 90도가 특수각인 관계로 아래와 같이 가능합니다.
                int afterX = beforeY + endX;
                int afterY = beforeX + endY;//=(-1*beforeX)*(-1) + endY;
                v[afterY][afterX]=true;
                list.add(new int[]{afterX,afterY});
            }//for i

            nowGen++;
        }//while

        return list;
    }//checkDragonCur
}//class
