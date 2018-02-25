package scanner.mongo.model;



public class ProviderViewDI extends ViewDI{

    private String serviceUrl;
    private String serviceDto;
    private String serviceDescription;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceDto() {
        return serviceDto;
    }

    public void setServiceDto(String serviceDto) {
        this.serviceDto = serviceDto;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewDI)) return false;

        ViewDI viewDI = (ViewDI) o;

        if (pComponent != null ? !pComponent.equals(viewDI.pComponent) : viewDI.pComponent != null) return false;
        if (serviceName != null ? !serviceName.equals(viewDI.serviceName) : viewDI.serviceName != null) return false;
        return methodName != null ? methodName.equals(viewDI.methodName) : viewDI.methodName == null;
    }

    @Override
    public int hashCode() {
        int result = pComponent != null ? pComponent.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        return result;
    }
}
