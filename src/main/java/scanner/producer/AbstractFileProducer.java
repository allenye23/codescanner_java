package scanner.producer;

import scanner.warehouse.Warehouse;

import java.io.File;

public abstract class AbstractFileProducer implements Producer<File>{
    public final static String END_FILE = "end file";
    protected Warehouse warehouse;

    @Override
    public void produce(File file) {
        if (file==null){
            warehouse.add(END_FILE);
            return;
        }
        if (!file.exists())
            return;
        warehouse.add(file.getAbsolutePath());

    }
}
