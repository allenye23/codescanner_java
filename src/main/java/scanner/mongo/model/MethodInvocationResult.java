package scanner.mongo.model;

import java.util.List;

public class MethodInvocationResult {

    public final  static String WS_SOAP = "WS - SOAP";
    public final  static String WS_RESTful = "WS - RESTful";

    private String domainName;
    private String moduleName;
    private String consumerClassName;
    private String consumerPackageName;
    private String providerPackageName;
    private String providerClassName;
    private String consumerMethodName;
    private String serviceType;
    private String url;
    private String restDto;
    private List<String> providerMethodNameList;

    public MethodInvocationResult() {
    }

    public MethodInvocationResult(String consumerClassName, String consumerPackageName, String providerPackageName, String providerClassName, String consumerMethodName, String serviceType, String url, List<String> providerMethodNameList) {
        this.consumerClassName = consumerClassName;
        this.consumerPackageName = consumerPackageName;
        this.providerPackageName = providerPackageName;
        this.providerClassName = providerClassName;
        this.consumerMethodName = consumerMethodName;
        this.serviceType = serviceType;
        this.url = url;
        this.providerMethodNameList = providerMethodNameList;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConsumerPackageName() {
        return consumerPackageName;
    }

    public void setConsumerPackageName(String consumerPackageName) {
        this.consumerPackageName = consumerPackageName;
    }

    public String getProviderPackageName() {
        return providerPackageName;
    }

    public void setProviderPackageName(String providerPackageName) {
        this.providerPackageName = providerPackageName;
    }

    public MethodInvocationResult create(){
        return new MethodInvocationResult();
    }


    public  MethodInvocationResult consumerClassName(String consumerClassName){
        this.consumerClassName = consumerClassName;
        return this;
    }

    public String getConsumerClassName() {
        return consumerClassName;
    }

    public void setConsumerClassName(String consumerClassName) {
        this.consumerClassName = consumerClassName;
    }

    public String getProviderClassName() {
        return providerClassName;
    }

    public void setProviderClassName(String providerClassName) {
        this.providerClassName = providerClassName;
    }

    public String getConsumerMethodName() {
        return consumerMethodName;
    }

    public void setConsumerMethodName(String consumerMethodName) {
        this.consumerMethodName = consumerMethodName;
    }

    public List<String> getProviderMethodNameList() {
        return providerMethodNameList;
    }

    public void setProviderMethodNameList(List<String> providerMethodNameList) {
        this.providerMethodNameList = providerMethodNameList;
    }

    public String getRestDto() {
        return restDto;
    }

    public void setRestDto(String restDto) {
        this.restDto = restDto;
    }

    @Override
    public String toString() {
        return "MethodInvocationResult{" +
                "domainName='" + domainName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", consumerClassName='" + consumerClassName + '\'' +
                ", consumerPackageName='" + consumerPackageName + '\'' +
                ", providerPackageName='" + providerPackageName + '\'' +
                ", providerClassName='" + providerClassName + '\'' +
                ", consumerMethodName='" + consumerMethodName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", url='" + url + '\'' +
                ", restDto='" + restDto + '\'' +
                ", providerMethodNameList=" + providerMethodNameList +
                '}';
    }
}
