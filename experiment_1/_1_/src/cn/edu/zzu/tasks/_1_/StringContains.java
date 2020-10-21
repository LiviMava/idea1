package cn.edu.zzu.tasks._1_;

public class StringContains {
    public static void main(String[] args) {
        String a = "avaicndljdabcfdaldfdfdabcaff";
        String b = "abc";
        if(a.contains(b)){
            System.out.println("包含");
        }else{
            System.out.println("不包含");
        }
    }
}
