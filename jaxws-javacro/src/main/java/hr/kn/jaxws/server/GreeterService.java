package hr.kn.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://kn.hr/greet/ws/test/v1.0", name = "GreetWS")
public interface GreeterService {
	  @WebMethod(operationName = "greet")
	  GreeterResponse greet (@WebParam(name = "greetRequest") GreeterRequest request);
}