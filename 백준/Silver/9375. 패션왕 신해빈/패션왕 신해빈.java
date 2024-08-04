import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());

        for(int tc=1;tc<=TC;tc++){
            int N = Integer.parseInt(br.readLine());
            Map<String, ArrayList<String>> map = new HashMap<>();
            List<String> kindList = new ArrayList<>();
            for(int n=0;n<N;n++){
                st = new StringTokenizer(br.readLine());
                String clothes = st.nextToken();
                String kind = st.nextToken();

                input(map, kindList, clothes,kind);
            }

            count(map, kindList);

        }

        System.out.println(sb);
    }

    public static void input(Map<String, ArrayList<String>> map, List<String> kindList, String clothes, String kind){
        ArrayList<String> tmpList = map.getOrDefault(kind, new ArrayList<String>());
        if(tmpList.size()==0) kindList.add(kind);
        tmpList.add(clothes);

        map.put(kind, tmpList);

    }

    public static void count(Map<String, ArrayList<String>> map, List<String> kindList){
        int totalCnt = 1;
        for (String kind:
             kindList) {
            int kindCnt = map.get(kind).size();
            totalCnt *= (kindCnt+1);
        }
        sb.append(--totalCnt).append("\n");
    }
}
