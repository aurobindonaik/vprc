package org.jw.vprc;

import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@SpringBootTest(classes = VprcApplication.class)
public abstract class AbstractIntegrationTest {

    @ClassRule
    public final static SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
}
