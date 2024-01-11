//풀이 방법: 블락 다 합친건 결국 x×y=brown+yellow, 따라서 2x+2y-4=brown , -> brown은 항상 짝수
// y=brown/2-x+2 => x*(brown/2-x+2) = brown+yellow
// -x^2+(2+brown/2)*x - (brown+yellow) = 0;
// x = (-(2+brown/2)+Math.sqrt((2+brown/2)*(2+brown/2)-4*(brown+yellow)))/2*(-1)
//가로가 무조건 길기 때문에 Math.sqrt() 이상으로 체크

class Solution {
    public int[] solution(int brown, int yellow) {
        int x = (-(2+brown/2)+(int) (Math.sqrt((2+brown/2)*(2+brown/2)-4*(brown+yellow))))/2*(-1);
        int y = (brown+yellow)/x;
        int[] answer = {y, x};
        return answer;
    }
}