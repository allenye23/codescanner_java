package scanner.warehouse;

public class WarehouseFactory {

    public static Warehouse getJavaElementWarehouse(){
        return JavaElementWarehouse.getJavaElementWarehouse();
    }

    public static Warehouse getJavaFileWarehouse(){
        return JavaFileWarehouse.getJavaFileWarehouse();
    }

    public static Warehouse getXMLWarehouse(){
        return XMLWarehouse.getXMLWarehouse();
    }
}
