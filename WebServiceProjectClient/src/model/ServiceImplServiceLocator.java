/**
 * ServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model;

public class ServiceImplServiceLocator extends org.apache.axis.client.Service implements model.ServiceImplService {

    public ServiceImplServiceLocator() {
    }


    public ServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceImpl
    private java.lang.String ServiceImpl_address = "http://localhost:8080/WebServiceProject/services/ServiceImpl";

    public java.lang.String getServiceImplAddress() {
        return ServiceImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceImplWSDDServiceName = "ServiceImpl";

    public java.lang.String getServiceImplWSDDServiceName() {
        return ServiceImplWSDDServiceName;
    }

    public void setServiceImplWSDDServiceName(java.lang.String name) {
        ServiceImplWSDDServiceName = name;
    }

    public model.ServiceImpl getServiceImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceImpl(endpoint);
    }

    public model.ServiceImpl getServiceImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            model.ServiceImplSoapBindingStub _stub = new model.ServiceImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getServiceImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceImplEndpointAddress(java.lang.String address) {
        ServiceImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (model.ServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                model.ServiceImplSoapBindingStub _stub = new model.ServiceImplSoapBindingStub(new java.net.URL(ServiceImpl_address), this);
                _stub.setPortName(getServiceImplWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServiceImpl".equals(inputPortName)) {
            return getServiceImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model", "ServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model", "ServiceImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceImpl".equals(portName)) {
            setServiceImplEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
