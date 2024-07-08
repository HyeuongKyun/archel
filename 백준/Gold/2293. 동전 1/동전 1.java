import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] coins = new int[N];

        for(int i=0;i<N;i++) coins[i] = sc.nextInt();
        dp(N,K,coins);

    }

    public static void dp(int N, int K, int[] coins){
        int[] dpArr = new int[K+1];
        dpArr[0]=1;

        for(int idx=0;idx<N;idx++)
            for(int j=coins[idx];j<=K;j++)
                dpArr[j] += dpArr[j-coins[idx]];

        System.out.println(dpArr[K]);

    }
}
