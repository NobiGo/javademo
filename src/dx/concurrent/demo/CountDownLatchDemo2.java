package dx.concurrent.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {
    public static void main(String[] args) {
        //一共有10个运动员
        int num = 10;
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(num);

        for (int i = 1; i <= num; i++) {
            new Thread(new Aworker(i, begin, end)).start();
        }

        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("judge say : run !");
        begin.countDown(); //裁判一声令下开始跑
        long startTime = System.nanoTime();
        try {
            end.await(); //等待结束
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            long endTime = System.nanoTime();
            System.out.println("judge say : all arrived !");
            System.out.println("spend time: " + (endTime - startTime));
        }
    }
}




class Aworker implements Runnable {
    private int num;
    private CountDownLatch begin;
    private CountDownLatch end;

    public Aworker(int num, final CountDownLatch begin, final CountDownLatch end) {
        this.num = num;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println(num + "th people is ready");
            begin.await();  //准备就绪
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            end.countDown();  //计数器减一，到达终点
            System.out.println(num + "th people arrive");
        }
    }
}