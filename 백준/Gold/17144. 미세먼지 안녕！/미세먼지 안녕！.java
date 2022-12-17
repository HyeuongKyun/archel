import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//풀이 방법
//확산이 우선 발생 , 확산 발생 후 공기 청정기 바람이 방향에 맞게 쭉 밀어버림
// 밀어버리고 만약에 공기 청정기로 들어오는 바람이 있으면 그 미세먼지는 없어짐
//1.미세먼지가 5미만은 곳은 먼지가 확산이 되지 않는다.
//2. List를 이용해서 bfs로 먼지 확산을 해주고 공기 청정기의 바람이 닿는 곳에
//   값이 존재한다면 방향에 맞게 밀어준다.
//   그렇지 않으면 전 R*C를 완전 탐색해야하는데 50*50*1000=2500*1000 => 2,500,000 => *=4 10,000,000 => 해볼만하네?
public class Main {
    static int R, C, T;//행,열,시간
    static int[][] map;

    static int[] dr={-1,0,1,0} , dc={0,1,0,-1}; //12시부터 시계 방향

    static List<Integer> list; //공기청정기 위치를 저장하고 있을 리스트(어차피 0열이기 때문에 int로 저장)

    static int cleanVal; //공기청정기가 청소한 val
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        //미리 합을 구해뒀다가 공기 청정기에 들어가는 값만을 따로 구해 더해준다.
        int originSum = 0;
        map = new int[R][C];
        list = new ArrayList<>();

        for(int i=0; i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C;j++){
                int val = map [i][j] = Integer.parseInt(st.nextToken());
                if (val>0) originSum += val;
                else if(val==-1) list.add(i);
            }//for j
        }//for i

        //입력값 끝

        cleanVal = 0;
        //t시간 만큼 같은 활동 반복
        for(int t=1;t<=T;t++){
            //우선 확산을 시켜준다.
            spread();

            //공기 청소기 방향에 맞게 밀어준다.
            cleaning();

        }

        System.out.println(originSum - cleanVal);
        
        br.close();
    }//main
    private static void cleaning(){
        //먼저들어간 인덱스가 반시계 방향 회전, 나중이 시계방향 회전이다.
        //그렇기 때문에 위에꺼 하나만 꺼내서 계산해도 된다.
        int upRow = list.get(0);
        //먼저 공기 청정기에 들어오는 값부터 처리해준다.
        cleanVal += (map[upRow-1][0] + map[upRow+2][0]);

        //0열 한칸씩 자리이동
        for(int i=upRow-1;i>0;i--)
            map[i][0] = map[i-1][0];
        for(int i=upRow+2;i<R-1;i++)
            map[i][0] = map[i+1][0];

        //0행 , R-1행 -1만큼 이동 시키기
        for(int j=0;j<C-1;j++){
            map[0][j] = map[0][j+1];
            map[R-1][j] = map[R-1][j+1];
        }

        //C-1행 한칸씩 자리이동
        for(int i=0;i<upRow;i++)
            map[i][C-1] = map[i+1][C-1];
        for(int i=R-1;i>upRow+1;i--)
            map[i][C-1] = map[i-1][C-1];

        //upRow와 upRow+1행 +1만큼 이동 시키기
        for(int j=C-1;j>1;j--){
            map[upRow][j] = map[upRow][j-1];
            map[upRow+1][j] = map[upRow+1][j-1];
        }
        map[upRow][1] = 0;
        map[upRow+1][1] = 0;

    }//cleaning

    private static void spread(){
        //먼지의 확산이 동시에 발생하기 때문에 이동할 값들을 저장해두고 있다가 한번에 적용 시켜주기 위한 배열
        int[][] mapShadow = new int[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int val = map[i][j];
                //값이 5미만인 곳은 확산이 일어나지 않는다.
                if(val<5) continue;
                int spreadVal = val/5;
                for(int d=0;d<4;d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    boolean bound = (nr>=0&&nr<R) && (nc>=0&&nc<C);
                    //경계를 벗어나거나 공기청정기 옆에는 확산이 일어나지 않는다.
                    if(!bound || map[nr][nc] == -1) continue;

                    //주변으로 확산되어 더하는 값은 나중에 한번에 해주어야하기 때문에 mapShadow에 저장
                    mapShadow[nr][nc] += spreadVal;

                    //값이 빠지는건 map에서 빠지게 해준다. (확산한 만큼 값이 빠진다.)
                    map[i][j] -= spreadVal;

                }//for d
            }//for j
        }//for i

        //이렇게 다 더하고 나면 연산하고 나면 mapShadow에 있는 값들을 map에다가 더해준다.
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int tempVal = mapShadow[i][j];
                if(tempVal<=0) continue;
                map[i][j] += tempVal;
            }//for j
        }//for i
    }//spread

}//class
