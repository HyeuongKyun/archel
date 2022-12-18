//풀이 방법
//N이 최대 15이니까 2^10=1024 , 2^5 = 32 => 2^15 => 약 30,000
//모든 경우를 탐색 해보자
//탐색 방법은 모든 경우를 0과 1로 표현해서 날짜 조건이 지켜지면서 더한 것 중 가장 큰 값만을 기록한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,maxMoney;//퇴사까지 남은 날,퇴사날 받을 최대 금액
    static int[][] arr;//남은 일정(0인덱스 버림)
    static boolean[] task;//일을 받았는지 기록할 변수 배열(0인덱스 버림)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1][2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        } //for i

        //입력 값 받기 끝
        maxMoney=0;
        task = new boolean[N+1];
        calMax(1);
        System.out.println(maxMoney);
        br.close();
    }//main
    private static void calMax(int idx){
        if(idx==N+1){
            int money=0;
            for(int i=1;i<=N;i++){
                if(task[i]){

                    int days = arr[i][0];
                    //일이 끝나는 일이 퇴사날을 지난 경우
                    if(i+(days-1)>N) return;
                    //일을하는데 또 다른 일이 생길 경우
                    for(int j=1;j<days;j++)
                        if(task[i+j]) return;

                    money+=arr[i][1];
                }//if
            }//for i
            if(maxMoney<money) maxMoney=money;
            return;
        }//기저조건

        task[idx]=true;
        calMax(idx+1);
        task[idx]=false;
        calMax(idx+1);
    }
}//class
