//풀이 방법 : 셀을 기준으로 탐색
//4방 탐색을하는데 근처에 cell이 가는 방향일 떄만 연산을 할꺼니까
//최대 1,000의 연산
//한시간이 지남을 탐색하는데 필요한 연산이 1,000 근데 격리 최대시간이 1,000
//1,000,000  = 백만
//그런데 여기서 택스트 케이스가 50개*백만 = 오천만 => 가능
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N,M,K;//셀의 개수 , 격리 시간 , 최초 군집의 개수
    static int[] dr = {-1,0,1,0};//12시부터 시계방향
    static int[] dc = {0,1,0,-1};
    static Node[][] cell,cellClone;//미생물에 대한 정보를 담을 배열
    static class Node{
        int number,direction;//갯수와 방향
        public Node(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }
    }//Node
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cell = new Node[N][N];
            //입력값 cell배열에 넣기
            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int number = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                cell[row][col] = new Node(number,direction);
            }

            //입력값 받기 끝;

            //4방향 탐색으로 영향을 미칠 셀이 있는지 N*N모두 탐색
            //이렇게 하는 이유는 이동하는 미생물 기준으로 하면 3개 이상의 군집이 한 곳으로 뭉칠때
            //계산하는 것이 힘들기 때문입니다.
            //M시간 동안 미생물의 이동

            for(int m=0;m<M;m++){
                //cellClone은 변하는 값 기록
                //cell은 값이 다 바뀔때 까지 원래 값 유지
                cellClone = new Node[N][N];
                microMove();
                //다 바뀌고 나면 cell에게 중간에 바뀐값 깊은복사
                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        cell[i][j] = cellClone[i][j];
                    }
                }
            }
            
            //M시간 동안 미생물이 이동하였으므로 숫자 확인
            //이렇게 하지말고 처음에 전체 갯수를 카운트 해두고 줄어드는 셀만큼만 뺐어도 되지만
            //지금은 시간이 충분하니까 그냥 다시 cell배열을 돌면서 확인한다.

            int totalCnt = 0;
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++){
                    Node node = cell[i][j];
                    if(node!=null)
                        totalCnt += node.number;
                }//for

            System.out.printf("#%d %d\n",tc,totalCnt);


        }//테스트 케이스

        br.close();
    }//main
    private static void microMove(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int maxDirect = -1;
                int maxNumber = 0;
                int totalNum = 0;
                for(int d=0;d<4;d++){
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    boolean bound = (nr>=0&&nr<N) && (nc>=0&&nc<N);
                    if (!bound) {
                        continue;
                    }
                    Node tempNode = cell[nr][nc];
                    if(tempNode==null) {
                        continue;
                    }
                    int nowNodenumber = tempNode.number;
                    int nowNodeDirect = tempNode.direction;

                    //4방 탐색을 하는데 근처에 미생물 군집이 있어도 해당 cell로 오는게 아니면 연산을 더 할 필요가 없다.
                    boolean directCheck = false;
                    if(d==0&&nowNodeDirect==2) {
                        directCheck=true;
                    }
                    else if(d==1&&nowNodeDirect==3) directCheck=true;
                    else if(d==2&&nowNodeDirect==1) directCheck=true;
                    else if(d==3&&nowNodeDirect==4) directCheck=true;
                    if(!directCheck) continue;
                    //가장 영향력이 큰 군집의 방향을 기록해둔다.
                    if(nowNodenumber>maxNumber) {
                        maxNumber=nowNodenumber;
                        maxDirect = nowNodeDirect;
                    }

                    //해당 셀로 오는 군집의 갯수들을 더한다.
                    totalNum += nowNodenumber;

                    //그리고 다음 시간(반복을 위해 이동을 하기 전 node는 null값으로 변경해 준다.)
                }//for(d)

                //4방 탐색을 하면서 필요한 정보를 모두 가져왔으므로 새로운 node를 cell에 넣어준다.
                Node newNode = null;
                if(i==0||i==N-1||j==0||j==N-1){
                    if(totalNum/2!=0){
                        if(maxDirect%2==0)
                            newNode = new Node(totalNum/2,maxDirect-1);
                        else
                            newNode = new Node(totalNum/2,maxDirect+1);
                    }
                    //만약 0일때는 군집이 없어진거니까 그냥 null을 넣는다.
                } else if (maxDirect==-1||maxNumber==0) {
                    newNode = null;
                } else {
                    newNode = new Node(totalNum, maxDirect);
                }
                //방문 기록은 null이 아니라 새로운 값이 들어갔을 때만 해준다.
                cellClone[i][j] = newNode;



            }//for(j)
        }//for(i)

    }//microMove
}//class
