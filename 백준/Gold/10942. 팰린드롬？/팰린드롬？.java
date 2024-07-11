import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nArr = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            nArr[n] = Integer.parseInt(st.nextToken());
        }

        int[][] memo = new int[N + 1][N + 1];

        int M = Integer.parseInt(br.readLine().trim());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int result = isPalindrome(S, E, nArr, memo);
            if(result==2) result = 0;
            sb.append(result).append('\n');
        }

        System.out.println(sb.toString());
    }

    public static int isPalindrome(int start, int end, int[] arr, int[][] memo) {
        if (start == end) {
            return 1; // 길이가 1인 경우
        }
        if (start + 1 == end) {
            return arr[start] == arr[end] ? 1 : 2; // 길이가 2인 경우
        }
        if (memo[start][end] != 0) {
            return memo[start][end]; // 이미 계산된 경우
        }

        if (arr[start] == arr[end] && isPalindrome(start + 1, end - 1, arr, memo) == 1) {
            memo[start][end] = 1;
        } else {
            memo[start][end] = 2;
        }
        return memo[start][end];
    }
}