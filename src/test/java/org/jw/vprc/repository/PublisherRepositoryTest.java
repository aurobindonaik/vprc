package org.jw.vprc.repository;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jw.vprc.VprcApplication;
import org.jw.vprc.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.jw.vprc.repository.FongoMongoTestConfig.getSpringFongoMongoDbRule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VprcApplication.class, FongoMongoTestConfig.class})
public class PublisherRepositoryTest {
    @Autowired
    private ApplicationContext applicationContext; // nosql-unit requirement

    @Rule
    public MongoDbRule mongoDbRule = getSpringFongoMongoDbRule();

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
//    @ShouldMatchDataSet(location = "publisher-test-expected.json")
    public void verifySavePublisher() throws Exception {
        publisherRepository.save(createPublisher());
        assertEquals(1, publisherRepository.count());
    }

    @Test
//    @UsingDataSet(locations = {"publisher-test-expected.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void verifyFindByPublishedId() throws Exception {
        publisherRepository.save(createPublisher());

        Publisher publisher = publisherRepository.findByPublisherId("anu1234");
        assertNotNull(publisher);
        assertEquals("Annapurna", publisher.getFirstName());
    }

    @Test
    public void verifyFindByPublisherFirstName() throws Exception {
        publisherRepository.save(createPublisher());
        publisherRepository.save(createPublisher2());

        List<Publisher> publishers = publisherRepository.findByFirstName("Aurobindo");
        assertEquals(1, publishers.size());
        assertEquals("Aurobindo", publishers.get(0).getFirstName());
    }

    @Test
    public void verifyFindByPublisherSurname() throws Exception {
        publisherRepository.save(createPublisher());
        publisherRepository.save(createPublisher2());

        List<Publisher> publishers = publisherRepository.findBySurname("Naik");
        assertEquals(2, publishers.size());
        assertEquals("Annapurna", publishers.get(0).getFirstName());
        assertEquals("Aurobindo", publishers.get(1).getFirstName());
    }

    @Test
    public void verifyUpdatePublisher() throws Exception {
        publisherRepository.save(createPublisher());

        Publisher publisher = publisherRepository.findByPublisherId("anu1234");
        publisher.setAddress("Annapurna's new address");

        publisherRepository.save(publisher);

        Publisher updatedPublisher = publisherRepository.findByPublisherId("anu1234");
        assertEquals("Annapurna's new address", updatedPublisher.getAddress());
    }

    @Test
    public void verifyDeletePublisher() throws Exception {
        Publisher publisherToBeDeleted = createPublisher();
        publisherRepository.save(publisherToBeDeleted);
        publisherRepository.save(createPublisher2());

        publisherRepository.delete(publisherToBeDeleted);
        List<Publisher> publishers = publisherRepository.findBySurname("Naik");
        assertEquals(1, publishers.size());
        assertEquals("Aurobindo", publishers.get(0).getFirstName());
    }

    @Test
    public void verifyDeletePublisherByPublisherId() throws Exception {
        Publisher publisherToBeDeleted = createPublisher();
        publisherRepository.save(publisherToBeDeleted);
        publisherRepository.save(createPublisher2());

        List<Publisher> deletedPublishers = publisherRepository.deleteByPublisherId(publisherToBeDeleted.getPublisherId());
        List<Publisher> publishers = publisherRepository.findBySurname("Naik");
        assertEquals(1, publishers.size());
        assertEquals("Aurobindo", publishers.get(0).getFirstName());

        assertEquals("Annapurna", deletedPublishers.get(0).getFirstName());
    }

    private Publisher createPublisher() {
        Publisher publisher = new Publisher();
        publisher.setId("r1");
        publisher.setPublisherId("anu1234");
        publisher.setFirstName("Annapurna");
        publisher.setSurname("Naik");
        publisher.setPhoneNumber("07872178624");
        publisher.setEmail("annapurnanaik@testemail.com");
        publisher.setAddress("Annapurna's address");

        return publisher;
    }

    private Publisher createPublisher2() {
        Publisher publisher = new Publisher();
        publisher.setId("r2");
        publisher.setPublisherId("auro5678");
        publisher.setFirstName("Aurobindo");
        publisher.setSurname("Naik");
        publisher.setPhoneNumber("07515863564");
        publisher.setEmail("aurobindonaik@testemail.com");
        publisher.setAddress("Aurobindo's address");

        return publisher;
    }
}
