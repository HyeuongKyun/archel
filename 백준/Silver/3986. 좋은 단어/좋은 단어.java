import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cnt = 0;
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            findGoodWords(br.readLine());
        }

        System.out.println(cnt);

    }

    public static void findGoodWords(String word){
        char[] charArr = word.toCharArray();
        Stack<Character> charStack = new Stack<>();
        for(int i=0; i<charArr.length;i++){

            char nowChat = charArr[i];

            if (charStack.isEmpty()) charStack.add(nowChat);
            else {
                if (charStack.peek()==nowChat) charStack.pop();
                else charStack.push(nowChat);
            }
        }

        if (charStack.isEmpty()) cnt++;

    }
}
