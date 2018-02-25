package scanner.filter;

import scanner.helper.XMLHelper;
import scanner.mongo.model.MethodInvocationResult;

public class MethodInvocationRestFilter extends AbstractMethodInvocationFilter {


    @Override
    public boolean filter(MethodInvocationResult methodInvocationResult) {
        if (XMLHelper.getSoapClassNameList().contains(methodInvocationResult.getProviderClassName())){
            methodInvocationResult.setServiceType(MethodInvocationResult.WS_SOAP);
            return true;
        }
        return false;
    }
}
