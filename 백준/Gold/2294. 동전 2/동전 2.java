import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][K+1];
        int[] nArr = new int[N+1];
        for(int n=1;n<=N;n++) nArr[n] = Integer.parseInt(br.readLine());
        dp(N, K, map, nArr);

        System.out.println(map[N][K]);

    }

    public static void dp(int N, int K, int[][] map, int[] nArr){
        // 첫 줄은 다음 줄 계산을 위해 미리 넣음
        for(int k=1;k<=K;k++)
            if(k%nArr[1]!=0) map[1][k]=-1;
            else map[1][k]=k/nArr[1];

        for(int n=2;n<=N;n++){
            for(int k=1;k<=K;k++){
                if(k<nArr[n]) map[n][k] = map[n-1][k];
                else if (k==nArr[n]) map[n][k] = 1;
                else {
                    int map1 = map[n-1][k]; int map2 = map[n][k-nArr[n]]+1;
                    if(map1==-1 && map2==0) map[n][k]=-1;
                    else if(map1!=-1 && map2!=0) map[n][k] = Math.min(map1, map2);
                    else if(map1!=-1) map[n][k] = map1;
                    else if(map2!=0) map[n][k] = map2;
                }
            }
        }
    }
}
