package basic;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class BigIntegerBigDecimal {

    @Test
    public void BigInteger() {
        BigInteger a = new BigInteger("5");     // String -> BigInteger
        BigInteger b = BigInteger.valueOf(7);       // long -> BigInteger
        BigInteger c = a.add(b);                    // add operation

        System.out.println(c.toString());           // toString()

        System.out.println(c.floatValue());         // floatValue()
        System.out.println(c.doubleValue());        // doubleValue()
        System.out.println(c.intValue());           // intValue()
        System.out.println(c.longValue());          // long Value()
    }


    @Test
    public void BigDecimal() {
        // todo
    }
}
