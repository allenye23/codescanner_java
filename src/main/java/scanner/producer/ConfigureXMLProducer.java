package scanner.producer;

import scanner.warehouse.XMLWarehouse;

import java.io.File;

public class ConfigureXMLProducer extends AbstractFileProducer{

    public ConfigureXMLProducer() {
        this.warehouse = new XMLWarehouse();
    }

    @Override
    public void produce(File file) {

    }

}
