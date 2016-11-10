package org.jw.vprc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebIntegrationTest extends AbstractIntegrationTest{

	@Value("${server.context-path}")
	protected String contextPath;
	@Value("${local.server.port}")
	protected String serverPort;

	protected String getUrl(String path) {
		return String.format("http://localhost:%s%s/%s", serverPort, contextPath, path);
	}
}
