import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=1;tc<=TC;tc++){
            br.readLine();//테스트케이스 받는 변수 빼주기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] scores = new int[101];
            int maxVin = -1;
            int maxIdx = -1;
            for(int i=0;i<1000;i++){
                int temp = Integer.parseInt(st.nextToken());
                scores[temp] += 1;
                int maxi = scores[temp];
                if(maxi>maxVin){
                    maxVin = maxi;
                    maxIdx = temp;
                }
                else if(maxi==maxVin)
                    if(temp>maxIdx)
                        maxIdx = temp;
            }

            sb.append("#" + tc + " " + maxIdx +"\n");
        }//테스트 케이스
        System.out.println(sb);
    }//main
}//class
