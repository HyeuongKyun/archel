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

        System.out.println(binarySearch(K,N, arr, rightMax));
    }

    public static long binarySearch(int K, int N, int[] arr, long rightMax){
        long left = 1;
        long right = rightMax+1;
        long mid = (left + right) / 2;
        int cutCnt = 0;

        while(left < right){
            cutCnt = cut(arr, mid);

            if(cutCnt>=N)
                left = mid +1;
            else
                right = mid;

            mid = (left + right) / 2;
        }
        return mid-1;
    }

    public static int cut(int[] arr, long len){
        int total = 0;
        for(int i=0;i<arr.length;i++) {
            total += (int) (arr[i]/len);
        }
        return total;
    }
}
