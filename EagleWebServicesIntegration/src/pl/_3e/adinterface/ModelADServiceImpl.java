
package pl._3e.adinterface;

import javax.jws.WebService;

@WebService(serviceName = "ModelADService", targetNamespace = "http://3e.pl/ADInterface", endpointInterface = "pl._3e.adinterface.ModelADServicePortType")
public class ModelADServiceImpl implements ModelADServicePortType {

	public RunProcessResponse runProcess(
			pl._3e.adinterface.ModelRunProcessRequest ModelRunProcessRequest) {
		throw new UnsupportedOperationException();
	}

	public StandardResponse createData(
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest) {
		throw new UnsupportedOperationException();
	}

	public StandardResponse updateData(
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest) {
		throw new UnsupportedOperationException();
	}

	public WindowTabData getList(
			pl._3e.adinterface.ModelGetListRequest ModelGetListRequest) {
		throw new UnsupportedOperationException();
	}

	public WindowTabData readData(
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest) {
		throw new UnsupportedOperationException();
	}

	public StandardResponse setDocAction(
			pl._3e.adinterface.ModelSetDocActionRequest ModelSetDocActionRequest) {
		throw new UnsupportedOperationException();
	}

	public StandardResponse deleteData(
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest) {
		throw new UnsupportedOperationException();
	}

	public WindowTabData queryData(
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest) {
		throw new UnsupportedOperationException();
	}

}
