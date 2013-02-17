package models;

import java.math.*;
import static java.math.BigInteger.*;

public class Factor {
    static long N;

    static public BigInteger factorial(int n) {
        if (n < 2) {
            return ONE;
        }
        BigInteger p = ONE, r = ONE;
        N = 1;
        int log2n = 31 - Integer.numberOfLeadingZeros(n);
        int h = 0, shift = 0, high = 1;
        while (h != n) {
            shift += h;
            h = n >>> log2n--;
            int len = high;
            high = (h & 1) == 1 ? h : h - 1;
            len = (high - len) >> 1;
            if (len > 0) {
                r = r.multiply((p = p.multiply(bp(len))));
            }
        }
        return r.shiftLeft(shift);
    }

    static BigInteger bp(int n) {
        int m = n >> 1;
        if (m == 0) {
            return valueOf(N += 2);
        } else if (n == 2) {
            return valueOf(N += 2).multiply(valueOf(N += 2));
        }
        return bp(n - m).multiply(bp(m));
    }
}
