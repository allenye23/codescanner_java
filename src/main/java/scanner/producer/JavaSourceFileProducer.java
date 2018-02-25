package scanner.producer;

import scanner.warehouse.JavaFileWarehouse;
import scanner.warehouse.Warehouse;

import java.io.File;

public class JavaSourceFileProducer extends AbstractFileProducer{

    public JavaSourceFileProducer() {
        this.warehouse = new JavaFileWarehouse();
    }



}
