package dx.hashmap.com;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Test extends Thread {
    //静态HashMap对象（线程共享资源）
    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);
    //静态对象
    static AtomicInteger at = new AtomicInteger();
    public void run() {
        while (at.get() < 100000000) {
            map.put(at.get(), at.get());
            at.incrementAndGet();
        }
    }
}