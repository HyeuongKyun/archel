import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int maxCnt = 0;
        int[][] arr = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for(int n=0;n<N;n++) {
            arr[n][0] = Integer.parseInt(st.nextToken());
            int tempInt = 0;
            for(int m=0;m<n;m++){
                if(arr[m][0]<arr[n][0] && arr[m][1]>tempInt) tempInt=arr[m][1];
            }
            arr[n][1] = tempInt+1;
            if(tempInt+1>maxCnt) maxCnt = tempInt+1;
        }

        System.out.println(maxCnt);




    }

}