package hr.svgroup.ws.upload;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsUploadApplication {

	public static final String WS = "/greet";

	public static void main(String[] args) {
		SpringApplication.run(WsUploadApplication.class, args);
	}

	@Bean
	public Endpoint endpoint(SpringBus bus) {
		Map<String, Object> tProperties = new HashMap<>();
		tProperties.put("schema-validation-enabled", true);
		tProperties.put("jaxb-validation-event-handler", new MyCustomHandler());

		Endpoint endpoint = Endpoint.create(greeterService());
		endpoint.setProperties(tProperties);

		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		binding.setMTOMEnabled(true);
		endpoint.publish(WS);
		return endpoint;
	}

	@Bean
	public GreeterService greeterService() {
		return new GreeterServiceImpl();
	}

}
