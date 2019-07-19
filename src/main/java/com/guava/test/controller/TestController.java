package com.guava.test.controller;

import com.guava.common.lang.StringUtils;
import com.guava.test.component.Context;
import com.guava.test.component.baidu.BaiduConfig;
import com.guava.test.component.nuonuo.Observed;
import com.guava.test.component.nuonuo.Observer;
import com.guava.test.component.ocr.BusinessDelegate;
import com.guava.test.component.ocr.BusinessLookUp;
import com.guava.test.component.ocr.InvoiceClient;
import com.guava.test.component.ocr.InvoiceDto;
import com.guava.test.component.visit.*;
import com.guava.test.component.wechat.WechatHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private Observer observer;

    @Autowired
    private BaiduConfig baiduConfig;

    @Autowired
    private WechatHander wechatHander;

    @Autowired
    private Context context;
    /**
     * 方式一：
     * 工具类的方式直接调用
     * 简单直观，提取静态代码块，适合用于抽取公共代码，但篇幅不宜过长
     * @param t
     * @return
     */
    @RequestMapping("/test")
    public Map test(String t){
        HashMap map=new HashMap(1);
        map.put("rst",StringUtils.split(t,"/"));
        return map;
    }

    /**
     * 方式二：
     * 通过将应用作为组件的方式进行调用
     * @return
     */
    @RequestMapping("/test2")
    public Map test2(){
        HashMap map=new HashMap(2);
        map.put("apis",baiduConfig.getApis());
        map.put("serverName",baiduConfig.getServerName());
        return map;
    }

    /**
     * 方式三：
     * 通过观察者方式进行调用，需要提供对象改变时的逻辑，通常用于异步结果返回时的业务处理
     * @return
     */
    @RequestMapping("/test3")
    public Map test3(){
        Observed o=new Observed();
        o.addObserver(observer);
        HashMap hashMap=new HashMap(1);
        hashMap.put("rst","业务处理提交成功！");
        return hashMap;
    }

    /**
     * 方式四：
     * 访问者模式调用业务
     * 需要自己手动传入实现，主要是将数据结构与操作分离
     *  1、对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
     *  2、需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作"污染"这些对象的类，也不希望在增加新操作时修改这些类。
     * @return
     */
    @RequestMapping("/test4")
    public Map test4(){
        HuaweiHander huaweiHander=new HuaweiHander();
        huaweiHander.accept(new HuaweiHttpImpl());
        HuaweiVisitor visitor=new  HuaweiHttpImpl();
        HuaweiFileHander huaweiFileHttp = (HuaweiFileHander)huaweiHander.accept( HuaweiHander.HuaweiApiEnum.FILE);//访问华为文件服务API
        visitor.visit(huaweiFileHttp);
        HuaweiOcrHander huaweiOcrHttp = (HuaweiOcrHander)huaweiHander.accept( HuaweiHander.HuaweiApiEnum.OCR);//访问华为发票识别API
        visitor.visit(huaweiOcrHttp);
        return null;
    }

    /**
     * 方式五：
     * 中介者模式  提取业务调用逻辑，逻辑解偶
     * 主要是抽取业务调用
     * 在中介者模式下，中介者类一般只提供静态方法调用
     * 被调用者作为业务组件加载在容器中
     * @return
     */
    @RequestMapping("/test5")
    public Map test5(){
        HashMap map=new HashMap();
        wechatHander.accept(map);
        return null;
    }

    /**
     * 方式六：
     * 业务代表的方式访问
     * 对表示层和业务层解耦。它基本上是用来减少通信或对表示层代码中的业务层代码的远程查询功能。
     * 本案例采用发票识别为例，先采用业务代表的方式，将第三方调用业务结偶，
     * 然后采用抽象工厂方式将第三方调用的返回处理业务组装重新构建实现，来二次构建组装对象，保证同一返回。
     *
     * @return
     */
    @RequestMapping("/test6")
    public Map test6(){
        BusinessDelegate businessDelegate = new BusinessDelegate();//创建业务代表
        businessDelegate.setServiceType(BusinessLookUp.BusinessEnum.BAIDU);
        InvoiceClient client = new InvoiceClient(businessDelegate);

        InvoiceDto invoiceDto=client.doTask(new HashMap());
        if(invoiceDto==null){
            //如果百度发票识别失败
            businessDelegate.setServiceType(BusinessLookUp.BusinessEnum.HUAWEI);
            invoiceDto=client.doTask(new HashMap());
        }
        HashMap map=new HashMap();
        map.put("data",invoiceDto);
        return map;
    }

    /**
     * 方式七：
     * 策略模式
     * 一个类的行为或其算法可以在运行时更改。
     * 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
     * 黑盒模式，不需要关注实现，只用传递具体参数即可
     * @return
     */
    @RequestMapping("/test7")
    public Map test7(){
        double a=1.0;
        double b=2.0;
        double rst = context.executeStrategy(Context.STRATEGYENUM.DEFAULTENUM, Context.OPERATEENUM.ADD,a,b);
        return null;
    }
}
