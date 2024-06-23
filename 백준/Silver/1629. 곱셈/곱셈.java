import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 그냥 B번 곱해 나가면 2^32-1 만큼 2*10^9만큼이기에 타임아웃
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());
        Long C = Long.parseLong(st.nextToken());

        System.out.println(pow(A,B,C));
    }
    
    public static Long pow(Long A, Long B, Long C){
        
        if(B==0) return 1L;
        Long temp = pow(A,B/2,C);
        if(B%2==0){
            return temp*temp%C;
        }
        return ((temp*temp)%C*A)%C;
    } 
}
