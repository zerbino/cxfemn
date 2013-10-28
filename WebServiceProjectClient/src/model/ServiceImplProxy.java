package model;

public class ServiceImplProxy implements model.ServiceImpl {
  private String _endpoint = null;
  private model.ServiceImpl serviceImpl = null;
  
  public ServiceImplProxy() {
    _initServiceImplProxy();
  }
  
  public ServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceImplProxy();
  }
  
  private void _initServiceImplProxy() {
    try {
      serviceImpl = (new model.ServiceImplServiceLocator()).getServiceImpl();
      if (serviceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceImpl != null)
      ((javax.xml.rpc.Stub)serviceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public model.ServiceImpl getServiceImpl() {
    if (serviceImpl == null)
      _initServiceImplProxy();
    return serviceImpl;
  }
  
  public java.lang.String op(model.Personne p) throws java.rmi.RemoteException{
    if (serviceImpl == null)
      _initServiceImplProxy();
    return serviceImpl.op(p);
  }
  
  
}