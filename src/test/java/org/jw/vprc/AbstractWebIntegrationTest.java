package org.jw.vprc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebIntegrationTest extends AbstractIntegrationTest{

	@Value("${server.context-path}")
	protected String contextPath;

	@LocalServerPort
	protected String serverPort;

	protected String getUrl(String path) {
		return String.format("http://localhost:%s%s/%s", serverPort, contextPath, path);
	}
}
