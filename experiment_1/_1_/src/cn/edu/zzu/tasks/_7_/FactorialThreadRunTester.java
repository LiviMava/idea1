package cn.edu.zzu.tasks._7_;

class FactorialThread implements Runnable {
    private int num;
    public FactorialThread(int num) {
        this.num = num;
    }

    public void run(){
        int i = num;
        int result = 1;
        System.out.println("new thread started" );
        while(i > 0){
            result = result * i;
            i = i - 1;
        }
        System.out.println("The factorial of "+num+" is "+result);
        System.out.println("new thread ends");
    }
}
public class FactorialThreadRunTester {
    public static void main(String[] args) {
        System.out.println("main thread starts");
        FactorialThread thread1 = new FactorialThread(5);
        Thread t = new Thread(thread1);
        t.start();    //将自动进入run()方法
        System.out.println("main thread ends " );
    }
}
