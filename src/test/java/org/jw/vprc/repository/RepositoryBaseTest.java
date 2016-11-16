package org.jw.vprc.repository;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.jw.vprc.TestClock;
import org.jw.vprc.VprcApplication;
import org.jw.vprc.domain.Publisher;
import org.jw.vprc.domain.ReportCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.jw.vprc.repository.FongoMongoTestConfig.getSpringFongoMongoDbRule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VprcApplication.class, FongoMongoTestConfig.class})
public class RepositoryBaseTest {
    @Autowired
    protected ApplicationContext applicationContext;

    @Rule
    public MongoDbRule mongoDbRule = getSpringFongoMongoDbRule();
    protected TestClock customisedClock;

    protected Publisher createPublisher() {
        Publisher publisher = new Publisher();
        publisher.setId("r1");
        publisher.setPublisherId("publisher1");
        publisher.setFirstName("Publisher");
        publisher.setSurname("Surname");
        publisher.setPhoneNumber("07111111111");
        publisher.setEmail("publisher1@testemail.com");
        publisher.setAddress("Publisher's address");

        return publisher;
    }

    protected Publisher createPublisher2() {
        Publisher publisher = new Publisher();
        publisher.setId("r2");
        publisher.setPublisherId("publisher2");
        publisher.setFirstName("SecondPublisher");
        publisher.setSurname("Surname");
        publisher.setPhoneNumber("07222222222");
        publisher.setEmail("publisher2@testemail.com");
        publisher.setAddress("SecondPublisher's address");

        return publisher;
    }

    protected ReportCard createReportCard(String publisherId, DateTime customisedDate){
        ReportCard reportCard = new ReportCard(publisherId, customisedDate.toDate());
        return reportCard;
    }
}
