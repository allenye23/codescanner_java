package scanner.filter;

import scanner.helper.XMLHelper;
import scanner.mongo.model.MethodInvocationResult;

public class MethodInvocationSOAPFilter extends AbstractMethodInvocationFilter {

    @Override
    public boolean filter(MethodInvocationResult methodInvocationResult) {
        if (XMLHelper.getRestDtoClassNameList().contains(methodInvocationResult.getRestDto())){
            methodInvocationResult.setServiceType(MethodInvocationResult.WS_RESTful);
            methodInvocationResult.setUrl(XMLHelper.getRestfulClassNameMap().get(methodInvocationResult.getProviderClassName()));
            return true;
        }
        return false;
    }
}
