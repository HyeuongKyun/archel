import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nArr = new int[N+1];
        for(int n=1;n<=N;n++) nArr[n] = Integer.parseInt(st.nextToken());

        //0이면 안해본거, 1이면 팰린드롬o, 2면 팰린드롬x
        int[][] nMap = new int[N+1][N+1];
        nMap[N][N]=1;
        for(int n=1;n<N;n++){
            //하나는 팰린드롬
            nMap[n][n]=1;
            //두개는 앞뒤 비교
            if(nArr[n]==nArr[n+1]) nMap[n][n+1]=1;
            else nMap[n][n+1]=2;
            // 3개 부터는 nArr[i]==nArr[j]이고 nMap[i+1][j-1]==1이면 nMap[i][j]=1
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for(int m=1;m<=M;m++){
            st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());
            int result = findVal(idx1, idx2, nMap,nArr);
            if(result==2) result=0;
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());

    }


    public static int findVal(int idx1, int idx2, int[][] nMap, int[] nArr){
        if(nMap[idx1][idx2]!=0) {
            return nMap[idx1][idx2];
        }

        int result = findVal(idx1+1, idx2-1,nMap ,nArr);
        if(result==1 && nArr[idx1]==nArr[idx2]) nMap[idx1][idx2]=1;
        else nMap[idx1][idx2]=2;
        return nMap[idx1][idx2];
    }



}
