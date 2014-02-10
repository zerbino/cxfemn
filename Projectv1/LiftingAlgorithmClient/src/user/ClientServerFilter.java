package user;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import model.Service;

public class ClientServerFilter {

	protected Service service;

	public ClientServerFilter() {
		service = JAXRSClientFactory.create(
				"http://localhost:8080/LiftingAlgorithm", Service.class);

	}

	public Service getService() {
		return this.service;
	}

}
