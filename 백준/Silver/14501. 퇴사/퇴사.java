import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] Ti = new int[N+1];
        int[] Pi = new int[N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            Ti[i] = Integer.parseInt(st.nextToken());
            Pi[i] = Integer.parseInt(st.nextToken());
        }

        int[] resultArr = new int[N+1];

        calculation(N, Ti, Pi, resultArr);

        System.out.println(resultArr[1]);
    }

    public static void calculation(int N, int[] Ti, int[] Pi, int[] resultArr){
        if(Ti[N]==1) resultArr[N] = Pi[N];

        for(int n=N-1; n>0; n--){
            int t = Ti[n];
            int p = Pi[n];

            if(t+n>N+1) {
                resultArr[n] = resultArr[n+1];
                continue;
            }

            if(t+n==N+1){
                if(p>=resultArr[n+1]) resultArr[n] = p;
                else resultArr[n] = resultArr[n+1];
            } else {
                if(resultArr[t+n]+p > resultArr[n+1]) resultArr[n] = resultArr[t+n]+p;
                else resultArr[n] = resultArr[n+1];
            }

        }
    }
}
