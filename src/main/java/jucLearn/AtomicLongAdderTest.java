package jucLearn;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AtomicLongAdderTest {
    private static final int PER_COUNT = 1000000;
    private static final int THREAD_COUNT = 100;

    private long count;

    private synchronized void add() {
        count++;
    }

    private AtomicLong atomicLong = new AtomicLong();

    private void addAtomicLong() {
        atomicLong.addAndGet(1);
    }

    private LongAdder longAdder = new LongAdder();

    private void addLongAdder() {
        longAdder.increment();
    }

    private LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    private void addLongAccumulator() {
        longAccumulator.accumulate(1);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicLongAdderTest atomicLongAdderTest = new AtomicLongAdderTest();
        CountDownLatch countDownLatch1 = new CountDownLatch(THREAD_COUNT);
        CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_COUNT);
        CountDownLatch countDownLatch3 = new CountDownLatch(THREAD_COUNT);
        CountDownLatch countDownLatch4 = new CountDownLatch(THREAD_COUNT);

        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < PER_COUNT; j++) {
                        atomicLongAdderTest.add();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }).start();
        }
        countDownLatch1.await();
        end = System.currentTimeMillis();
        System.out.printf("synchronized spends %d ms\n", end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < PER_COUNT; j++) {
                        atomicLongAdderTest.addAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }).start();
        }
        countDownLatch2.await();
        end = System.currentTimeMillis();
        System.out.printf("addAtomicLong spends %d ms\n", end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < PER_COUNT; j++) {
                        atomicLongAdderTest.addLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }).start();
        }
        countDownLatch3.await();
        end = System.currentTimeMillis();
        System.out.printf("addLongAdder spends %d ms\n", end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < PER_COUNT; j++) {
                        atomicLongAdderTest.addLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }).start();
        }
        countDownLatch4.await();
        end = System.currentTimeMillis();
        System.out.printf("addLongAccumulator spends %d ms\n", end - start);
    }
}
