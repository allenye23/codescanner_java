package scanner.analyzer;

import org.eclipse.jdt.core.dom.*;
import scanner.dom.JavaElement;
import scanner.handler.StatementHandler;
import scanner.mongo.model.MethodInvocationResult;

import java.util.*;

public class MethodInvocationAnalyzer implements Analyzer<MethodInvocationResult>{

    private StatementHandler statementHandler;

    public MethodInvocationAnalyzer() {
        this.statementHandler = new StatementHandler();
    }

    @Override
    public List<MethodInvocationResult> analysis(Object obj) {
        JavaElement javaElement = (JavaElement) obj;
        List<MethodInvocationResult> results = new ArrayList<>();
        List<MethodDeclaration> methodDeclarationList = javaElement.getMethodDeclarationList();
        PackageDeclaration packageDeclaration = javaElement.getPackageDeclaration();
        methodDeclarationList.forEach((methodDeclaration -> {
            Map<String, Object> variableMap = new HashMap<>();
            Map<String, List<String>> methodInvocationMap = new HashMap<>();
            try {
                Block block = methodDeclaration.getBody();
                if (javaElement.getClassName().equals("ShipmentHelper")&&methodDeclaration.getName().getIdentifier().equals("checkIsAMRequired")){
                    System.out.println("hahahhahhaha");
                }
                getMethodAndVariableStatement(variableMap, methodInvocationMap, block);
                Set<Map.Entry<String, List<String>>> methodInvocationSet = methodInvocationMap.entrySet();
                Iterator<Map.Entry<String, List<String>>> iterator = methodInvocationSet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, List<String>> methodInvocation = iterator.next();
                    String variableName = methodInvocation.getKey();
                    List<String> methodNameList = methodInvocation.getValue();
                    try {
                        MethodInvocationResult methodInvocationResult = getMethodInvocationResult(javaElement, packageDeclaration, methodDeclaration, variableMap, variableName, methodNameList);
                        results.add(methodInvocationResult);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println(packageDeclaration.getName().getFullyQualifiedName() + "." + javaElement.getClassName() + ":" + methodDeclaration.getName().getIdentifier());
                e.printStackTrace();
            }
        }));
        return results;
    }

    private MethodInvocationResult getMethodInvocationResult(JavaElement javaElement, PackageDeclaration packageDeclaration, MethodDeclaration methodDeclaration, Map<String, Object> variableMap, String variableName, List<String> methodNameList) throws Exception {
        Object className = "";
        MethodInvocationResult methodInvocationResult = new MethodInvocationResult();
        boolean isRestful = false;
        if ((!"".equals(variableName))&&variableName.substring(0, 1).toCharArray()[0]>='A'&&variableName.substring(0, 1).toCharArray()[0]<='Z'){
            className = variableName;
        }else{
            className = javaElement.findClassNameByVariable(variableName, variableMap);
            if (className instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) className;
                Type argumentType = (Type) parameterizedType.typeArguments().get(0);
                if (argumentType instanceof SimpleType){
                    SimpleType simpleType = (SimpleType) argumentType;
                    className = simpleType.getName().getFullyQualifiedName();
                    isRestful = true;
                }
            }
        }
        String fullName;
        if (!JavaElement.UNKNOW.equals(className)) {
            if (className.toString().contains(".")){
                fullName = className.toString();
            }else{
                fullName = javaElement.findPackageNameByClass(className.toString());
            }
        } else {
            fullName = variableName + ": " + JavaElement.UNKNOW;
        }
        if (isRestful){
            methodInvocationResult.setRestDto(fullName);
        }
        methodInvocationResult.setConsumerClassName(javaElement.getClassName());
        methodInvocationResult.setConsumerMethodName(methodDeclaration.getName().getIdentifier());
        methodInvocationResult.setConsumerPackageName(packageDeclaration.getName().getFullyQualifiedName());
        methodInvocationResult.setProviderClassName(fullName);
        methodInvocationResult.setProviderMethodNameList(methodNameList);
        if (javaElement.getModuleName()!=null){
            methodInvocationResult.setModuleName(javaElement.getModuleName().toUpperCase());
        }
        if (javaElement.getDomainName()!=null){
            methodInvocationResult.setDomainName(javaElement.getDomainName().toUpperCase());
        }
        return methodInvocationResult;
    }

