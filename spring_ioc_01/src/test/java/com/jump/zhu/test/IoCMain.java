package com.jump.zhu.test;

import com.jump.zhu.pojo.UserBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IoCMain {

    @Test
    public void fun1(){
        UserBean userBean = new UserBean();
        userBean.say();
    }
    /**
     * 默认在IoC容器初始化的时候实例对象
    */
    @Test
    public void fun2(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean("userBean");
    }
/**
 * 根据 id
*/
    @Test
    public void fun3(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean("user1,user2,user3");
    }
    /**
     * 根据 name
     */
    @Test
    public void fun4(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean("u1");//u2,u3
    }

    /**
     * 根据 class
     * 有多个时会报错    NoUniqueBeanDefinitionException
     *      1.可通过name跟class的方式组合查找
     *      2.xml中配置  primary="true"  表示多个形同的bean是优先被获取
     */
    @Test
    public void fun5(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean(UserBean.class);
        //a.getBean("u1",UserBean.class);
    }

    /**
     * 默认在IoC容器初始化的时候不实例对象
     * 在获取的时候实例对象
     */
    @Test
    public void fun6(){
        BeanFactory a = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        a.getBean("userBean");
    }

    /**
     * today
     * @params No such property: code for class: Script1
     * @return void
     * @author jump.zhu
     * @date   2021/3/2 23:05
     */
    @Test
    public void fun7(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean("user");
        System.out.println("----------------------------");
        a.getBean("user");
    }


    /**
     * today
     * @params No such property: code for class: Script1
     * @return void
     * @author jump.zhu
     * @date   2021/3/2 23:05
     */
    @Test
    public void fun8(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        a.getBean("user9");
        System.out.println("----------------------------");
        a.getBean("user9");
    }


    @Test
    public void fun9(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(a.getBean("bean").toString());
        System.out.println(a.getBean("bean2").toString());
        System.out.println(a.getBean("bean3").toString());
    }

    @Test
    public void easy(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext-IoC-easy.xml");
        System.out.println(a.getBean("bean").toString());
        System.out.println(a.getBean("bean2").toString());
        System.out.println(a.getBean("bean3").toString());
    }

    @Test
    public void other(){
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext-IoC-other.xml");
        System.out.println(a.getBean("user").toString());
    }



}
