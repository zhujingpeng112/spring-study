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
