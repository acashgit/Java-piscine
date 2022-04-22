package ex02;

public class Launch {
    private int size;
    private int count;
    private int distance;
    private int index = 0;
    private int[] array;
    private int threadSum = 0;

    public Launch(int size, int count, int distance, int[] array){
        this.array = array;
        this.count = count;
        this.distance = distance;
        this.size = size;
    }

    public void starting() throws InterruptedException {
        Threads[] array = new Threads[this.count];
        for (int i = 0; i < count; i++){
            if (distance > size - index)
                distance = size - index;
            array[i] = new Threads(i, distance, index, this.array);
            index += distance;
        }
        for (int i = 0; i < count; i++){
            array[i].start();
            array[i].join();
        }
        Calculator calculator = new Calculator();
        calculator.getResult();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[] getArray() {
        return array;
    }
}
