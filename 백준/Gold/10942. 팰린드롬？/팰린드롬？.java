import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nArr = new int[N+1];
        for(int n=1;n<=N;n++) nArr[n] = Integer.parseInt(st.nextToken());

        int[][] nMap = new int[N+1][N+1];
        nMap[N][N]=1;
        for(int i=N;i>=1;i--){
            for(int j=i;j<=N;j++){
                //하나는 팰린드롬
                if(j==i) nMap[i][j]=1;
                //두개는 앞뒤 비교
                else if(j==i+1 && nArr[i]==nArr[j]) nMap[i][j]=1;
                // 3개 부터는 nArr[i]==nArr[j]이고 nMap[i+1][j-1]==1이면 nMap[i][j]=1
                else if(nMap[i+1][j-1]==1 && nArr[i]==nArr[j]) nMap[i][j]=1;
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            sb.append(nMap[i][j]).append("\n");
        }

        System.out.println(sb);
    }

}


