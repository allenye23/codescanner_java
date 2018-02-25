package scanner.paser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import scanner.dom.JavaElement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JDTPaser implements Paser<JavaElement>{

    private ASTParser astParser;

    public JDTPaser() {
        astParser = ASTParser.newParser(AST.JLS8); //设置Java语言规范版本
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        Map<String, String> compilerOptions = JavaCore.getOptions();
        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8); //设置Java语言版本
        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        astParser.setCompilerOptions(compilerOptions); //设置编译选项
    }

    @Override
    public JavaElement paser(String sourceCode) {
        CompilationUnit compilationUnit = getCompilationUnit(sourceCode);
        JavaElement javaElement = buildJavaElement(compilationUnit);
        if (javaElement != null) return javaElement;
        return null;
    }

    private CompilationUnit getCompilationUnit(String sourceCode) {
        astParser.setSource(sourceCode.toCharArray());
        return (CompilationUnit) astParser.createAST(null);
    }

    private JavaElement buildJavaElement(CompilationUnit compilationUnit) {
        JavaElement javaElement = new JavaElement();
        javaElement.setPackageDeclaration(compilationUnit.getPackage());
        javaElement.setImports(compilationUnit.imports());
        List types = compilationUnit.types();
        if (types.size()>0&&types.get(0) instanceof TypeDeclaration){
            TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
            javaElement.setClassName(typeDec.getName().getIdentifier());
            FieldDeclaration[] fieldDeclaration = typeDec.getFields();
            MethodDeclaration[] methodDeclaration = typeDec.getMethods();
            if (typeDec.getSuperclassType()!=null)
                javaElement.setParent(typeDec.getSuperclassType().toString());
            javaElement.setFieldDeclarationList(Arrays.asList(fieldDeclaration));
            javaElement.setMethodDeclarationList(Arrays.asList(methodDeclaration));
            return javaElement;
        }
        return null;
    }
}
