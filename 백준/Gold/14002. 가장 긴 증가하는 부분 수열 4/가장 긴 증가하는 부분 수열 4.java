import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int maxCnt = 0;
        int maxIdx = -1;
        int[][] arr = new int[N][2];
        List<List<Integer>> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int n=0;n<N;n++) {
            arr[n][0] = Integer.parseInt(st.nextToken());
            list.add(new ArrayList<>());
            int tempInt = 0;
            for(int m=0;m<n;m++){
                if(arr[m][0]<arr[n][0] && arr[m][1]>tempInt) {
                    tempInt=arr[m][1];
                    list.remove(n);
                    list.add(new ArrayList<>());

                    List<Integer> tempList = list.get(m);
                    for(int i=0;i<tempList.size();i++) list.get(n).add(tempList.get(i));
                }
            }

            if(tempInt+1>maxCnt) {
                maxCnt = tempInt+1;
                maxIdx = n;
            }

            arr[n][1] = tempInt+1;
            list.get(n).add(arr[n][0]);
        }
        System.out.println(maxCnt);
        for(int i=0;i<list.get(maxIdx).size();i++) System.out.printf("%d ",list.get(maxIdx).get(i));


    }

}
