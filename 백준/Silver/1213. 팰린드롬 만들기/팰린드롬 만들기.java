import java.util.Scanner;

public class Main {
    static String ERROR_MSG = "I'm Sorry Hansoo";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] charArr = str.toCharArray();
        int[] arrCnt = new int[26];

        for (int i = 0; i < charArr.length; i++) {
            char tmpChar = charArr[i];
            int idx = (int) tmpChar - 'A';
            arrCnt[idx]++;
        }

        //홀수 일 때만 중앙 처리

        int oddCnt = 0;
        for (int i = 0; i < 26; i++){
            if (arrCnt[i] % 2 != 0) {
                oddCnt++;
                if (oddCnt != 1) {
                    System.out.println(ERROR_MSG);
                    return;
                }
                arrCnt[i]--; // 중앙 배치
                charArr[charArr.length / 2] = (char) ('A' + i);
            }
        }

        int idx = 0;
        for(int i=0;i<26;i++){
            while(arrCnt[i] > 0){
                charArr[idx] = charArr[charArr.length-1-idx] = (char) ('A' + i);
                idx++;
                arrCnt[i]-=2;
            }
        }

        String result = "";
        for(int i=0;i<charArr.length;i++)
            result += charArr[i];

        System.out.println(result);

    }
}
