import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][][] allHoneyArr;//채취가능한 벌꿀의 경우를 넣어줄 3차원 배열
    static boolean[][] nextMaxMap;//최초 가장 큰 값을 구하는 채취 경우를 제외하고 나머지 중에서 가장 큰 값을 찾기 위한 방문 기록
    static int[][] map;//벌꿀의 정보를 받을 변수
    static int N,M,C;//벌통 사이즈 , 벌통의 갯수, 벌꿀 채취 최대 양
    static int maxVal,maxIdxi,maxIdxj;//최대값과 최소값을 기록할 변수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int TC = Integer.parseInt(br.readLine());
            for(int tc=1;tc<=TC;tc++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                map = new int[N][N];

                for(int i=0;i<N;i++){
                    st = new StringTokenizer(br.readLine());
                    for(int j=0;j<N;j++)
                          map[i][j] = Integer.parseInt(st.nextToken());
                }//for
                
                //입력값 받기 끝

                //모든 채취 가능한 벌꿀의 경우가 들어갈 변수
                allHoneyArr = new int[N][N-M+1][M];
                nextMaxMap = new boolean[N][N-M+1];
                //allHoneyArr를 채워줄 변수
                fillHoneyArr();

                int answer = 0;
                maxVal = 0;
                maxIdxi = -1;
                maxIdxj = -1;
                for(int i=0;i<N;i++){
                    for(int j=0;j<N-M+1;j++){
                        int[] tempArr = allHoneyArr[i][j];
                        boolean[] v = new boolean[M];
                        findMax(tempArr,0,v,i,j);
                    }//for
                }//for
                answer += maxVal;
                for(int m=1;m<M;m++){
                    if(maxIdxj-m==-1) break;
                    nextMaxMap[maxIdxi][maxIdxj-m]=true;
                }
                for(int m=0;m<M;m++) {
                    if (maxIdxj + m == N-M+1) break;
                    nextMaxMap[maxIdxi][maxIdxj + m] = true;
                }
                maxVal = 0;
                for(int i=0;i<N;i++){
                    for(int j=0;j<N-M+1;j++){
                        if(nextMaxMap[i][j]) continue;
                        int[] tempArr = allHoneyArr[i][j];
                        boolean[] v = new boolean[M];
                        findMax(tempArr,0,v,i,j);
                    }//for
                }//for
                answer += maxVal;
                System.out.printf("#%d %d\n",tc,answer);
            }//테스트 케이스
        br.close();
    }//main



    private static void findMax(int[] tempArr,int idx,boolean[] v,int i,int j) {
        if(idx==M){
            int powHap=0;
            int nomalHap=0;
            for(int m=0;m<M;m++){
                if(v[m]){
                    int tempVal = tempArr[m];
                    nomalHap += tempVal;
                    powHap += (int) Math.pow(tempVal,2);
                    if(nomalHap>C) return;//C보다 크면 더 계산할 가치가 없다. 그냥 바로 return
                }//if
            }//for m
            if(powHap>maxVal) {
                maxVal = powHap;
                maxIdxi=i;
                maxIdxj=j;
            }
            return;

        }//기저 조건

        v[idx]=true;
        findMax(tempArr,idx+1,v,i,j);
        v[idx]=false;
        findMax(tempArr,idx+1,v,i,j);

    }//findMax

    private static void fillHoneyArr() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N-M+1;j++){
                int[] tempArr = new int[M];
                for(int k=j;k<j+M;k++)
                    tempArr[k-j] = map[i][k];
                allHoneyArr[i][j] = tempArr;
            }//for j
        }//for i
    }//fillHoneyArr
}//class
