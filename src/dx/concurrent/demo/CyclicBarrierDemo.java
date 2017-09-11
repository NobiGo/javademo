package dx.concurrent.demo;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的应用场景
 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的应用场景。比如我们用一个Excel保存了用户所有银行流水，
 * 每个Sheet保存一个帐户近一年的每笔银行流水，现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，
 * 都执行完之后，得到每个sheet的日均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水.
 *
 * 当计数减至0时，阻塞解除，所有在此 CyclicBarrier 上面阻塞的线程开始运行。在这之后，如果再次调用 await() 方法，
 * 计数就又会变成 N-1，新一轮重新开始，这便是 Cyclic 的含义所在。
 * **/
public class CyclicBarrierDemo {

    static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //线程1等待
                    System.out.println("线程1等待");
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();

        try {
            //
            System.out.println("线程2 等待");
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }
}