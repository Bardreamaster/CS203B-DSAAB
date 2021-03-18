import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class KMPtest {
    String S,P,ans;
    int[] next;

    public String show(int[] in){
        String out;
        out = "[";
        for(int i=0;i<in.length;i++){
            out += (Integer.toString(in[i]));
            if(i!=in.length-1){out+=',';}
        }
        out += ']';
        return out;
    }
    @Before
    /*public void init(){
        S = "";
        P = "abcabd";
    }*/

    @Test
    public void test1() {
        P = "abcabd";
        ans = show(KMP.computeNextArray(P));
        assertEquals("[-1,0,0,0,1,2]",ans);
        ans = show(KMP.computeNextArray2(P));
        assertEquals("[0,0,0,1,2,0]",ans);
        P ="touristrealgod";
        ans = show(KMP.computeNextArray(P));
        assertEquals("[-1,0,0,0,0,0,0,1,0,0,0,0,0,0]",ans);
        ans = show(KMP.computeNextArray2(P));
        assertEquals("[0,0,0,0,0,0,1,0,0,0,0,0,0,0]",ans);
        P ="asardasd";
        ans = show(KMP.computeNextArray(P));
        assertEquals("[-1,0,0,1,0,0,1,2]",ans);
        ans = show(KMP.computeNextArray2(P));
        assertEquals("[0,0,1,0,0,1,2,0]",ans);
        P ="abababa";
        ans = show(KMP.computeNextArray(P));
        assertEquals("[-1,0,0,1,2,3,4]",ans);
        ans = show(KMP.computeNextArray2(P));
        assertEquals("[0,0,1,2,3,4,5]",ans);
    }

    @Test
    public void test2() {
        P = "wo0000000";
        S = "chenljnbwowowoo";
        assertEquals(0,KMP.KMPSearchTimes(S,P));
        P = "tourist";
        S = "touristrealgod";
        assertEquals(1,KMP.KMPSearchTimes(S,P));
        P = "abab";
        S = "abababab";
        assertEquals(3,KMP.KMPSearchTimes(S,P));
        P = "aba";
        S = "abababab";
        assertEquals(3,KMP.KMPSearchTimes(S,P));
        P = "abcba";
        S = "abcbabcbaabcabcba";
        assertEquals(3,KMP.KMPSearchTimes(S,P));
        P = "s";
        S = "abcbabcbaabcabcba";
        assertEquals(0,KMP.KMPSearchTimes(S,P));
    }
    @Test
    public void test3() {
        P = "wo";
        S = "chenljnbwowowoo";
        assertEquals("[8, 10, 12]",KMP.KMPFindLocations(S,P).toString());
        P = "tourist";
        S = "touristrealgod";
        assertEquals("[0]",KMP.KMPFindLocations(S,P).toString());
        P = "abab";
        S = "abababab";
        assertEquals("[0, 2, 4]",KMP.KMPFindLocations(S,P).toString());
        P = "aba";
        S = "abababab";
        assertEquals("[0, 2, 4]",KMP.KMPFindLocations(S,P).toString());
        P = "abcba";
        S = "abcbabcbaabcabcba";
        assertEquals("[0, 4, 12]",KMP.KMPFindLocations(S,P).toString());
    }
}
