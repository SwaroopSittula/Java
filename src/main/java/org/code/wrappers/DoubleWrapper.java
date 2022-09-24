package org.code.wrappers;

public class DoubleWrapper {

    public static void main(String[] args) {
        //Infinity
        //Not a Number
        doubleInfo();

        //Power Of
        powerOf();
    }

    public static void powerOf(){
        Double a = Double.valueOf(3);
        Double b = Double.valueOf(2);
        Double nan = Double.NaN;

        System.out.println(Math.pow(a,b));
        System.out.println(Math.pow(b,a));
        System.out.println(Math.pow(a, nan));
    }

    public static void doubleInfo(){
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NaN);
    }
}
