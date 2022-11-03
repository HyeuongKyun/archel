import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int N = numbers.length;
        String[] arr = new String[N];
        for(int i=0;i<N;i++){
           arr[i] = String.valueOf(numbers[i]); 
        }
        Arrays.sort(arr, new Comparator<String>() {
            
            @Override
            public int compare(String o1, String o2) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                sb1.append(o1).append(o2);
                sb2.append(o2).append(o1);
                int sbInt1 = Integer.parseInt(sb1.toString());
                int sbInt2 = Integer.parseInt(sb2.toString());
                if(sbInt1>sbInt2) return -1;
                else if(sbInt1<sbInt2) return 1;
                else return 0;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<N;i++){
            sb.append(arr[i]);
        }
        
        String answer = sb.toString();
        if (answer.charAt(0) == '0') return "0";
        else return answer;
    }
}