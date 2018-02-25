package scanner.warehouse;

import scanner.dom.JavaElement;

public class JavaElementWarehouse extends AbstractWarehouse<JavaElement>{

   private static JavaElementWarehouse javaElementWarehouse;

    public static JavaElementWarehouse getJavaElementWarehouse() {
        if (javaElementWarehouse==null){
            synchronized (JavaElementWarehouse.class){
                if (javaElementWarehouse==null){
                    javaElementWarehouse = new JavaElementWarehouse();
                }
            }
        }
        return javaElementWarehouse;
    }
}
