package com.tujia.dto;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @author xiaopengw
 * @date 2018/7/27
 * @remark
 */
public class Animal {
    private String color;
    private int age;
    private double amount;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("同步代码块");
    }

    public Animal() {
        System.out.println("构造方法");
    }

    public Animal(String color, int age) {
        System.out.println("构造方法");
        this.color = color;
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "color='" + color + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Animal().getAmount());
        System.out.println(0 == 0.0D);
        System.out.println(new BigDecimal(125.125D).multiply(new BigDecimal(100)).intValue());
        //System.out.println(new Double(125.125D));
        System.out.println(new Double(0.0).isNaN());
    }
}
/**
 从Java EE 5规范开始，Servlet中增加了两个影响Servlet生命周期的注解（Annotion）；@PostConstruct和@PreDestroy。
 这两个注解被用来修饰一个非静态的void()方法 。写法有如下两种方式：

 @PostConstruct
 Public void someMethod() {}
 或者
 public @PostConstruct void someMethod(){}

 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
 PreDestroy（）方法在destroy()方法执行执行之后执行


 被注解的Servlet生命周期
 需要注意的是，注解会多多少少地影响到服务器的启动速度。服务器在启动时候会遍历Web 应用的WEB-INF/classes下的所有class文件与WEB-INF/lib下的所有jar文件,以检查哪些类使用了注解。
 如果应用程序中没有 使用任何注解，可以在Web.xml中设置的metadata-complete属性为true.
 (支持@PostConstruct和 @PreDestroy的服务器需要支持Servlet2.5规范。 Tomcat5.x仅支持Servlet2.4规范。)

 我现在要说的是用实例说明它有什么作用。
 比如说我有一种情况，在我的servlet初始化加载之前我想处理一些东西，像加载缓存等等。
 怎么做。@PostConstruct就派上用场了。那为什么这玩意用的不多呢，这是因为如果初始化之前我们要加载或处理某些玩意完全可以在构造器初始化时就处理了，但这种方法需要自己重写构造器。

 */