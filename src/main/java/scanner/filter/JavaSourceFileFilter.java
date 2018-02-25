package scanner.filter;


import java.io.File;

public class JavaSourceFileFilter extends AbstractFileFilter{


    @Override
    public boolean filter(File file) {
        if(file.getName().endsWith("java")&&(!file.getName().endsWith("Test.java"))&&(!file.getName().endsWith("IT.java"))&&(!file.getName().endsWith("Tester.java"))){
            return true;
        }
        return false;
    }
}
