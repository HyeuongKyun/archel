import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    //2번,6번 인덱스의 톱니 자성이 같은지 같지 않을지를 중점으로 봐야한다.
    //그렇게 끝나고 나서는 0번 인덱스의 자성을 가지고 값을 환산한다.
    static int[][] wheel,rotaInfo;//4개위 바퀴에 대한 정보, 어떻게 돌릴껀지데 대한 정보
    static boolean[] v;//로테이션을 돌 때 bfs를 돌기 위해서 방문한 곳인지 기록을 남기기 위한 배열
    static Queue<int[]> q;//bfs를 돌리기 위한 queue
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            int K = Integer.parseInt(br.readLine());//회전시키는 횟수
            wheel =  new int[4][8];

            for(int i=0;i<4;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<8;j++)
                    wheel[i][j] = Integer.parseInt(st.nextToken());
            }
            rotaInfo = new int[K][2];//K번 rotation에 대한 정보 기입

            for(int i=0;i<K;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<2;j++)
                    rotaInfo[i][j] = Integer.parseInt(st.nextToken());
            }

            //입력값 대입 끝

            //k번 만큼의 회전 메서드 실행
            //bfs로 접근 만약 접해있는 자성이 다르면 q에 추가해서 또 같은 작업 반복
            for(int k=0;k<K;k++){
                //로테이션을 돌 때 마다 최신화 해주어야하는 변수들
                v = new boolean[4];
                q = new LinkedList<>();
                rotation(k);
            }


            //회전 끝
            // 점수 계산
            int answer = 0;
            for(int i=0;i<4;i++){
                if(wheel[i][0]==1){
                    answer += (int)Math.pow(2,i);
                }
            }
            System.out.printf("#%d %d\n",tc,answer);
        }//테스트 케이스

        br.close();
    }//main

    private static void rotation(int k) {
        q.add(rotaInfo[k]);
        while(!q.isEmpty()){
            int[] tempInfo = q.poll();
            int target = tempInfo[0]-1;
            int direction = tempInfo[1];

            v[target] = true;
            if (target == 0) {
                if(!v[target+1] && (wheel[target][2]!=wheel[target+1][6])) {
                    int[] nextRotaInfo = {(target+1)+1,(-1)*direction};
                    q.add(nextRotaInfo);
                }
            } else if (target == 3) {
                if(!v[target-1] && (wheel[target][6]!=wheel[target-1][2])) {
                    int[] nextRotaInfo = {(target-1)+1,(-1)*direction};
                    q.add(nextRotaInfo);
                }
            } else {//target==1,2일 때
                if(!v[target+1] && (wheel[target][2]!=wheel[target+1][6])) {
                    int[] nextRotaInfo = {(target+1)+1,(-1)*direction};
                    q.add(nextRotaInfo);
                }
                if(!v[target-1] && (wheel[target][6]!=wheel[target-1][2])) {
                    int[] nextRotaInfo = {(target-1)+1,(-1)*direction};
                    q.add(nextRotaInfo);
                }
            }//if elseif else끝
            doRota(target,direction);
        }
        

    }//rotation
    //톱니의 위치를 바꿔주는 함수
    private static void doRota(int target,int direction){
        if(direction==1){
            int lastVal = wheel[target][7];
            for(int i=7;i>0;i--)
                wheel[target][i]=wheel[target][i-1];
            wheel[target][0] = lastVal;
        }else{//방향이 시계 반대 방향으로 돌 때
            int firstVal = wheel[target][0];
            for(int i=1;i<8;i++)
                wheel[target][i-1]=wheel[target][i];
            wheel[target][7] = firstVal;
        }
    }//doRota
}//class
