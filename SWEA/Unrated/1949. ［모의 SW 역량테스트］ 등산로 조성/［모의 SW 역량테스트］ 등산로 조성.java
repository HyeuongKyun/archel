//풀이 방법 : 완전 탐색
//N이 최대 8 이고 최대 높이인 봉우리가 5대 이하 , 공사 가능 깊이 5이하
//이기 때문에 모든 경우를 탐색하는 경우의 수는 다음과 같습니다.
// (8*8) * 5 * 5 = 1600
// 그런데 여기서 테스트케이스가 51개 이므로 대락 1600*50 = 80000이기 때문에 완전 탐색이 가능합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N,K,maxVal,maxLen; //지도 한변의 길이, 공사 가능 깊이,지도에서 가장 큰 수,등산로 길이 중 가장 긴 길이를 저장해줄 변수
    static int[][] map; //지도에 대한 정보
    static int[] dr = {-1,0,1,0};
    static int[] dc= {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            maxVal = Integer.MIN_VALUE;
            map = new int[N][N];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    int temp = map[i][j] = Integer.parseInt(st.nextToken());
                    if(temp > maxVal) maxVal = temp;
                }
            }

            maxLen = Integer.MIN_VALUE;

            //입력값 받기 끝
            List<int[]> list = new ArrayList<>();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]==maxVal)
                        list.add(new int[]{i,j});
                }//for
            }//for
            //한꺼번에 묶어줘도 되지만 for문이 깊어져서 계산을
            //많이 하는 것처럼 가독성을 해쳐서 따로 분리

            for(int l=0;l<list.size();l++){//5번 이하
                int[] tempArr = list.get(l);
                int row = tempArr[0];
                int col = tempArr[1];
                for(int i=0;i<N;i++){//8번 이하
                    for(int j=0;j<N;j++){//8번 이하
                        for(int k=1;k<=K;k++){//5번 이하
                            int minusCheck = map[i][j] -= k;
                            //0보다 작아 졌는데 더 줄이면서 검사할 필요 없으니까 break 해준다.
                            if(minusCheck<0) {
                                map[i][j] += k;
                                break;
                            }
                            dfs(row,col,1);//현재 등산로의 길이를 표현해줄 변수
                            map[i][j] += k;
                        }//for
                    }//for
                }//for
            }//for
            System.out.printf("#%d %d\n",tc,maxLen);
        }//테스트 케이스

        br.close();
    }//main

    private static void dfs(int row, int col,int len) {
        if(len>maxLen) maxLen = len;

        for(int d=0;d<4;d++){
            int nr = dr[d] + row;
            int nc = dc[d] + col;
            boolean bound = (nr>=0&&nr<N) && (nc>=0&&nc<N);
            if(!bound) continue;

            int nowVal = map[row][col];
            int nextVal = map[nr][nc];
            //다음 val값이 더 커야만 dfs를 할꺼기 때문에 굳이 v를 안써도 방문한 곳을 또 방문할 일은 없다.
            if(nowVal>nextVal){
                dfs(nr,nc,len+1);
            }

        }
    }//dfs

}//class
