package cn.edu.zzu.tasks._7_;

public class ProducerAndConsumerSyMethod {
    public static void main(String[] args) {
        Tickets t = new Tickets(10); //建立票对象，票总数10
        new Consumer1(t).start();  //开始卖票线程
        new Producer1(t).start();   //开始存票线程
    }
}
class Consumer1 extends Thread
{
    Tickets t=null;
    public Consumer1(Tickets t) {
        this.t=t;
    }
    public void run() {
        //如果售票数小于限定总量，则不断售票
        while(t.i<t.size)
            t.sell();
    }
}
class Producer1 extends Thread
{
    Tickets t=null;
    public Producer1(Tickets t)
    {	this.t=t;	}
    public void run()
    {
        //如果存票数小于限定总量，则不断存入票
        while(t.number<t.size)
            t.put();
    }
}
class Tickets {
    int size;  //票总数
    int number=0;  //存票序号
    int i=0;   //售票序号
    boolean available=false;  //是否有待售的票
    public Tickets(int size) {
        this.size=size;
    }
    public synchronized void put() {  //同步方法，实现存票的功能
        System.out.println("Producer puts ticket "+(++number));
        available=true;
    }
    public synchronized void sell() {  //同步方法，实现售票的功能
        if(available==true && i<=number)
            System.out.println("Consumer buys ticket "+(++i));
        if(i==number)
            available=false;
    }
}