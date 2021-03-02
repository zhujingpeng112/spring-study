package com.jump.zhu.factory;

import com.jump.zhu.pojo.UserBean;

public class DynamicFactoryDemo {



    public UserBean getInstance(){
        return new UserBean();
    }

}
