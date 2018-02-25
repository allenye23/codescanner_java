package scanner.mongo.model;


public class ConsumerViewDI extends ViewDI{

    private String destination;
    private String consumerFunctionDescription;


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConsumerFunctionDescription() {
        return consumerFunctionDescription;
    }

    public void setConsumerFunctionDescription(String consumerFunctionDescription) {
        this.consumerFunctionDescription = consumerFunctionDescription;
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
