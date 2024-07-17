import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<int[]> lines = new ArrayList<>();
        lines.add(new int[]{0,0});
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int lineA = Integer.parseInt(st.nextToken());
            int lineB = Integer.parseInt(st.nextToken());
            int[] tempArr = new int[]{lineA,lineB};
            lines.add(tempArr);
        }

        Collections.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int[][] newLines = new int[N+1][2];
        int maxVal = 0;
        for(int i=1;i<=N;i++) {
            int maxCnt = 0;
            newLines[i][0] = lines.get(i)[1];
            for(int j=1;j<i;j++){
                if(newLines[j][0]<newLines[i][0] && newLines[j][1]>maxCnt) maxCnt=newLines[j][1];
            }
            newLines[i][1]=maxCnt+1;
            if(maxVal<newLines[i][1]) maxVal=newLines[i][1];
        }

        System.out.println(N-maxVal);

    }
}
