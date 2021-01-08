package com.willing.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        String s = "asb";
        String b = null;
        System.out.println(s + b);
        List<Person> list = new ArrayList<>();
        list.add(new Person("yy", 21));
        list.add(new Person("tom", 22));
        list.add(new Person("jack", 30));
        //list.stream()将集合转换成流
        list.stream()
                //过滤age！=21的数据
                .filter(person -> person.getAge() != 21)
                .map(Person::getName)
                .sorted()//排序
                .collect(Collectors.toList())//将流转化成list
                .forEach(System.out::println);

    }
}
