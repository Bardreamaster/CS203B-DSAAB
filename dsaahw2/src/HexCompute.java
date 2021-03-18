
import javax.naming.NoPermissionException;
import java.util.Stack;

public class HexCompute {
    private static char[] DECtoHEX = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private static int[] HEXtoDEC = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    public static String addStrings(String num1, String num2){
        String ans="";
        if(!PoN(num1) && PoN(num2)){
            return lessString(num2,num1);
        }else if(PoN(num1) && !PoN(num2)){
            return  lessString(num1,num2);
        }else if(!PoN(num1) && !PoN(num2)){
            ans = addStrings(abs(num1),abs(num2));
            String test = abs(ans);
            if(test.equals("0")){return "0";}
            return '-' + ans;
        }
        num1 = abs(num1);
        num2 = abs(num2);
        if(!isInt16(num1)||!isInt16(num2)){return "ERROR";}
        char carry = 0;
        num1 = new StringBuffer(num1).reverse().toString();
        num2 = new StringBuffer(num2).reverse().toString();
        int ll,ls;
        boolean ck=true;
        if(num1.length()<num2.length()){
            ll=num2.length();ls=num1.length();ck=false;
        }else {ll=num1.length();ls=num2.length();ck=true;}

        for(int i=0;i<ll;i++){
            if(i>=ls){
                if(ck){
                    ans = ans + add(num1.charAt(i),'0',carry);
                    carry = iscarry(num1.charAt(i),'0',carry);
                }else {
                    ans = ans + add('0',num2.charAt(i),carry);
                    carry = iscarry('0',num2.charAt(i),carry);
                }
            }else{
                ans = ans + add(num1.charAt(i),num2.charAt(i),carry);
                carry = iscarry(num1.charAt(i),num2.charAt(i),carry);
            }
        }
        ans = ans.concat(String.valueOf(carry));
        ans = new StringBuffer(ans).reverse().toString();
        ans = nonezero(ans);

        return ans;
    }

    public static String multiply(String num1, String num2){
        String ans="";
        String tmp="";
        boolean PoN = true;
        if(PoN(num1)&&PoN(num2)||(!PoN(num1)&&!PoN(num2))){
            PoN =true;
        }else{
            PoN = false;
        }
        num1 = abs(num1);
        num2 = abs(num2);
        if(!isInt16(num1)||!isInt16(num2)){return "ERROR";}
        if(num1=="0"||num2=="0"){return "0";}
        num1 = new StringBuffer(num1).reverse().toString();
        num2 = new StringBuffer(num2).reverse().toString();

        char carry = '0';
        for(int i=0;i<num1.length();i++){
            for (int j=0;j<num2.length();j++){
                tmp = tmp + multi(num1.charAt(i),num2.charAt(j),carry);
                carry = carrys(num1.charAt(i),num2.charAt(j),carry);
            }
            if(i==0){
                ans = tmp;
                if(carry!='0'){
                    ans = ans.concat(String.valueOf(carry));
                }
            }else {
                if(carry!='0'){
                    tmp = tmp.concat(String.valueOf(carry));
                }
                for(int k=0;k<i;k++){tmp = "0".concat(tmp);}
                tmp = new StringBuffer(tmp).reverse().toString();
                ans = new StringBuffer(addStrings(new StringBuffer(ans).reverse().toString(),tmp)).reverse().toString();
            }
            tmp = "";
            carry = '0';
        }
        ans = new StringBuffer(ans).reverse().toString();
        ans = nonezero(ans);
        if(PoN){
            return ans;
        }else {
            return '-' + ans;
        }

    }

    public static String lessString(String num1, String num2){
        if(num1 == num2){return "0";}
        String ans="";
        String tmp="";
        num1 = abs(num1);
        num2 = abs(num2);
        boolean PoN = true;
        if(!cmp(num1,num2)){
            tmp = num1;
            num1 = num2;
            num2 = tmp;
            PoN = false;
        }

        char carry = 0;
        num1 = new StringBuffer(num1).reverse().toString();
        num2 = new StringBuffer(num2).reverse().toString();
        int ll=num1.length(),ls=num2.length();

        for(int i=0;i<ll;i++){
            if(i>=ls){
                ans = ans + less(num1.charAt(i),'0',carry);
                carry = iscarryl(num1.charAt(i),'0',carry);
            }else{
                ans = ans + less(num1.charAt(i),num2.charAt(i),carry);
                carry = iscarryl(num1.charAt(i),num2.charAt(i),carry);
            }
        }
        ans = new StringBuffer(ans).reverse().toString();
        ans = nonezero(ans);
        if(abs(ans).equals("0")){return "0";}
        if(PoN){
            return ans;
        }else {
            return ans = '-' + ans;
        }


    }
    private static boolean isInt16(String in){
        for(int i=0;i<in.length();i++){
            char tmp = in.charAt(i);
            if(tmp>70 || tmp<48 ||(tmp>57  && tmp<65 )){
                return false;
            }
        }
        return true;
    }
    private static boolean PoN(String in){
        if(in.isEmpty()){return false;}
        if(in.charAt(0)=='-'){
            return false;
        }else {
            return true;
        }
    }
    private static boolean cmp(String num1,String num2){
        if(num1.length()>num2.length()){
            return true;
        }else if(num1.length()<num2.length()){
            return false;
        }else {
            return (num1.compareTo(num2) >= 0);
        }
    }
    private static String abs(String in){
        if(in.charAt(0)=='-'||in.charAt(0)=='+'){
            in = in.substring(1);
        }
        return in;
    }
    private static char add(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        int ans = (m + n + p) % 16;
        return toHEX(ans);
    }
    private static char iscarry(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        if((m + n + p) >= 16){
            return '1';
        }else {return '0';}
    }
    private static char less(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        int ans = (16 + m - n - p) % 16;
        return toHEX(ans);
    }
    private static char iscarryl(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        if((m - n - p) < 0){
            return '1';
        }else {return '0';}
    }
    private static char multi(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        return toHEX((m*n+p)%16);
    }
    private static char carrys(char a,char b,char c){
        int m=toDEC(a),n=toDEC(b),p=toDEC(c);
        return toHEX((m*n+p)/16);
    }
    private static char toHEX(int in){
        if(in>=0 && in<=16){
            return DECtoHEX[in];
        }else {
            return '0';
        }
    }
    private static int toDEC(char in){
        if(in>=48 && in<=57){
            return HEXtoDEC[in-48];
        }else if(in>=65 && in<=70){
            return HEXtoDEC[in-55];
        } else{
            return 0;
        }
    }
    private static String nonezero(String in){

        while (in.charAt(0)=='0' && in.length()>1){
            in = in.substring(1);
        }
        return in;
    }
}
