package com.willing.reflection;

/**
 * @author yy
 * @date 2020/7/31 16:48
 */
public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("全限定名 : "+cc.getName()+cc.isInterface());
        System.out.println("简称 : "+cc.getSimpleName());
        System.out.println("标准名称 : "+cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class aClass=null;
        try {
            aClass=Class.forName("com.willing.reflection.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(aClass);
        for (Class face:aClass.getInterfaces())
            printInfo(face);
        Class up=aClass.getSuperclass();
        Object o=null;
        try {
            o=up.newInstance();
        } catch (IllegalAccessException e) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (InstantiationException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(o.getClass());

    }
}

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
//    Toy() {
//    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots {
    FancyToy(){
        super(1);
    }

}
