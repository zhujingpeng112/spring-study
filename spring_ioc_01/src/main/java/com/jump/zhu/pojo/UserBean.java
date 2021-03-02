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
