package customnumberbase;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class CustomNumberBase {

    private static final String symbols1 = "a,b,c,ç,d,e,f,g,ğ,h,ı,i,j,k,l,m,n,o,ö,p,r,s,ş,t,u,ü,v,y,z";
    private static String[] symbols2 = symbols1.split(",");

    private static final Integer BASE1 = symbols2.length;
    private static final BigInteger BASE2 = new BigInteger(BASE1.toString());

    public CustomNumberBase(String[] symbols) {
        this.symbols2 = symbols;
    }

    private static int findIndex(String[] array, String symbol) {

        for (int i = 0; i < array.length; i++) {

            if (array[i].equals(symbol)) {
                return i;
            }
        }
        System.out.println("bilinmeyen - unknown sembol");
        System.exit(1);
        return -1;
    }

    public static String fromBase10(BigInteger i) {
        StringBuilder sb = new StringBuilder("");
        if (i.intValueExact() == 0) {
            return symbols2[0];
        }
        while (i.signum() == 1) {
            i = fromBase10(i, sb);
        }
        return sb.toString();
    }

    private static BigInteger fromBase10(BigInteger i, final StringBuilder sb) {
        int remainder = i.remainder(BASE2).intValueExact();
        sb.append(symbols2[remainder]);
        return i.divide(BASE2);

    }

    public static BigInteger toBase10(String str) {
        if (str.contains(",")) {

            String[] symbols = str.split(",");
            Collections.reverse(Arrays.asList(symbols));
            return toBase10(symbols);

        } else {
            return toBase10(new StringBuilder(str).reverse().toString().split(""));
        }
    }

    private static BigInteger toBase10(String[] symbols) {
        BigInteger n = new BigInteger("0");
        for (int i = 0; i< symbols.length; i++) {
            n = n.add(toBase10(findIndex(symbols2, symbols[i]), i));
        }
        return n;
    }

    private static BigInteger toBase10(Integer n, Integer pow) {

        return ((new BigInteger(n.toString())).multiply((BASE2.pow(pow))));
    }
    
}
