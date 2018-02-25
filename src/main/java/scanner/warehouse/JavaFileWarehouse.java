package scanner.warehouse;

public class JavaFileWarehouse extends AbstractWarehouse<String>{
    
    private static JavaFileWarehouse javaFileWarehouse;

    public static JavaFileWarehouse getJavaFileWarehouse() {
        if (javaFileWarehouse ==null){
            synchronized (JavaFileWarehouse.class){
                if (javaFileWarehouse ==null){
                    javaFileWarehouse = new JavaFileWarehouse();
                }
            }
        }
        return javaFileWarehouse;
    }
}
