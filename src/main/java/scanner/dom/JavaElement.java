package scanner.dom;

import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaElement {

    public final static String  UNKNOW = "unknow";
    private String domainName;
    private String moduleName;
    private String className;
    private PackageDeclaration packageDeclaration;
    private String parent;
    private List imports;
    private List<FieldDeclaration> fieldDeclarationList;
    private List<MethodDeclaration> methodDeclarationList;
    private HashMap<String, String> importMap;
    private HashMap<String, String> fieldMap;

    public PackageDeclaration getPackageDeclaration() {
        return packageDeclaration;
    }

    public void setPackageDeclaration(PackageDeclaration packageDeclaration) {
        this.packageDeclaration = packageDeclaration;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List getImports() {
        return imports;
    }

    public void setImports(List imports) {
        this.imports = imports;
        buildImportMap();
    }

    public List<FieldDeclaration> getFieldDeclarationList() {
        return fieldDeclarationList;
    }

    public void setFieldDeclarationList(List<FieldDeclaration> fieldDeclarationList) {
        this.fieldDeclarationList = fieldDeclarationList;
        buildFieldMap();
    }

    public List<MethodDeclaration> getMethodDeclarationList() {
        return methodDeclarationList;
    }

    public void setMethodDeclarationList(List<MethodDeclaration> methodDeclarationList) {
        this.methodDeclarationList = methodDeclarationList;
    }

    @Override
    public String toString() {
        return "JavaClassElement{" +
                "packageDeclaration=" + packageDeclaration +
                ", parent='" + parent + '\'' +
                ", imports=" + imports +
                ", fieldDeclarationList=" + fieldDeclarationList +
                ", methodDeclarationList=" + methodDeclarationList +
                '}';
    }

    private void buildImportMap(){
        importMap = new HashMap<>();
        imports.forEach(obj->{
            ImportDeclaration importDeclaration = (ImportDeclaration) obj;
            String fullNam = importDeclaration.getName().getFullyQualifiedName();
            String[] packnames = fullNam.split("\\.");
            String className = packnames[packnames.length-1];
            importMap.put(className,fullNam);
        });
    }

    private void buildFieldMap() {
        fieldMap = new HashMap<>();
        fieldDeclarationList.forEach((fieldDeclaration -> {
            fieldMap.put(((VariableDeclarationFragment)fieldDeclaration.fragments().get(0)).getName().getIdentifier(),fieldDeclaration.getType().toString());
        }));
    }

    public String findPackageNameByClass(String className) throws Exception {
        if (importMap.containsKey(className)){
            return importMap.get(className);
        }
       return UNKNOW;
    }

    public Object findClassNameByVariable(String variable,Map<String,Object> variableMap) throws Exception {
        if (variableMap.containsKey(variable)){
            return variableMap.get(variable);
        }
        if (fieldMap.containsKey(variable)){
            return fieldMap.get(variable);
        }
        return UNKNOW;
    }

}
