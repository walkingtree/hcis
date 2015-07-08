
package pl._3e.adinterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "ModelADServicePortType", targetNamespace = "http://3e.pl/ADInterface")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ModelADServicePortType {

	@WebMethod(operationName = "runProcess", action = "")
	@WebResult(name = "RunProcessResponse", targetNamespace = "http://3e.pl/ADInterface")
	public RunProcessResponse runProcess(
			@WebParam(name = "ModelRunProcessRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelRunProcessRequest ModelRunProcessRequest);

	@WebMethod(operationName = "createData", action = "")
	@WebResult(name = "StandardResponse", targetNamespace = "http://3e.pl/ADInterface")
	public StandardResponse createData(
			@WebParam(name = "ModelCRUDRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest);

	@WebMethod(operationName = "updateData", action = "")
	@WebResult(name = "StandardResponse", targetNamespace = "http://3e.pl/ADInterface")
	public StandardResponse updateData(
			@WebParam(name = "ModelCRUDRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest);

	@WebMethod(operationName = "getList", action = "")
	@WebResult(name = "WindowTabData", targetNamespace = "http://3e.pl/ADInterface")
	public WindowTabData getList(
			@WebParam(name = "ModelGetListRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelGetListRequest ModelGetListRequest);

	@WebMethod(operationName = "readData", action = "")
	@WebResult(name = "WindowTabData", targetNamespace = "http://3e.pl/ADInterface")
	public WindowTabData readData(
			@WebParam(name = "ModelCRUDRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest);

	@WebMethod(operationName = "setDocAction", action = "")
	@WebResult(name = "StandardResponse", targetNamespace = "http://3e.pl/ADInterface")
	public StandardResponse setDocAction(
			@WebParam(name = "ModelSetDocActionRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelSetDocActionRequest ModelSetDocActionRequest);

	@WebMethod(operationName = "deleteData", action = "")
	@WebResult(name = "StandardResponse", targetNamespace = "http://3e.pl/ADInterface")
	public StandardResponse deleteData(
			@WebParam(name = "ModelCRUDRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest);

	@WebMethod(operationName = "queryData", action = "")
	@WebResult(name = "WindowTabData", targetNamespace = "http://3e.pl/ADInterface")
	public WindowTabData queryData(
			@WebParam(name = "ModelCRUDRequest", targetNamespace = "http://3e.pl/ADInterface")
			pl._3e.adinterface.ModelCRUDRequest ModelCRUDRequest);

}
