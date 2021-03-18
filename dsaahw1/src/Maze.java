import java.util.*;

public class Maze {
    private int[][] mazeMat;
    private int width;
    private int height;
    private boolean[][] tabu;
    public Maze(int[][] mazeMatrix, int width, int height) {
        this.mazeMat = mazeMatrix;
        this.width = height;
        this.height = width;
    }
    class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public void DFSearchPath(int startX, int startY, int endX, int endY) {
        inittabu();
        Stack<Node> dfs = new Stack<Node>();
        Node s = new Node(startX,startY),e = new Node(endX,endY);
        dfs.push(s);
        tabu[startX][startY] = true;
        tabu[endX][endY] = true;
        int cur = 0;
        int max = 0;

        do{
            Node p = new Node(dfs.peek().x,dfs.peek().y) ;
            if((cur=search(p))>0){
                dfs.push(p);
                tabu[p.x][p.y] = false;
            }else{
                dfs.pop();
            }
            max += 1;
            if(max > 100000){break;}
        }while((!dfs.isEmpty()) && (dfs.peek().x!=e.x || dfs.peek().y!=e.y));

        while (!dfs.isEmpty()){
            Node tmp = dfs.pop();
            mazeMat[tmp.x][tmp.y] = 7;
        }
    }

    private int search(Node n){
        //if(isLegal(n)){
            n.y += 1 ;
            if(isLegal(n)){
                return 1;
            }
            n.y -= 1 ;
            n.x += 1 ;
            if(isLegal(n)){
                return 2;
            }
            n.y -= 1 ;
            n.x -= 1 ;
            if(isLegal(n)){
                return 3;
            }
            n.y += 1 ;
            n.x -= 1 ;
            if(isLegal(n)){
                return 4;
            }
            return 0;
        //}
        //return -1;
    }

    private boolean isLegal(Node n){
        if(n.x>=0 && n.x<this.width && n.y>=0 && n.y<this.height){
            if(tabu[n.x][n.y]){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
    private void inittabu(){
        tabu = new boolean[this.width][this.height];
        for(int i=0;i < this.width;i++){
            for(int j=0;j < this.height;j++){
                if(mazeMat[i][j]==0){
                    tabu[i][j] = true;
                }else{tabu[i][j] = false;}
            }
        }
    }
    @Override
    public String toString() {
        String ResultString = "";
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ResultString += mazeMat[i][j]+" ";
            }
            ResultString += "\n";
        }
        return ResultString;
    }

}




