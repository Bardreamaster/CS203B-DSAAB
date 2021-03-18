import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    //private Edge[] vertex;
    private int[][] matrix;
    private boolean directed;
    private int numberofVertex;

    //
    public void readGraphFile(String strFile) throws IOException {
        String fileName = strFile;
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String rl;
        String tmp = "";
        while ((rl = bf.readLine()) != null) {
            tmp += rl + " ";
        }
        String[] es = tmp.split(" ");
        int[] nums = new int[es.length];
        for (int i = 0; i < es.length; i++) {
            nums[i] = Integer.parseInt(es[i]);
        }
        bf.close();

        if(nums.length<0){
            return;
        }
        int tmpnum = 0;
        for(int i=0;i<nums.length-1;++i){
            if((i+1)%3!=0 && nums[1+i]>tmpnum){
                tmpnum = nums[1+i];
            }
        }
        numberofVertex = tmpnum+1;
        //vertex = new Edge[numberofVertex];
        matrix = new int[numberofVertex][numberofVertex];
        if(nums[0]==1){ directed = true;}
        else if(nums[0]==0){ directed = false;}

        int numE = (nums.length-1)/3;
        for(int i=0;i<numE;++i){
            //vertex[i] = new Edge(nums[i*3+1],nums[i*3+2]);
            matrix[nums[i*3+1]][nums[i*3+2]] = nums[i*3+3];
        }
        if(directed==false){
            for(int i=0;i<numE;++i){
                //vertex[i] = new Edge(nums[i*3+1],nums[i*3+2]);
                matrix[nums[i*3+2]][nums[i*3+1]] = nums[i*3+3];
            }
        }
    }

    // print each vertex in a line with other vertices and their weight in ascending order according to vertices number
    public String printAdjacencyList(){
        String strList="";
        if(true/*directed*/){
            for(int i=0;i<matrix.length;++i){
                strList += i+":";
                for(int j=0;j<matrix[0].length;++j){
                    if(matrix[i][j]>0){
                        strList += "("+ i+","+j+":"+matrix[i][j]+")";
                    }
                }
                strList += "\n";
            }
        }else{
            for(int i=0;i<matrix.length;++i){
                strList += i+":";
                for(int j=i;j<matrix[0].length;++j){
                    if(matrix[i][j]>0){
                        strList += "("+ i+","+j+":"+matrix[i][j]+")";
                    }
                }
                strList += "\n";
            }
        }

        return strList;// each line prints the start vertex and other vertices numbers and weights of edges ascending
    }

    // print each weight in matrix with vertices number as row and col in ascending order according to vertices number
    public String printAdjacencyMatrix(){
        String strMatrix="";
        for(int i=0;i<matrix[0].length;++i) {
            for(int j=0;j<matrix.length;++j){
                strMatrix += matrix[j][i]+ " ";
            }
            strMatrix += "\n";
        }
        return strMatrix;// each row and each line print the vertex numbers and weights ascending
    }

    // print the shortest path from startVertex to other vertices, except itself
    // output format: [shortestPathLength]v1,v2,...,vn
    // if no path from v1 to vn, print [0]null
    public String ShortestPath(int startVertex){
        int[] Path = new int[numberofVertex];
        boolean[] U = new boolean[numberofVertex];
        int ts = startVertex;
        for(int i=0;i<numberofVertex;++i){
            Path[i] = matrix[ts][i];
        }
        int[] res = new int[numberofVertex];
        U[ts] = true;
        for(int i=0;i<numberofVertex;++i){
            if(Path[i]!=0){
                res[i]=ts;
            }
        }
        for(int i=0;i<numberofVertex;++i){
            int used = ts;
            int L=Integer.MAX_VALUE;
            for(int j=0;j<numberofVertex;++j){
                if(Path[j]>0 &&  !U[j] && Path[j] < L ){
                    L = Path[j];
                    used = j;
                }
            }
            U[used] = true;
            for(int j = 0;j < numberofVertex;++j) {
                if(!U[j]) {
                    if(matrix[used][j] != 0 && (Path[j] > L + matrix[used][j] || Path[j] == 0)){
                        Path[j] = L + matrix[used][j];
                        res[j] = used;
                    }
                }
            }
        }
        String strPath="";
        for(int i=0;i<numberofVertex;++i){
            if(i!=ts){
                strPath += "["+Path[i]+"]";
                if(Path[i]==0){
                    strPath += "null,";
                }else{
                    Stack stack = new Stack();
                    stack.push(i);
                    int tmp2=i;
                    while(tmp2!=ts){
                        stack.push(res[tmp2]);
                        tmp2 = res[tmp2];
                    }
                    while (!stack.empty()){
                        strPath += stack.pop()+",";
                    }
                }
                strPath = strPath.substring(0,strPath.length()-1);
                strPath += "\n";
            }
        }
        return strPath;
    }

    public String ShortestPath(int startVertex, int endVertex){
        int[] Path = new int[numberofVertex];
        boolean[] U = new boolean[numberofVertex];
        int ts = startVertex;
        for(int i=0;i<numberofVertex;++i){
            Path[i] = matrix[ts][i];
        }
        int[] res = new int[numberofVertex];
        U[ts] = true;
        for(int i=0;i<numberofVertex;++i){
            if(Path[i]!=0){
                res[i]=ts;
            }
        }
        for(int i=0;i<numberofVertex;++i){
            int used = ts;
            int L=Integer.MAX_VALUE;
            for(int j=0;j<numberofVertex;++j){
                if(Path[j]>0 &&  !U[j] && Path[j] < L ){
                    L = Path[j];
                    used = j;
                }
            }
            U[used] = true;
            for(int j = 0;j < numberofVertex;++j) {
                if(!U[j]) {
                    if(matrix[used][j] != 0 && (Path[j] > L + matrix[used][j] || Path[j] == 0)){
                        Path[j] = L + matrix[used][j];
                        res[j] = used;
                    }
                }
            }
        }
        String strPath="";
        int i = endVertex;
            if(i!=ts){
                strPath += "["+Path[i]+"]";
                if(Path[i]==0){
                    strPath += "null,";
                }else{
                    Stack stack = new Stack();
                    stack.push(i);
                    int tmp2=i;
                    while(tmp2!=ts){
                        stack.push(res[tmp2]);
                        tmp2 = res[tmp2];
                    }
                    while (!stack.empty()){
                        strPath += stack.pop()+",";
                    }
                }
                strPath = strPath.substring(0,strPath.length()-1);
            }

        return strPath;
    }

}
