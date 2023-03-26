package Prodcons;

public class Buffer {
    int numItems = 0;
    final int numConsumers;

    public Buffer(int numConsumers) {
        this.numConsumers = numConsumers;
    }

    public int getBufferCapacity() {
        return numConsumers;
    }

    public void fillBuffer(){
        if(numItems == 0){
            numItems = numConsumers;
            System.out.println("The buffer is full.");
        }
        else System.out.println("ERROR - The buffer is not empty!!");
    }

    public void decrementNumberOfItemsLeft(){
        if (numItems <= 0) {
            throw new RuntimeException("Can't get item, no items left in the buffer!");
        }
        numItems--;
    }

    public boolean isBufferEmpty() {
        return numItems == 0;
    }

    public void getItem(int consumerId) {
        System.out.println(String.format("Get item for consumer with id: %d.", consumerId));
    }

}
