import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] nArr = new boolean[N+1];
        nArr[1]=true;
        if(N>=3) nArr[3]=true;

        for(int i=4;i<=N;i++){
            if(!nArr[i-3]||!nArr[i-1]) nArr[i]=true;
        }

        if(nArr[N]) System.out.println("SK");
        else System.out.println("CY");

    }
}
