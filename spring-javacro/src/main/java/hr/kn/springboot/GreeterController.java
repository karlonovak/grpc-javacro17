package hr.kn.springboot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterController {

	@PostMapping("/greet")
	public GreeterResponse greet(@RequestBody GreeterRequest request) {
		System.out.println("Got '" + request.getGreeting() + "' from " + request.getSender());
		return new GreeterResponse(request.getRequestId(), true);
	}
}
