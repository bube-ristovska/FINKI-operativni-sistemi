package Prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    static final int NUM_RUNS = 100;
    static final int NUM_CONSUMERS = 50;

    // TO DO: Define synchronization elements
    public static Semaphore isBufferEmptySemaphore;
    public static Semaphore[] itemsSemaphores;
    public static Lock lock;

    public static void initialization(int numConsumers) {
        // TO DO: Initialize synchronization elements
        isBufferEmptySemaphore = new Semaphore(1); //prazen buffer prvicno zatoa 1 dozvola
        lock = new ReentrantLock();
        itemsSemaphores = new Semaphore[NUM_CONSUMERS];
        for (int i = 0; i < NUM_CONSUMERS; i ++) {
            itemsSemaphores[i] = new Semaphore(0); //ne mozhat da pristapat deka e pochetno prazen
        }
    }




    public static void main(String[] args) {
        initialization(NUM_CONSUMERS);

        Buffer sharedBuffer = new Buffer(NUM_CONSUMERS);
        Producer producer = new Producer(sharedBuffer);
        List<Consumer> consumers = new ArrayList<>();

        producer.start(); //remember producer is a thread

        for (int i = 0; i < NUM_CONSUMERS; i++) {
            Consumer consumer = new Consumer(i, sharedBuffer); //id + buffer
            consumers.add(consumer);
        }

        consumers.forEach(Thread::start);//remember consumers are a thread

    }
}
