import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        Arrays.sort(arrInt);

        for(int i=0, j=N-1;i<j;){
            int sum = arrInt[i]+arrInt[j];

            if(sum==M){
                answer++;
                i++;
                j--;
            }
            else if(sum>M) j--;
            else if(sum<M) i++;


        }

        System.out.println(answer);
    }
}
