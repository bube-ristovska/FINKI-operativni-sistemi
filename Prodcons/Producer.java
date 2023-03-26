package Prodcons;

public class Producer extends Thread {
    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void execute() throws InterruptedException {
        ProducerConsumer.isBufferEmptySemaphore.acquire();

        ProducerConsumer.lock.lock();
        buffer.fillBuffer();
        ProducerConsumer.lock.unlock();

        for (int i = 0; i < ProducerConsumer.NUM_CONSUMERS; i++) {
            ProducerConsumer.itemsSemaphores[i].release();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < ProducerConsumer.NUM_RUNS; i++) {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
