package com.zxf.demo.test;

import java.util.*;

/**
 * @author zxf
 */
public class testMap {
    public testMap (){
        System.out.println("构造");
    }
    public static void main(String []args){
        Map<Integer,String> map = new LinkedHashMap<>(16);
        map.put(1,"aaa");
        map.put(17,"dsd");
        System.out.println(map);

        List<String> list = new LinkedList<>();
        list.add("z");
        list.add("x");
        list.add("f");
        System.out.println(list);
    }
}
