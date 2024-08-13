import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[K];
        long rightMax = 0;

        for(int k=0;k<K;k++){
            arr[k] = Integer.parseInt(br.readLine());
            rightMax = Math.max(rightMax,arr[k]);
        }

        binarySearch(N, arr, rightMax);
    }

    public static void binarySearch(int N, int[] arr, long rightMax){
        long left = 0;
        long right = rightMax+1;
        long mid = 0;
        long cutCnt = 0;

        while(left < right){

            mid = (left + right) / 2;
            cutCnt = cut(arr, mid);

            if(cutCnt<N) {
                right = mid;
            }
            else {
                left = mid +1;
            }

        }
        System.out.println(left-1);
    }


    public static long cut(int[] arr, long len){
        long total = 0;
        for (int j : arr) {
            total += j / len;
        }
        return total;
    }
}

