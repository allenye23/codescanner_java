package scanner.mongo.model;

public class ViewDI {
    protected String provider;
    protected String pComponent;
    protected String consumer;
    protected String cComponent;
    protected String serviceName;
    protected String serviceType;
    protected String methodName;
    protected String updatedBy;
    protected String remark;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getpComponent() {
        return pComponent;
    }

    public void setpComponent(String pComponent) {
        this.pComponent = pComponent;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getcComponent() {
        return cComponent;
    }

    public void setcComponent(String cComponent) {
        this.cComponent = cComponent;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewDI)) return false;

        ViewDI viewDI = (ViewDI) o;

        if (pComponent != null ? !pComponent.equals(viewDI.pComponent) : viewDI.pComponent != null) return false;
        if (cComponent != null ? !cComponent.equals(viewDI.cComponent) : viewDI.cComponent != null) return false;
        if (serviceName != null ? !serviceName.equals(viewDI.serviceName) : viewDI.serviceName != null) return false;
        return methodName != null ? methodName.equals(viewDI.methodName) : viewDI.methodName == null;
    }

    @Override
    public int hashCode() {
        int result = pComponent != null ? pComponent.hashCode() : 0;
        result = 31 * result + (cComponent != null ? cComponent.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        return result;
    }
}
