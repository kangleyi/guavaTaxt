package com.guava.test.component.visit;

import java.util.HashMap;

public class HuaweiHander implements HuaweiHttp{
    HashMap<HuaweiApiEnum,HuaweiHttp> map;

    public enum HuaweiApiEnum{
        OCR,FILE
    }

    public HuaweiHander() {
        map=new HashMap(2);
        map.put(HuaweiApiEnum.OCR,new HuaweiOcrHander());
        map.put(HuaweiApiEnum.FILE,new HuaweiFileHander());
    }

    public HuaweiHttp accept(HuaweiApiEnum huaweiApiEnum){
        return map.get(huaweiApiEnum);
    }

    @Override
    public void accept(HuaweiVisitor visitor) {
        for(HuaweiApiEnum huaweiApiEnum:HuaweiApiEnum.values()){
           HuaweiHttp huaweiHttp=map.get(huaweiApiEnum);
           if(huaweiHttp!=null){
               huaweiHttp.accept(visitor);
           }
        }
        visitor.visit(this);
    }
}
