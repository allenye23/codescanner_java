package scanner.warehouse;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractWarehouse<T> implements Warehouse<T>{

    private ConcurrentLinkedQueue<T> warehouse;

    public AbstractWarehouse() {
        warehouse = new ConcurrentLinkedQueue<>();
    }

    public void add(T t){
        warehouse.add(t);
    }

    public T poll(){
        return warehouse.poll();
    }

    public int getCount(){
        return warehouse.size();
    }
}
