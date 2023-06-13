//풀이: 그룹의 원소 갯수를 k라 하고 1번부터 s/2=500 이하까지 어떤 경우가 가장 짧게 만들어지는지 확인(500이상은 중복이 생길 가능성이 없으니 제외)
//k개의 원소로 그룹을 체크하기 위해 확인하는 반복문 약 1000/k
// 500+333+...+250+...+2 = 1000* (1/2 + 1/3 + 1/4 +...+ 1/500) < 1000 * (1+..8개...+1) ~= 8,000 그러므로 모든 경우 탐색해도 괜찮다. 

class Solution {
 public int solution(String s) {
        int ans = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            int len = 0;

            for(int j = 0; j + i <= s.length();){
                int h = j + i;
                int cnt = 1;
                String seg = s.substring(j, j + i);

                while(h + i <= s.length() && seg.equals(s.substring(h, h + i))){
                    h += i;
                    cnt ++;
                } 

                if(cnt == 1) len += i;
                else len += i + String.valueOf(cnt).length();  

                j = h;
            }

            if(s.length() % i != 0) len += s.length() % i; 
            ans = Math.min(ans, len);
        }

        return ans;
    }
}