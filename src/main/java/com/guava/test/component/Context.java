package com.guava.test.component;

import com.guava.common.lang.AccuracyStrategy;
import com.guava.common.lang.DefaultStrategy;
import com.guava.common.lang.Strategy;
import org.springframework.stereotype.Component;

@Component
public class Context {
    private Strategy DEFAULTENUM;

    private Strategy ACCURACYSTRATEGY;
    public enum STRATEGYENUM{
        DEFAULTENUM,ACCURACYSTRATEGY
    }
    public enum OPERATEENUM{
        ADD,SUBSTRACT,MULTIPLY
    }

    public Strategy getDEFAULTENUM() {
        if(DEFAULTENUM==null){
            synchronized (this){
                if(DEFAULTENUM==null){
                    DEFAULTENUM=new DefaultStrategy();
                }
            }
        }
        return DEFAULTENUM;
    }

    public Strategy getACCURACYSTRATEGY() {
        if(ACCURACYSTRATEGY==null){
            synchronized (this){
                if(ACCURACYSTRATEGY==null){
                    ACCURACYSTRATEGY=new AccuracyStrategy();
                }
            }
        }
        return DEFAULTENUM;
    }

    public double executeStrategy(STRATEGYENUM strategyenum,OPERATEENUM operateenum,double num1, double num2){
        Strategy strategy=null;
        switch (strategyenum){
            case DEFAULTENUM:strategy=getDEFAULTENUM();break;
            case ACCURACYSTRATEGY:strategy=getACCURACYSTRATEGY();break;
            default:;
        }
        double rst=0.0;
        if(strategy==null)
            return 0.0;
        switch (operateenum){
            case ADD:return strategy.add(num1,num2);
            case SUBSTRACT:return strategy.substract(num1,num2);
            case MULTIPLY:return strategy.multiply(num1,num2);
            default:return 0.0;
        }
    }
}
