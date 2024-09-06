import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int answer=0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arrInt = new int[N];
        for(int i=0;i<N;i++)
            arrInt[i] = Integer.parseInt(st.nextToken());
        boolean[] v = new boolean[N];
        for(int i=0;i<N;i++){
            if(v[i]) continue;
            for(int j=i+1;j<N;j++){
                if(v[j]) continue;
                if(M==arrInt[i]+arrInt[j]){
                    v[i] = true; v[j] = true;
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
