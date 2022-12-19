//풀이 방법
//벽을 세울 수 있는 모든 경우를 따져보자
//8*8=> 64*63*62 =>2^6*2^6*2^6=2^18 = 1024*256=250,000 
//2가 있는 곳에 dfs를 사용하면 2가 10일 때지만 4방탐색을
//이미 방문한 곳은 안하면 되기 때문에 그냥 8*8=64
//그리고 그 경우에 false인 곳을 찾아야하기 때문에 64
//=> 250,000*64*64=250,00*2^12!=250,000*4,000=1,000,000,000
//가능

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,maxArea;//맵의 크기 , 넓이의 최대값
    static int[][] map;//지도
    static boolean[][] virus;//바이러스 감염진행도
    static List<int[]> list;//바이러스가 있는 인덱스를 저장
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int temp =map[i][j] = Integer.parseInt(st.nextToken());
                if(temp==2) list.add(new int[]{i,j});
            }//for j
        }//for i

        //입력값 받기 끝

        //벽을 세웠다가 다시 지워줘야함 벽 세울 곳은
        //2차원 배열을 1차원 배열로 생각하고 N*M에서 순서 상관없이 3개를 뽑기로 진행
        //근데 만약 그곳에 1or2가 있다면 벽을 세우지 않고 다음으로 진행
        //벽을 세우면 나중에 다시 내려줘야 다음에 벽을 세울 때 지장이 가지 않는다.
        //벽 세우기
        boolean[] v = new boolean[N*M];
        maxArea = 0;
        build(0,0,v);//몇 개를 뽑았는지, v의 인덱스, v 배열

        System.out.println(maxArea);
        
        br.close();
    }//main

    //N*M에서 3개를 뽑는 조합
    private static void build(int cnt,int idx ,boolean[] v) {
        if(idx==N*M&&cnt!=3) return;
        if(cnt==3){
            List<int[]> listBuild = new ArrayList<>();
            for(int i=0;i<N*M;i++){
                if(v[i]){
                    int row = i/M;
                    int col = i%M;
                    if(map[row][col]==0) listBuild.add(new int[]{row,col});
                    else return;
                }//if
            }//for i
            
            //여기까지 왔다는건 벽을 세울 수 있다는 뜻이므로 벽을 세워준다.
            //벽 세워주기
            for(int i=0;i<3;i++){
                int[] tempArr = listBuild.get(i);
                map[tempArr[0]][tempArr[1]] = 1;
            }//for

            //이제 dfs혹은 bfs로 감염이 될 곳을 표시.
            virus = new boolean[N][M];
            for(int i=0;i<list.size();i++) checkMap(list.get(i));
            //감염 정도를 다 표시 했으니 감염 안된 구역의 넓이 확인하기
            cleanArea();

            //다음을 위해 세웠던 벽 허물기
            for(int i=0;i<3;i++){
                int[] tempArr = listBuild.get(i);
                map[tempArr[0]][tempArr[1]] = 0;
            }//for

            return;
        }//기저 조건

        v[idx] = true;
        build(cnt+1,idx+1,v);
        v[idx] = false;
        build(cnt,idx+1,v);

    }//build

    private static void cleanArea() {
        int area = 0;
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                if(!virus[i][j] && map[i][j]==0) area++;

        if(area>maxArea) maxArea=area;
    }//cleanArea

    //dfs로 바이러스가 어디까지 감염하는지 표시
    private static void checkMap(int[] idx) {

//        if(virus[idx[0]][idx[1]] || map[idx[0]][idx[1]]!=0 )
        for(int d=0;d<4;d++){
            int nr = dr[d]+idx[0];
            int nc = dc[d]+idx[1];
            boolean bound = (nr>=0&&nr<N) && (nc>=0&nc<M);
            //경계값 , 이미 들린곳 , 0이 아닌 곳
            if(!bound || virus[nr][nc] || map[nr][nc]!=0 ) continue;
            virus[nr][nc] = true;
            checkMap(new int[]{nr,nc});
        }//for d
    }//checkMap
}//class
