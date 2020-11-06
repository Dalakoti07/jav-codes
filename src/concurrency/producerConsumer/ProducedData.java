package concurrency.producerConsumer;

public class ProducedData {
    private int idx;
    public ProducedData(int idx){
        this.idx=idx;
    }
    public ProducedData(){

    }

    @Override
    public String toString() {
        return "product";
    }
}
