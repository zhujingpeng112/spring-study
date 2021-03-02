package com.jump.zhu.factory;

import com.jump.zhu.pojo.UserBean;

import java.util.HashMap;
import java.util.Map;

public class StaticFactoryDemo {

    public static Map<String,Object> hashMap;

    static {
        hashMap = new HashMap<String, Object>();
        hashMap.put("a",new UserBean());
        hashMap.put("a1",new UserBean());
        hashMap.put("a2",new UserBean());
    }




    /**
     * 静态工厂
     * @return com.jump.zhu.pojo.UserBean
     * @author jump.zhu
     * @date   2021/3/2 23:00
    */
    private static UserBean getInstance(){
        return new UserBean();
    }

    private static UserBean getInstance(String name){
        return (UserBean) hashMap.get(name);
    }

}