    private void analysisMethod(PackageDeclaration packageDeclaration, MethodDeclaration methodDeclaration) {
        
    }

    private void getMethodAndVariableStatement(Map<String, Object> variableMap, Map<String, List<String>> methodInvocationMap, Statement statementParam) {
        List<Statement> allStatement = new ArrayList<>();
        statementHandler.handler(statementParam, allStatement);
        allStatement.forEach((statement -> {
            if (statement instanceof VariableDeclarationStatement) {
                analysisVariableDeclaration(variableMap, methodInvocationMap, (VariableDeclarationStatement) statement);
            }
            if (statement instanceof ExpressionStatement) {
                ExpressionStatement expressionStatement = (ExpressionStatement) statement;
                Expression expression = expressionStatement.getExpression();
                if (expression instanceof Assignment){
                    Assignment assignment = (Assignment) expression;
                    expression = assignment.getRightHandSide();
                }
                getMethodInvocation(methodInvocationMap, expression);
            }
            if (statement instanceof ReturnStatement) {
                ReturnStatement returnStatement = (ReturnStatement) statement;
                Expression expression = returnStatement.getExpression();
                getMethodInvocation(methodInvocationMap, expression);
            }
        }));
    }


    private void analysisVariableDeclaration(Map<String, Object> variableMap, Map<String, List<String>> methodInvocationMap, VariableDeclarationStatement statement) {
        VariableDeclarationStatement variableDeclarationStatement = statement;
        Type type = variableDeclarationStatement.getType();
        Object variableClassName = variableDeclarationStatement.getType().toString();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            variableClassName = parameterizedType.getType().toString();
            if (variableClassName.toString().contains("FWRESTServiceProxy")){
//                Type argumentType = (Type) parameterizedType.typeArguments().get(0);
//                if (argumentType instanceof SimpleType){
//                    SimpleType simpleType = (SimpleType) argumentType;
//                    variableClassName = simpleType.getName().getFullyQualifiedName();
//                }
                variableClassName = parameterizedType;
            }
        }
        VariableDeclarationFragment fragment = (VariableDeclarationFragment) variableDeclarationStatement.fragments().get(0);
        Expression expression = fragment.getInitializer();
        if (expression != null) {
            getMethodInvocation(methodInvocationMap, expression);
        }
        variableMap.put(((VariableDeclarationFragment) variableDeclarationStatement.fragments().get(0)).getName().getIdentifier(), variableClassName);
    }

    private void getMethodInvocation(Map<String, List<String>> methodInvocationMap, Expression expression) {
        if (expression instanceof MethodInvocation) {
            MethodInvocation methodInvocation = (MethodInvocation) expression;
            String methodInvocationVariable = "";
            Expression methodExpress = methodInvocation.getExpression();
            if (methodExpress instanceof SimpleName) {
                SimpleName simpleName = (SimpleName) methodExpress;
                methodInvocationVariable = simpleName.getIdentifier();
            }
            if (methodExpress instanceof FieldAccess) {
                FieldAccess fieldAccess = (FieldAccess) methodExpress;
                methodInvocationVariable = fieldAccess.getName().getIdentifier();
            }
            if (!methodInvocationMap.containsKey(methodInvocationVariable)) {
                methodInvocationMap.put(methodInvocationVariable, new ArrayList<>());
            }
            if (methodExpress instanceof MethodInvocation){
                getMethodInvocation(methodInvocationMap,methodExpress);
            }
            methodInvocationMap.get(methodInvocationVariable).add(methodInvocation.getName().getIdentifier());
        }
    }

   
    
    
}
