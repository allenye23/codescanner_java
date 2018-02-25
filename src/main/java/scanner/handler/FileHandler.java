package scanner.handler;

import scanner.filter.Filter;
import scanner.producer.Producer;

import java.io.File;

public class FileHandler implements Handler<File> {

    private Producer producer;
    private Filter filter;

    public FileHandler(Producer producer, Filter filter) {
        this.producer = producer;
        this.filter = filter;
    }

    @Override
    public void handle(File file) {
        if (filter.filter(file)){
            producer.produce(file);
        }
    }

}
