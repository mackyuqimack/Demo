package com.yuqi.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Function: 两个线程交替执行打印 1~100
 *
 * lock 版
 *
 * @author crossoverJie Date: 11/02/2018 10:04
 * @since JDK 1.8
 */
public class Demo {

	private int start = 1;

	/**
	 * 对 flag 的写入虽然加锁保证了线程安全，但读取的时候由于 不是 volatile 所以可能会读取到旧值
	 *
	 */
	private volatile boolean flag = false;

	/**
	 * 重入锁
	 */
	private final static Lock LOCK = new ReentrantLock();

	public static void main(String[] args) {
		Demo twoThread = new Demo();

		Thread t1 = new Thread(new OuNum(twoThread));
		t1.setName("偶数");

		Thread t2 = new Thread(new JiNum(twoThread));
		t2.setName("基数");

		t1.start();
		t2.start();
	}

	/**
	 * 偶数线程
	 */
	public static class OuNum implements Runnable {

		private Demo number;

		public OuNum(Demo number) {
			this.number = number;
		}

		@Override
		public void run() {
			while (number.start <= 1000) {

				if (number.flag) {
					try {
						LOCK.lock();
						System.out.println(Thread.currentThread().getName() + "+-+" + number.start);
						number.start++;
						number.flag = false;

					} finally {
						LOCK.unlock();
					}
				}
			}
		}
	}

	/**
	 * 奇数线程
	 */
	public static class JiNum implements Runnable {

		private Demo number;

		public JiNum(Demo number) {
			this.number = number;
		}

		@Override
		public void run() {
			while (number.start <= 1000) {
				if (!number.flag) {
					try {
						LOCK.lock();
						System.out.println(Thread.currentThread().getName() + "+-+" + number.start);
						number.start++;
						number.flag = true;

					} finally {
						LOCK.unlock();
					}
				}
			}
		}
	}
}