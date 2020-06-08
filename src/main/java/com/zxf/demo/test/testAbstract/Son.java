package com.zxf.demo.test.testAbstract;

/**
 * @author zxf
 */
public class Son extends Father {

    /**
     * Class 'Son' must either be declared abstract or implement abstract method 'say()' in 'GrandFather'
     * Class 'Son' must either be declared abstract or implement abstract method 'eat()' in 'Father'
     * */
    @Override
    public String say(){
        this.age=111;
        return null;
    }

    @Override
    String eat() {
        System.out.println("son eat");
        return null;
    }

    public static void main(String[] args){
        Son son = new Son();
        son.age=1111;
        son.name="dsds";
        son.say();
        son.eat();
        System.out.println(son.age);
    }
}
