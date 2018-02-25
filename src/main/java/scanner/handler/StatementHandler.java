package scanner.handler;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class StatementHandler {

    public void handler(Statement statement, List<Statement> resultList){

        if (statement instanceof IfStatement){
            IfStatement ifStatement = (IfStatement) statement;
            handler(ifStatement.getThenStatement(),resultList);
            if (ifStatement.getElseStatement()!=null){
                handler(ifStatement.getElseStatement(),resultList);
            }
        }
        if (statement instanceof TryStatement){
            TryStatement tryStatement = (TryStatement) statement;
            handler(tryStatement.getBody(),resultList);
            for (Object obj:tryStatement.catchClauses()) {
                CatchClause catchClause = (CatchClause) obj;
                handler(catchClause.getBody(),resultList);
            }
        }
        if (statement instanceof ForStatement){
            ForStatement forStatement = (ForStatement) statement;
            handler(forStatement.getBody(),resultList);
        }
        if (statement instanceof EnhancedForStatement){
            EnhancedForStatement enhancedForStatement = (EnhancedForStatement) statement;
            handler(enhancedForStatement.getBody(),resultList);
        }
        if (statement instanceof SwitchStatement){
            SwitchStatement switchStatement = (SwitchStatement) statement;
            for (Object switchStatementObject:switchStatement.statements()){
                Statement switchStatementMenber = (Statement) switchStatementObject;
                handler(switchStatementMenber,resultList);
            }
        }
        if (statement instanceof WhileStatement){
            WhileStatement whileStatement = (WhileStatement) statement;
            handler(whileStatement.getBody(),resultList);
        }
        if (statement instanceof Block){
            Block block = (Block) statement;
            for (Object blockObject:block.statements()){
                Statement blockStatement = (Statement) blockObject;
                handler(blockStatement,resultList);
            }
        }
//        if (statement instanceof ReturnStatement){
//            ReturnStatement returnStatement = (ReturnStatement) statement;
//            Expression expression = returnStatement.getExpression();
//            if (expression instanceof MethodInvocation){
//                MethodInvocation methodInvocation = (MethodInvocation) expression;
//
//            }
//        }
        if (statement instanceof VariableDeclarationStatement||statement instanceof ExpressionStatement||statement instanceof ReturnStatement){
            resultList.add(statement);

        }
    }
}
