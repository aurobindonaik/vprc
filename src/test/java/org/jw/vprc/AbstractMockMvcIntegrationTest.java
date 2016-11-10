package org.jw.vprc;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
public abstract class AbstractMockMvcIntegrationTest extends AbstractIntegrationTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
}
