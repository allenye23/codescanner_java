package scanner.warehouse;

public class XMLWarehouse extends AbstractWarehouse<String>{

    private static XMLWarehouse xmlWarehouse;

    public static XMLWarehouse getXMLWarehouse() {
        if (xmlWarehouse ==null){
            synchronized (XMLWarehouse.class){
                if (xmlWarehouse ==null){
                    xmlWarehouse = new XMLWarehouse();
                }
            }
        }
        return xmlWarehouse;
    }
}
