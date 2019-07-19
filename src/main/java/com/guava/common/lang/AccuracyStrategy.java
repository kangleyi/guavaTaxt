package com.guava.common.lang;

import java.math.BigDecimal;

public class AccuracyStrategy implements Strategy{
    @Override
    public double add(double a, double b) {
        BigDecimal bigDecimal1=new BigDecimal(a+"");
        BigDecimal bigDecimal2=new BigDecimal(b+"");
        return bigDecimal1.add(bigDecimal2).setScale(BigDecimal.ROUND_HALF_UP,2).doubleValue();
    }

    @Override
    public double substract(double a, double b) {
        BigDecimal bigDecimal1=new BigDecimal(a+"");
        BigDecimal bigDecimal2=new BigDecimal(b+"");
        return bigDecimal1.subtract(bigDecimal2).setScale(BigDecimal.ROUND_HALF_UP,2).doubleValue();
    }

    @Override
    public double multiply(double a, double b) {
        BigDecimal bigDecimal1=new BigDecimal(a+"");
        BigDecimal bigDecimal2=new BigDecimal(b+"");
        return bigDecimal1.multiply(bigDecimal2).setScale(BigDecimal.ROUND_HALF_UP,2).doubleValue();
    }
}
