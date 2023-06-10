//가장 큰 경후 한 번 회전(100-1)*4 = 약 400
//그런데 이런 회전이 10,000까지 가능 => 400*10,000 = 4,000,000
//즉, 그냥 구현 문제 반복 돌면서 숫자들 한칸씩 밀어주고 그중에 최소값을 기억해뒀다가 배열에 담으면 된다.
//2차원 배열안에 값을 다 넣고 시작할 필요는 없지만 최대 10,000이니까 그냥 넣고 진행해도 괜찮다.


import java.util.*;

class Solution {
    static List<Integer> list;
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        list = new ArrayList<>();
        map = new int[rows+1][columns+1];
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=columns;j++){
                map[i][j] = (i-1)*columns + j;
            }
        }
        // for(int i=0;i<=rows;i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }
        
        //회전
        circle(queries);
        
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        // int[] answer = {};
        return answer;
    }//main
    
    public static void circle(int[][] queries){
        for(int c=0;c<queries.length; c++){
            int minValue = 10001;//최대 10000이니 초기값은 그보다만 크면된다.
            int[] tempQueries = queries[c];
            int x1=tempQueries[0]; int y1=tempQueries[1]; int x2=tempQueries[2]; int y2=tempQueries[3];
            //처음 모서리에 있는 값 따로 저장
            int x1y2 = map[x1][y2];
            minValue = x1y2;
            
            
            //여기서 코드 줄이려면 상하, 좌우 묶을 수 있다.
            //상
            for(int i=y2;i>y1;i--){
                int tempVal = map[x1][i-1];
                if(minValue>tempVal) minValue = tempVal;
                map[x1][i] = tempVal;
            }
            //좌
            for(int i=x1;i<x2;i++){
                int tempVal = map[i+1][y1];
                if(minValue>tempVal) minValue = tempVal;
                map[i][y1] = tempVal;
            }
            //하
            for(int i=y1;i<y2;i++){
                int tempVal = map[x2][i+1];
                if(minValue>tempVal) minValue = tempVal;
                map[x2][i] = tempVal;
            }
            //우
            for(int i=x2;i>x1;i--){
                int tempVal = map[i-1][y2];
                if(minValue>tempVal) minValue = tempVal;
                map[i][y2] = tempVal;
            }
            //처음 모서리 값은 이미 바뀐 상태였으니 원래 값을 넣어준다.
            map[x1+1][y2] = x1y2;
            list.add(minValue);
        }//for c
    }//circle
}