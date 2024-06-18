//[1,3] 을 끊으면 1과 연결된 node 갯수 counting 3과 연결된 갯수 counting(n- 1카운팅)
// -> 총 n-1번의 근처 노드 탐색
// 앞에서 부터 자를꺼니까 node 갯수가 역전되는 순간에 역전 전이 큰지 후가 큰지 비교
class Solution {
    static int nodeCount1;
    static int nodeCount2;
    static int diffMin;
    static boolean[] nodeV;
    public int solution(int n, int[][] wires) {
        diffMin=100;
        
        for(int i=0;i<wires.length;i++){
            nodeCount1=0; nodeCount2=0;
            nodeV = new boolean[n+1];
            nodeV[wires[i][0]]=true;
            nodeV[wires[i][1]]=true;
            nodeCount1++;
            checkCntDFS(i, 0, wires);
            nodeCount2= n-nodeCount1;
            // System.out.printf("i:%d,nodeCount1:%d,nodeCount2:%d\n",i,nodeCount1,nodeCount2);
            if(diffMin > Math.abs(nodeCount2-nodeCount1)) diffMin = Math.abs(nodeCount2-nodeCount1);
        }
        
        int answer = diffMin;
        return answer;
    }
    
    public void checkCntDFS(int arrIdx, int nodeIdx, int[][] wires){
        int nowNode = wires[arrIdx][nodeIdx];
        // System.out.println(nowNode);
        for(int i=0;i<wires.length;i++){
            if(wires[i][0]==nowNode && !nodeV[wires[i][1]]){
                nodeV[wires[i][1]]=true;
                nodeCount1++;
                checkCntDFS(i, 1, wires);
            }else if(wires[i][1]==nowNode && !nodeV[wires[i][0]]){
                nodeV[wires[i][0]]=true;
                nodeCount1++;
                checkCntDFS(i, 0, wires);
            }
            
        }
    }
}