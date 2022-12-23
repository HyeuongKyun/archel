import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//N극 0, S극 1 , 12시부 시계방향으로 정보 제공
public class Main {
    static char[][] gears;
    static int K; //회전 횟수
    static int[][] kInfo;//회전 정보
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new char[4+1][8];//0인덱스 버림,
        for(int i=1;i<=4;i++)
            gears[i] = br.readLine().toCharArray();
        K = Integer.parseInt(br.readLine());
        kInfo = new int[K][2];
        for(int k=0;k<K;k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            kInfo[k][0] = Integer.parseInt(st.nextToken());
            kInfo[k][1] = Integer.parseInt(st.nextToken());
        }//for k

        //입력값 받기 끝

        //k번 만큼 회전 시키기
        for(int k=0;k<K;k++){
            spin(k);
        }

        int answer =0;
        for(int i=1;i<=4;i++){
            if(gears[i][0]=='1')
                answer += (int) (Math.pow(2,i-1));
        }

        System.out.println(answer);

        br.close();
    }//main

    private static void spin(int k) {
        int id = kInfo[k][0];//몇번 바퀴를 돌릴건지
        int dir = kInfo[k][1];//어디 방향으로 돌리는지
        int[] idDir = new int[4+1];
        idDir[id] = dir;
        Queue<int[]> q = new LinkedList<>();//현재 바퀴가 돌면 돌아야할 바퀴들을 넣어주기 위한 q
        q.add(new int[]{id,dir});
        fillQ(q,id,idDir);
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int nowId = temp[0];
            if(temp[1]==-1){//반시계 방향 회전
                char gearFirVal = gears[nowId][0];
                for(int i=0;i<7;i++)
                    gears[nowId][i] = gears[nowId][i+1];
                gears[nowId][7] = gearFirVal;
            }else if(temp[1]==1){//시계 방향 회전
                char gearLastVal = gears[nowId][7];
                for(int i=6;i>=0;i--)
                    gears[nowId][i+1] = gears[nowId][i];
                gears[nowId][0] = gearLastVal;
            }//if,else if
        }//while
    }//spin

    private static void fillQ(Queue<int[]> q,int id , int[] idDir) {
        for(int i=1;i<4;i++){
            if(id+i<=4){
                if(gears[id+i][6] != gears[id+i-1][2] && idDir[id+i-1]!=0){
                    if(idDir[id+i-1]==1) {
                        idDir[id+i]=-1;
                    }
                    else if(idDir[id+i-1]==-1) {
                        idDir[id+i]=1;
                    }
                    q.add(new int[]{id+i,idDir[id+i]});
                }//if
            }//if
            if(id-i>=1){
                if(gears[id-i][2] != gears[id-i+1][6] && idDir[id-i+1]!=0){
                    if(idDir[id-i+1]==1) {
                        idDir[id-i]=-1;
                    }
                    else if(idDir[id-i+1]==-1) {
                        idDir[id-i]=1;
                    }
                    q.add(new int[]{id-i,idDir[id-i]});
                }//if
            }//if
        }//for i
    }//fillQ
}//class
