import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int n=0;n<N;n++){
            String word = br.readLine();
            boolean[] vArr = new boolean[26];
            char[] charArr = word.toCharArray();
            boolean result = true;
            vArr[(int)(charArr[0]-'a')] = true;
            for(int i=1;i<charArr.length;i++){
                if(charArr[i-1]==charArr[i]) continue;
                int idx = (int)(charArr[i]-'a');
                if(vArr[idx]) {
                    result = false;
                    break;
                }
                vArr[idx] = true;
            }
            if(result) cnt++;

        }

        System.out.println(cnt);

    }
}
