package com.yy.design.deadlock;

/**
 * @author yy
 * @date 2020/7/5 12:11
 */
public class DeadlockTest {
    //两个共享变量;根据加锁的位置看是否需要将变量设置为可见性的。
    private static String sharedA="1";
    private static String sharedB="2";
    public static void main(String[] args) {
        Thread threadA=new Thread(() -> {
            //锁内读写，其他线程的缓存会失效，进入snc时线程就会去拿监视锁把sharedA锁住，独占锁。
            synchronized (sharedA){
                try {
                    System.out.println(Thread.currentThread().getName()+"已经获取到sharedA的锁---"+"---sharedA="+sharedA);
                    long start = System.currentTimeMillis();
                    //拿到sharedA锁后让线程睡眠，不让线程立即去获取sharedB的锁
                    Thread.sleep(0);
                    System.out.println("执行睡眠时间"+(start-System.currentTimeMillis()));
                    System.out.println(Thread.currentThread().getName()+"尝试获取sharedB的锁");
                    synchronized (sharedB){
                        System.out.println("已经获取到B的锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        },"线程A");
        Thread threadB=new Thread(() -> {
            //造成一个互斥的实现死锁。
            synchronized (sharedB){
                try {
                    System.out.println(Thread.currentThread().getName()+"已经获取到sharedB的锁---"+"---sharedB="+sharedB);
                    Thread.sleep(0);
                    System.out.println(Thread.currentThread().getName()+"尝试获取sharedA的锁");
                    synchronized (sharedA){
                        System.out.println();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        },"线程B");
        threadA.start();
        threadB.start();
    }

}
