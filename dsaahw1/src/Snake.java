import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
public class Snake {
    public int[][] grid = null;
    int width = 20;
    int height = 20;
    Queue<pos> queueSnake = new LinkedList<>();
    pos curPos;
    pos foodPos;
    int length;
    private int numofstone,numoffood;
    public Snake() {
        if (grid == null) grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j]=1;
            }
        }
        curPos = new pos(1, 1);
        foodPos = new pos(5, 5);
        length = 1;
    }
    public class pos {
        public int x;
        public int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Snake(int width, int height){
        this.height = height;
        this.width = width;
        grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j]=1;
            }
        }
        curPos = new pos(1, 1);
        foodPos = new pos(5, 5);
        length = 1;
    }
    //generate the game map of matrix
    public void StartSnake(int i, int j){
        curPos.x = i;
        curPos.y = j;
        queueSnake.offer(curPos);
        grid[i][j] = 0;

    }
    // start snake at position (i,j)
    public void ShowSnake(){

        for(int i=-1;i<=height;i++){
            for(int j=-1;j<=width;j++){
                if((i==-1||j==-1||i == height||j==width)){System.out.print('■');}
                else if(grid[i][j]==0){System.out.print('●');}
                else if(grid[i][j]==-1){System.out.print('■');}
                else if(grid[i][j]==2){System.out.print('✫');}
                else {System.out.print("  ");}
                if(j==width){System.out.print('\n');}
            }
        }
    }
    // print the grid with snake
    public void GenerateFood(int x, int y){
        if(FoodOnSnake(x,y)){
            grid[y][x]=2;
            numoffood+=1;
        }
    }
    // generate food at position(x,y)
    public void GenerateFoodRandom(){
        Random r = new Random();
        int tmp = r.nextInt(width*height-length-numofstone-numoffood);
        int ct = 0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(grid[i][j]==1 ){
                    ct++;
                    if(ct==tmp){
                        grid[i][j]=2;
                        numoffood+=1;
                        return;
                    }
                }
            }
        }
    }
    // generate food at a random position
    public void GenerateStone(int x, int y){
        if(grid[y][x]==0){
            grid[y][x]=-1;
            numofstone+=1;
        }
    }
    // generate a stone at position(x,y)
    public void GenerateStoneRandom(){
        Random r = new Random();
        int tmp = r.nextInt(width*height-length-numoffood-numofstone);
        int ct = 0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(grid[i][j]==1){
                    ct++;
                    if(ct==tmp){
                        grid[i][j]=-1;
                        numofstone+=1;
                        return;
                    }
                }
            }
        }
    }
    // generate a stone at a random position
    public boolean FoodOnSnake(int x, int y){
        if(grid[x][y]==0){
            return true;
        }else {return false;}
    }
    // if food is on the snake, return true
// food should disappear and generates new food
    public void Move(int direction){
        pos p = new pos(0,0);
        switch (direction){
            case 1:
                p.x = curPos.x;
                p.y = curPos.y+1;
                break;
            case 2:
                p.x = curPos.x+1;
                p.y = curPos.y;
                break;
            case 3:
                p.x = curPos.x;
                p.y = curPos.y-1;
                break;
            case 4:
                p.x = curPos.x-1;
                p.y = curPos.y;
                break;
            default:
                return;
        }
        if(p.x<0||p.x>=height||p.y<0||p.y>=width){System.exit(0);}
        if(grid[p.x][p.y]==-1||grid[p.x][p.y]==0){
            System.exit(0);
        }else if(grid[p.x][p.y]==2){
            queueSnake.offer(p);
            curPos = p;
            grid[curPos.x][curPos.y]=0;
            length++;
            GenerateFoodRandom();

        }else {
            queueSnake.offer(p);
            curPos = p;
            grid[curPos.x][curPos.y]=0;
            pos tmp = queueSnake.poll();
            grid[tmp.x][tmp.y]=1;
        }

    }
// move a step according to direction, 0-right, 1-down, 2-left, 3-right
}
