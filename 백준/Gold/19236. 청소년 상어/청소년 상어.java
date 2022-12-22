//풀이 방법
//최대값을 구해야하는데 수많은 물고기 이동을 하고 나서 상어가 이동 하는데
//상어의 이동은 그 방향에 있어 칸의 제한이 없다 그렇기 때문에 모든 경우를
//탐색할 수 있게끔 dfs를 하여야 하고 주의할 점은 계속해서 map이 움직일 것이기 때문에
//map을 static으로 선언하는 것이 아니라 매개변수로 해서 들고 다녀야한다.
//dfs의 기저 조건으로 상어가 더 이상 이동할 곳이 없다면을 해야하는데 물고기아 없는
//빈 공간을 0으로 표현하자. 그래서 그 방향에 모든 칸이 0이면 상어의 사냥은 끝!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Fish implements Comparable<Fish> {
        int num, dir ,row,col ,sha;//번호 ,방향, 나중에 찾기 편하게 하기위해 위치 정보,상어면 1아니면 0
        public Fish(int num, int dir, int row, int col) {
            this.num = num;
            this.dir = dir;
            this.row = row;
            this.col = col;
        }

        //priority queue에 넣어서 num의 오름차순으로 뽑기 편하게 하기 위함이다.
        @Override
        public int compareTo(Fish o) {
            return this.num > o.num ? 1 : -1;
        }
    }//Fish
    static int[] dr = {-1,-1,-1,0,1,1,1,0};//0인덱스를 0 처리해버리면 나머지로 연산할 때 문제가된다.
    static int[] dc = {1,0,-1,-1,-1,0,1,1};//방향이 12시부터 1이기 때문에 1시부터가 0이다.
    static int maxVal;//제일 많은 물고기를 먹었을 때의 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] map = new Fish[4][4];
        List<Fish> list = new ArrayList<>();

        for(int i=0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                Fish temp = map[i][j] = new Fish(num,dir,i,j);
                list.add(temp);
            }//for j
        }//for i
        list.add(new Fish(0,0,-1,-1));

        Collections.sort(list);
        map[0][0].sha =1;
        maxVal = 0;
        Fish shark = map[0][0];
        list.remove(shark.num);

        moveEvery(map,list,map[0][0].num,shark);

        System.out.println(maxVal);
        br.close();
    }//main

    private static void moveEvery(Fish[][] map, List<Fish> list, int val, Fish shark) {
        moveFish(map,list);
        //Fish[][] mapClone = map.clone();
        //clone으로 하는게 의미 없는 이유는 map이 자꾸 바꿔서 넘어 올 때 마다 clone
        //또한 같은 주소로 값이 바뀌게 되서 의미가 없다.
        Fish[][] mapClone = new Fish[4][4];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                mapClone[i][j] = map[i][j];

        List<Fish> listClone = new ArrayList<>();
        for(int i=0;i<list.size();i++)
            listClone.add(new Fish(list.get(i).num,list.get(i).dir,list.get(i).row,list.get(i).col));

        //현재 상어의 위치
        //상어가 이동할 경로 후보
        List<Fish> shaMoveCandiList = new ArrayList<>();
        int shaDir = shark.dir;
        int shaRow = shark.row;
        int shaCol = shark.col;
        for(int d=1;d<=3;d++){//한번에 이동할 수 있는 최대 경우 3번
            int canRow = shaRow + d*dr[shaDir];
            int canCol = shaCol + d*dc[shaDir];
            boolean bound = (canRow>=0&&canRow<4) && (canCol>=0&&canCol<4);
            if(!bound) {
                break;
            }
            if(map[canRow][canCol]==null) {
                continue;
            }
            //여기까지 왔으면 list에 들어간다는 뜻
            shaMoveCandiList.add(map[canRow][canCol]);
        }//for d

        int size = shaMoveCandiList.size();
        //더 이상 이동할 곳이 없다면
        if(size==0){
            if(maxVal<val) {
                maxVal=val;
            }
        }//기저조건
        //이동할 곳이 있을 경우
        else{

            for(int s=0;s<size;s++){
                map[shaRow][shaCol] = null;
                Fish tempCandi = shaMoveCandiList.get(s);
                int tempRow = tempCandi.row;
                int tempCol = tempCandi.col;
                int tempDir = tempCandi.dir;
                int tempNum = tempCandi.num;
                shark.row = tempRow;
                shark.col = tempCol;
                shark.dir = tempDir;
                map[tempRow][tempCol] = shark;
                int idx = -1;
                for(int i=1;i<list.size();i++){
                    if(tempNum==list.get(i).num){
                        idx = i;
                        list.remove(idx);
                        break;
                    }//if
                }//for


                moveEvery(map,list,val + tempNum,shark);

                list.add(idx,tempCandi);

                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        map[i][j] = mapClone[i][j];
                    }
                }
                for(int i=0;i<list.size();i++){
                    list.get(i).num = listClone.get(i).num;
                    list.get(i).dir = listClone.get(i).dir;
                    list.get(i).row = listClone.get(i).row;
                    list.get(i).col = listClone.get(i).col;
                }
            }//for s
        }//else
    }//moveEvery

    private static Fish[][] moveFish(Fish[][] map , List<Fish> list) {
        int size = list.size();

        //물고기 정보를 list에서 얻어서 본격적인 물고기위 위치를 이동 시켜준다.
        for(int i=1;i<size;i++){
            Fish temp = list.get(i);
            int row = temp.row;
            int col = temp.col;
            for(int d=0;d<8;d++){
                int nr = row + dr[(temp.dir+d)%8];
                int nc = col + dc[(temp.dir+d)%8];
                boolean bound = (nr>=0&&nr<4) && (nc>=0&&nc<4);

                //주변이 벽이거나 상어가 있으면 45씩 방향을 틀어라
                //그렇지않으면 자리 바꾸고 for d문을 나가라
                if(bound && (map[nr][nc]==null || map[nr][nc].sha!=1)) {
                    Fish target = map[nr][nc];
                    if(target==null){//만약 빈칸이면 temp만 자리를 이동 시켜준다
                        temp.row = nr;
                        temp.col = nc;
                        map[row][col] = null;
                        map[nr][nc] = temp;
                    }
                    else{//temp와 target 둘 다 null이 아니면 자리를 서로 바꿔준다.
                        target.row = row;
                        target.col = col;
                        temp.row = nr;
                        temp.col = nc;
                        map[row][col] = target;
                        map[nr][nc] = temp;
                    }
                    temp.dir = (temp.dir+d)%8;
                    break;
                }//if
            }//for d
        }//for i

        return map;
    }//moveFish
}//class
