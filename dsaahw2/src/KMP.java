import java.util.LinkedList;

public class KMP {

    public static int[] computeNextArray(String pattern) {
        int l = pattern.length();
        int[] next = new int[l];
        next[0] = -1;
        next[1] = 0;
        for(int i=1,j=0;i<l-1;i++){
            while (j>0 && pattern.charAt(i)!=pattern.charAt(j)){
                j = next[j-1];
                if(j<0){j=0;}
            }
            if(pattern.charAt(i)==pattern.charAt(j)){
                j++;
            }
            next[i+1] = j;
        }
        return next;
    }


    public static int KMPSearchTimes(String text, String pattern) {
        int ans = 0;
        int[] next = computeNextArray2(pattern);
        int tl = text.length(),pl = pattern.length();
        int j=0;
        for (int i=0;i<tl;i++){
            while (j>0 && text.charAt(i)!=pattern.charAt(j)){
                j = next[j-1];
            }
            if(text.charAt(i)==pattern.charAt(j)){
                if(j==pl-1){
                    i = i - pl + 1;
                    j = 0;
                    ans++;
                }else{
                    j++;
                }
            }
        }
        return ans;
    }

    public static LinkedList<Integer> KMPFindLocations(String text, String pattern) {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        int[] next = computeNextArray2(pattern);
        int tl = text.length(),pl = pattern.length();
        int j=0;
        for (int i=0;i<tl;i++){
            while (j>0 && text.charAt(i)!=pattern.charAt(j)){
                j = next[j-1];
            }
            if(text.charAt(i)==pattern.charAt(j)){
                if(j==pl-1){
                    i = i - pl + 1;
                    j = 0;
                    ans.add(i);
                }else{
                    j++;
                }
            }
        }
        return ans;
    }

    public static int[] computeNextArray2(String pattern) {
        int l = pattern.length();
        int[] next = new int[l];
        next[0] = 0;
        for(int i=1,j=0;i<l;i++){
            while (j>0 && pattern.charAt(i)!=pattern.charAt(j)){
                j = next[j-1];
            }
            if(pattern.charAt(i)==pattern.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
