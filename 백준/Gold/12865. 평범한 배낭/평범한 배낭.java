import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dpArr = new int[N+1][K+1]; //무게따라 표현
        int[][] items = new int[N+1][2]; //물건 정보

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            items[i][0]=Integer.parseInt(st.nextToken());
            items[i][1]=Integer.parseInt(st.nextToken());
        }

        dp(N,K,dpArr, items);

    }

    public static void dp(int N, int K, int[][] dpArr, int[][] items){
        for(int n=1;n<=N;n++){

            for(int k=1;k<=K;k++){
                if(k-items[n][0]<0) {
                    dpArr[n][k] = dpArr[n-1][k];
                    continue;
                }
                dpArr[n][k] = Math.max(dpArr[n-1][k],dpArr[n-1][k-items[n][0]]+items[n][1]);
            }

        }
        System.out.println(dpArr[N][K]);

    }
}