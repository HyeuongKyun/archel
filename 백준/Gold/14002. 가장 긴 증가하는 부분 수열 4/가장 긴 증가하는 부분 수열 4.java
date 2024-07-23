import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()) ;
        int[] arr = new int[N];
        int[] order = new int[N];
        int[] lisArray = new int[N];
        int size = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        lisArray[0] = arr[0] = Integer.parseInt(st.nextToken());
        for(int n=1;n<N;n++){
            arr[n]=Integer.parseInt(st.nextToken());
            if(arr[n]>lisArray[size]) {
                lisArray[++size]=arr[n];
                order[n] = size;
            } else {
                int idx = binarySearch(arr[n], size, lisArray);
                lisArray[idx] = arr[n];
                order[n] = idx;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(size+1).append("\n");
        Stack<Integer> stack = new Stack<>();
        for(int i=N-1;i>=0;i--){
            if(order[i]==size) {
                stack.add(arr[i]);
                size--;
            } else if (size==-1) break;
        }

        int len = stack.size();
        for(int i=0;i<len;i++)
            sb.append(stack.pop()).append(" ");

        System.out.println(sb.toString());
    }

    public static int binarySearch(int arrVal, int size, int[] lisArray){
        int left = 0;
        int right = size;
        while (left<right){
            int mid = (left+right)/2;
            for(int i=0;i<lisArray.length;i++)
            if(arrVal>lisArray[mid]) left = mid+1;
            else right = mid;
        }
        return right;
    }
}
