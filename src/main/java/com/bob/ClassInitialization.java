package com.bob;

/*
    .class语法获取类的引用（ref）不会触发初始化
    class.forName()会触发初始化
    如果一个static final是编译期常量如Initable.staticFinal那样，不需初始化就可以读取
    如果不是编译期常量如Initable.staticFinal2那样，会触发初始化
 */

import java.util.Random;
class Initable{
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static{
        System.out.println("Initializing Initable");
    }
}

class Initable2{
    static int staticNonFinal =147;
    static{
        System.out.println("Initializing Initable2");
    }
}

class Initable3{
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}



public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception{
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        //Does not trigger initialization;如此引用不会触发初始化
        System.out.println(Initable.staticFinal);

        //Does trigger initialization;
        System.out.println(Initable.staticFinal2);
        //Does trigger initialization;
        System.out.println(Initable2.staticNonFinal);

        Class initable3 = Class.forName("com.bob.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
