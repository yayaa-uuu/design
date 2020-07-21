package com.yy.design.reflection;

/**
 * @author yy
 * @date 2020/7/2 21:57
 */
public class User {
    private String Id;
    private String name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void hello(){
        System.out.println("hello word---");
    }
    private void hi(){
        System.out.println("hi");
    }
}
