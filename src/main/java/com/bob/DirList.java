package com.bob;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
    File（"."）中的点代表根目录
   file.list(FilenameFilter()) 称为回调，是一个策略模式的例子，
   list实现了基本的功能，FilenameFilter接口实现提供了这个策略
   list中有个filter.accept需要从FilenameFilter中获得
 */

public class DirList {
    public static void main(String[] args) {

        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);//ASCII递增排序
        for(String dirItem : list){
            System.out.println(dirItem);
        }
    }
}
class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter (String regex){
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name){
        return pattern.matcher(name).matches();
    }
}