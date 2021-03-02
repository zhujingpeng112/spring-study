# Spring5框架

spring官网https://spring.io/

Spring jar包地址 https://repo.spring.io/libs-release-local/org/springframework/spring/

SpringFramework框架

> IoC(DI)
>
> AOP

## 一、IoC控制反转

IoC( Inversion of Controller )翻译过来叫‘**控制反转**’

IoC本质上是一个概念，是一种思想，控制反转就是对对象控制权的转移，SpringIoC容器创建对象，然后讲对象的使用权交出去。

依赖

```xml
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.2.13.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.2.13.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.2.13.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.7.0</version>
            <scope>test</scope>
        </dependency>
```



### 1.使用方法

#### 1.xml

xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="userBean" class="com.jump.zhu.pojo.UserBean"/>
    <!--  id属性表示 名字为 user1,user2,user3   id唯一       name属性表示名字为  u1或u2或u3  会根据  逗号 分号 空格  分割 -->
    <bean id="user1,user2,user3" name="u1,u2,u3" class="com.jump.zhu.pojo.UserBean" primary="true"/>
    <!--  可根据 id name 类型 三种方式 来获取bean   primary="true"表示多个形同的bean是优先被获取-->
    
    <!-- 静态工厂   直接获取对象-->
    <bean name="user" class="com.jump.zhu.factory.StaticFactoryDemo" factory-method="getInstance"/>

    <!-- 动态工厂 -->
    <bean id="dynamicFactoryDemo" class="com.jump.zhu.factory.DynamicFactoryDemo"/>
    <!-- 从工厂中获取对象 -->
    <bean id="user9" factory-bean="dynamicFactoryDemo" factory-method="getInstance"/>

    <!--注入-->
    <bean class="com.jump.zhu.pojo.UserBean" name="bean">
        <constructor-arg name="id" value="1"/>
        <constructor-arg name="name" value="jump1"/>
    </bean>

    <bean class="com.jump.zhu.pojo.UserBean" name="bean2">
        <constructor-arg index="0" value="2"/>
        <constructor-arg index="1" value="jump2"/>
    </bean>

    <bean class="com.jump.zhu.pojo.UserBean" name="bean3">
        <property name="id" value="3"/>
        <property name="name" value="jump3"/>
    </bean>
    

</beans>
```

简化注入  

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean class="com.jump.zhu.pojo.UserBean" id="bean" c:_0="1" c:_1="jump1"/>
    <bean class="com.jump.zhu.pojo.UserBean" id="bean2" c:id="2" c:name="jump2"/>
    <bean class="com.jump.zhu.pojo.UserBean" id="bean2" p:id="3" p:name="jump3"/>
</beans>
```

其他注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean class="com.jump.zhu.pojo.Cat" id="cat" c:_0="huahua" c:_1="red"/>

    <bean name="user" class="com.jump.zhu.pojo.UserBean">
        <!-- 设值注入 -->
        <property name="cat" ref="cat">
            <!--<bean class="com.jump.zhu.pojo.Cat"/>-->
        </property>
        <property name="favorites">
            <array>
                <value>篮球</value>
                <value>爬山</value>
            </array>
        </property>
        <property name="cats">
            <list>
                <ref bean="cat"/>
                <bean class="com.jump.zhu.pojo.Cat" id="cat" c:_0="ruru" c:_1="yellow"/>
            </list>
        </property>
        <property name="map">
            <map>
                <entry key="name" value="zhangsan"/>
                <entry key="aa" value="lisi"/>
            </map>
        </property>

        <property name="prop">
            <props>
                <prop key="name">zhangsan</prop>
                <prop key="aa">lisi</prop>
            </props>
        </property>

    </bean>

</beans>
```

bean

```java
package com.jump.zhu.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserBean {

    public Integer id;

    public String name;

    private Cat cat;

    private String[] favorites;

    private List<Cat> cats;

    private Map<String,Object> map;

    private Properties prop;


    public void say(){
        System.out.println("猪");
    }

    public UserBean() {
        //System.out.println("UserBean 无参构造器");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }


    public UserBean(Integer id, String name, Cat cat, String[] favorites, List<Cat> cats, Map<String, Object> map, Properties prop) {
        this.id = id;
        this.name = name;
        this.cat = cat;
        this.favorites = favorites;
        this.cats = cats;
        this.map = map;
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat=" + cat +
                ", favorites=" + Arrays.toString(favorites) +
                ", cats=" + cats +
                ", map=" + map +
                ", prop=" + prop +
                '}';
    }
}

```

```java
package com.jump.zhu.pojo;

public class Cat {


    public String nick;

    public String color;

    public Cat() {
    }

    public Cat(String nick, String color) {
        this.nick = nick;
        this.color = color;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "nick='" + nick + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

```







静态工厂

```java
package com.jump.zhu.factory;

import com.jump.zhu.pojo.UserBean;

import java.util.HashMap;
import java.util.Map;

public class StaticFactoryDemo {
    /**
     * 静态工厂
     * @return com.jump.zhu.pojo.UserBean
     * @author jump.zhu
     * @date   2021/3/2 23:00
    */
    private static UserBean getInstance(){
        return new UserBean();
    }
}

```



动态工厂

```java
public class DynamicFactoryDemo {

    public UserBean getInstance(){
        return new UserBean();
    }

}
```





测试类

```java
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
```





#### 2.注解



### 2.BeanFactory和ApplicationContext的区别

从类图结构中我们看出ApplicationContext具有BeanFactory的所有功能，同事扩展了很多BeanFactory不具备的功能【事件广播，资源加载，web支持等等】











