package hr.kn.springboot;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcApplication.class, args);
	}

	/**
	 * This will keep TCP connections alive infinitely to make it work similarly
	 * as gRPC or Thrift. Alive connections promote reusage and boost
	 * performance.
	 *
	 */
	@Bean
	@SuppressWarnings("rawtypes")
	public EmbeddedServletContainerFactory getEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
		containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				((AbstractHttp11Protocol) connector.getProtocolHandler()).setKeepAliveTimeout(-1);
				((AbstractHttp11Protocol) connector.getProtocolHandler()).setMaxKeepAliveRequests(-1);
			}
		});
		return containerFactory;
	}
}
