import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0,-1,-1};
    static int[] dc = {-1,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] mapArr = new int[N][N];
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) mapArr[i][j] = Integer.parseInt(st.nextToken());
        }

        //0은 가로, 1은 대각선, 2는 세로;
        int[][][] resultArr = new int[N][N][3];

        for(int j=1;j<N;j++){
            if(mapArr[0][j]==0) {
                resultArr[0][j][0]=1;
            } else if(mapArr[0][j]==1) break;
        }

        countDP(N, resultArr, mapArr);

        System.out.println(resultArr[N-1][N-1][0]+resultArr[N-1][N-1][1]+resultArr[N-1][N-1][2]);

    }

    public static void countDP(int N, int[][][] resultArr, int[][] mapArr){


        for(int i=1;i<N;i++){

            for(int j=0;j<N;j++){


                if(mapArr[i][j]==1) continue;

                for(int d=0;d<3;d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    boolean bound = nr>=0 && nr<N && nc>=0 && nc<N;
                    if(bound){
                        if(d==1 && mapArr[i][nc]==0 && mapArr[nr][j]==0 && mapArr[nr][nc]==0){
                            resultArr[i][j][d] += (resultArr[nr][nc][0] + resultArr[nr][nc][1]+ resultArr[nr][nc][2]);
                        } else if(d!=1 && mapArr[nr][nc]==0){
                            resultArr[i][j][d] += (resultArr[nr][nc][d] + resultArr[nr][nc][1]);
                        }
                    }

                }

            }
        }



    }
}
