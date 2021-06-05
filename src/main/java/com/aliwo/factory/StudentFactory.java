package com.aliwo.factory;

import com.aliwo.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @author xuyayuan
 * @data 2021年06月05日
 * 写双重检测锁的时候要思考线程安全的问题
 * 通过当前类的getInstance方法可以获取到一个单例的student对象
 * 当前的代码和当前的应用程序没有任何关系
 * 多线程环境下会引发线程安全的问题，因为
 * private Student student; new Student(100, "1111", "100", "1111", "1111", 21); 两者一起引发
 * 是全局性的可修改的成员变量，
 * 当前代码存在线程安全的问题：
 * 一：在方法签名上添加synchronized，使方法变成同步方法
 * 二：在存在线程安全问题的成员变量声明前添加volatile关键字
 * 三：若存在线程安全问题的成员变量为Integer,Long,Boolean等，可以将他们定义为AtomXXX类型的
 */
@Component // 当前类是单例的
public class StudentFactory {

    private  Student student;
    // 第二种线程安全解决
    //private volatile Student student;

    /**
     * 第一种线程安全问题解决方案
     * 在方法签名上添加synchronized，使方法变成同步方法，可以解决线程安全问题
     *
     * @return com.aliwo.entity.Student
     * @author xuyy
     * @date 2021/6/5 11:07
     */
    public synchronized Student getInstance() {
        // 双重检测机制
        if (null == student) {
            synchronized (this) {
                if (null == student) {
                    // 以下的语句的底层由三个步骤构成
                    // 一：申请堆空间m
                    // 二：使用对象的初始化数据初始化堆空间
                    // 三：将student引用指向堆空间
                    student = new Student(100, "1111", "100", "1111", "1111", 21);
                }
            }
        }
        return student;
    }

    /**
     * 第二种线程安全解决方案，在成员变量 private Student student; 改成 private volatile Student student;
     * @author jitwxs
     * @date 2021/6/5 11:08
     * @return com.aliwo.entity.Student
     */
 /*   public Student getInstance() {
        // 双重检测机制
        if (null == student) {
            synchronized (this) {
                if (null == student) {
                    // 以下的语句的底层由三个步骤构成
                    // 一：申请堆空间m
                    // 二：使用对象的初始化数据初始化堆空间
                    // 三：将student引用指向堆空间
                    student = new Student(100, "1111", "100", "1111", "1111", 21);
                }
            }
        }
        return student;*/
}
