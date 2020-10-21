package cn.edu.zzu.tasks._4_;

import java.util.HashSet;

public class ConflictHashCodeTest {
    public static void main(String[] args) {
        Person p1 = new Person(100, "eee");
        Person p2 = new Person(100, "eee");
        Person p3 = new Person(200, "小绿");
        Person p4 = new Person(100, "EEE");
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        System.out.printf("会创建“类对应的散列表”\n" + "p1 = ('eee', 100)\n"
                +"p2 = ('eee', 100)\n"+"p3 = ('aaa', 200)\n"+"p4 = ('EEE', 100)\n");
        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 比较p1 和 p4， 并打印它们的hashCode()
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);
    }
}
