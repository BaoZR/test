package com.bob;

import java.util.ArrayList;
import java.util.List;
/*
    示例存储了一个类引用，稍后又产生了一个list,填充这个list的是类引用newInstance()出来的对象。

    Long.toString(..)将long转化为String
    类引用.newInstance()产生对象
 */
class CountedInteger{
    private static long counter;//默认为0
    private final long id = counter++;//final不可变，也许是初始化时给他的//counter每初始化一次会加1
    public String toString(){
        return Long.toString(id);
    }
}

public class FilledList<T> {//<T>可以保证所有的类型都一样
    private Class<T> type;

    //构造方法 传入的是类引用
    private FilledList(Class<T> type){
        this.type = type;
    }
    private List<T> create(int nElements){
        List<T> result = new ArrayList<T>();
        try{
            for(int i = 0;i < nElements;i++)
                result.add(type.newInstance());//list里面存的都是对象
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args){
        FilledList<CountedInteger> fl = new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(fl.create(15));
    }



}
