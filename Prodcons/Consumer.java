package Prodcons;

public class Consumer extends Thread{
    Buffer buffer;
    int consumerId;

    public Consumer(int consumerId, Buffer buffer) {
        this.buffer = buffer;
        this.consumerId = consumerId;
    }

    public void execute() throws InterruptedException {
        ProducerConsumer.itemsSemaphores[consumerId].acquire();
        buffer.getItem(consumerId);

        ProducerConsumer.lock.lock();
        buffer.decrementNumberOfItemsLeft();
        if(buffer.isBufferEmpty()){ //ako e prazen da
            ProducerConsumer.isBufferEmptySemaphore.release();
        }
        ProducerConsumer.lock.unlock();
    }

    @Override
    public void run() {
        for (int i = 0; i < ProducerConsumer.NUM_RUNS; i++) {
            try {
                execute(); //let's try it for a number of runs
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
