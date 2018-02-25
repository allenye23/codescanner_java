package scanner.warehouse;

public interface Warehouse<T> {

    void add(T t);

    T poll();

    int getCount();
}
