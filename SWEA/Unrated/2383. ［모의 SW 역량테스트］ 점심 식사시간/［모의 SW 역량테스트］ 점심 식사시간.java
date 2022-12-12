import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static List<int[]> list;//처음에 사람 위치를 넣을 변수
    static int N,len,minTime,totalTime; //지도의 사이즈,list의 길이,최소시간을 담을 변수,반복을 돌 시간
    static int[] exit1,exit2;//계단은 항상 2개라고 주었기 때문에 미리 변수로 선언
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st;
            list = new ArrayList<>();
            exit1 = null; exit2 = null;
            N = Integer.parseInt(br.readLine());

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp==0) continue;
                    else if(temp==1){
                        list.add(new int[]{i,j});
                    }
                    else {
                        //출구만 length가 3인 배열
                        if(exit1==null) exit1 = new int[]{i,j,temp};
                        else exit2 = new int[]{i,j,temp};
                    }

                }//for j
            }//for i

            len = list.size();
            //입력값 받기 끝

            //사람의 수가 10이하 이니까 두 출구 중 어느 출구로 나갈지
            //선택할 수 있는 모든 경우의 수는 2^10==1024
            //50개의 테스트 케이스니까 50*1000=5만 3초안에 가능
            boolean[] v = new boolean[len];
            minTime = Integer.MAX_VALUE;

            fillArr(0,v);
            //이걸 해주는 이유는 둘 다 비었을 때도 한번 도니까 그걸 제외해줘야 해서
            minTime--;
            sb.append("#"+tc+" "+minTime+"\n");
        }//테스트 케이스
        System.out.println(sb);
        br.close();
    }//main

    private static void fillArr(int idx , boolean[] v){
        if(idx==len){
            List<Integer> listForExit1 = new ArrayList<>();
            List<Integer> listForExit2 = new ArrayList<>();
            for(int i=0;i<len;i++){
                int[] tempArr = list.get(i);
                int tempPR = tempArr[0];
                int tempPC = tempArr[1];
                if(v[i]) {
                    int distance = Math.abs(exit1[0]-tempPR) + Math.abs(exit1[1]-tempPC);
                    listForExit1.add(distance);
                }
                else{
                    int distance = Math.abs(exit2[0]-tempPR) + Math.abs(exit2[1]-tempPC);
                    listForExit2.add(distance);
                }//if else
            }//for i
            //listForExit들 다 채웠으니까 이제 계단 내려보내는게 어떤게 젤 빠른지 비교해본다.
            findMin(listForExit1,listForExit2);
            return;
        }//if 기저조건

        v[idx]=true;
        fillArr(idx+1,v);
        v[idx]=false;
        fillArr(idx+1,v);

    }//fillArr

    private static void findMin(List<Integer> listForExit1, List<Integer> listForExit2) {
        listForExit1.sort(null);
        listForExit2.sort(null);
        totalTime=0;
        int cnt1=0;
        int cnt2=0;
        while(true){
            totalTime++;
            boolean b1 = listForExit1.isEmpty();
            boolean b2 = listForExit2.isEmpty();
            if(!b1){
                cnt1=0;
                //우선 전체 값 1식 다 빼주기
                for(int i=0;i<listForExit1.size();i++){
                    int temp = listForExit1.get(i)-1;
                    listForExit1.remove(i);
                    listForExit1.add(i,temp);
                    if (temp<=-1) cnt1++;
                }
                //계단 다내려간 사람은 list에서 빼고 cnt도 빼주기
                while(listForExit1.get(0)==(-1)*exit1[2]-1){
                    listForExit1.remove(0);
                    cnt1--;
                    if(listForExit1.isEmpty()) break;
                }
                
                //계단을 내려가는 사람이 3명 이상으면 늦게 온 사람은 대기 시키는 반복문

                while(cnt1>3){
                    int temp = listForExit1.get(cnt1-1)+1;
                    listForExit1.remove(cnt1-1);
                    listForExit1.add(cnt1-1,temp);
                    cnt1--;
                }
            }
            if(!b2){
                //우선 전체 값 1식 다 빼주기
                cnt2=0;
                for(int i=0;i<listForExit2.size();i++){
                    int temp = listForExit2.get(i)-1;
                    listForExit2.remove(i);
                    listForExit2.add(i,temp);
                    if (temp<=-1) cnt2++;
                }
                //계단 다내려간 사람은 list에서 빼고 cnt도 빼주기
                while(listForExit2.get(0)<=(-1)*exit2[2]-1){
                    listForExit2.remove(0);
                    cnt2--;
                    if(listForExit2.isEmpty()) break;
                }

                //계단을 내려가는 사람이 3명 이상으면 늦게 온 사람은 대기 시키는 반복문

                while(cnt2>3){
                    int temp = listForExit2.get(cnt2-1)+1;
                    listForExit2.remove(cnt2-1);
                    listForExit2.add(cnt2-1,temp);
                    cnt2--;
                }
            }
            if(b1&&b2) break;
        }
        if(totalTime<minTime) minTime=totalTime;
    }//findMin
}//class
