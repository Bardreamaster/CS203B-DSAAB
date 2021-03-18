import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
public class BigInt16test {

    String a;
    String b;

    @Test
    public void test1() {
        a ="2";
        b ="3";
        assertEquals("5",HexCompute.addStrings(a,b));
        a = "7";
        b = "F";
        assertEquals("16",HexCompute.addStrings(a,b));
        a ="123";
        b ="456";
        assertEquals("579",HexCompute.addStrings(a,b));
        a ="123333";
        b ="456666";
        assertEquals("579999",HexCompute.addStrings(a,b));
        a ="123";
        b ="456456456456";
        assertEquals("456456456579",HexCompute.addStrings(a,b));
        a ="AA52C";
        b ="BC766E";
        assertEquals("C71B9A",HexCompute.addStrings(a,b));
        a ="+FFF52E";
        b ="2F573FFA";
        assertEquals("30573528",HexCompute.addStrings(a,b));
        a = "+F3EBCD9976F2A4";
        b ="+ED72F86AFCA77392";
        assertEquals("EE66E438961E6636",HexCompute.addStrings(a,b));
    }
    @Test
    public void test2(){
        a = "+4";
        b ="-7";
        assertEquals("-3",HexCompute.addStrings(a,b));
        a = "EE9375BA2";
        b ="-4396F2CF";
        assertEquals("EA5A068D3",HexCompute.addStrings(a,b));
        a = "FBCA2B";
        b ="-77F9DA82";
        assertEquals("-76FE1057",HexCompute.addStrings(a,b));
        a = "-FBCA2B";
        b ="-77F9DA82";
        assertEquals("-78F5A4AD",HexCompute.addStrings(a,b));
    }
    @Test
    public void test3(){
        a="2";
        b="3";
        assertEquals("6",HexCompute.multiply(a,b));
        a="F";
        b="27";
        assertEquals("249",HexCompute.multiply(a,b));
        a="123";
        b="20";
        assertEquals("2460",HexCompute.multiply(a,b));
        a="123";
        b="456";
        assertEquals("4EDC2",HexCompute.multiply(a,b));
        a="73FD6";
        b="9";
        assertEquals("413E86",HexCompute.multiply(a,b));
        a="73FD6";
        b="4399";
        assertEquals("1EA0A28E6",HexCompute.multiply(a,b));
        a="-439F68";
        b="-74F285";
        assertEquals("1EE44AF22108",HexCompute.multiply(a,b));
        a="20201025";
        b="-2020996";
        assertEquals("-4081945DA4C2AE",HexCompute.multiply(a,b));
    }
}
