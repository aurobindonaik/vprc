package org.jw.vprc.repository;

import org.junit.Before;
import org.junit.Test;
import org.jw.vprc.domain.Publisher;
import org.jw.vprc.domain.ReportCard;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PublisherRepositoryTest extends RepositoryBaseTest {
    @Autowired
    private PublisherRepository publisherRepository;

    private List<ReportCard> reportCards;

    @Before
    public void setUp() throws Exception {
        ReportCard reportCard1 = Mockito.mock(ReportCard.class);
        ReportCard reportCard2 = Mockito.mock(ReportCard.class);
        reportCards = Arrays.asList(reportCard1, reportCard2);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void verifySavePublisher() throws Exception {
        publisherRepository.save(createPublisher());
        assertEquals(1, publisherRepository.count());
    }

    @Test
    public void verifyFindByPublishedId() throws Exception {
        publisherRepository.save(createPublisher());

        Publisher publisher = publisherRepository.findByPublisherId("publisher1");
        assertNotNull(publisher);
        assertEquals("Publisher", publisher.getFirstName());
    }

    @Test
    public void verifyFindByPublisherFirstName() throws Exception {
        publisherRepository.save(createPublisher());
        publisherRepository.save(createPublisher2());

        List<Publisher> publishers = publisherRepository.findByFirstName("SecondPublisher");
        assertEquals(1, publishers.size());
        assertEquals("SecondPublisher", publishers.get(0).getFirstName());
    }

    @Test
    public void verifyFindByPublisherSurname() throws Exception {
        publisherRepository.save(createPublisher());
        publisherRepository.save(createPublisher2());

        List<Publisher> publishers = publisherRepository.findBySurname("Surname");
        assertEquals(2, publishers.size());
        assertEquals("Publisher", publishers.get(0).getFirstName());
        assertEquals("SecondPublisher", publishers.get(1).getFirstName());
    }

    @Test
    public void verifyUpdatePublisher() throws Exception {
        publisherRepository.save(createPublisher());

        Publisher publisher = publisherRepository.findByPublisherId("publisher1");
        publisher.setAddress("Publisher's new address");

        publisherRepository.save(publisher);

        Publisher updatedPublisher = publisherRepository.findByPublisherId("publisher1");
        assertEquals("Publisher's new address", updatedPublisher.getAddress());
    }

    @Test
    public void verifyDeletePublisher() throws Exception {
        Publisher publisherToBeDeleted = createPublisher();
        publisherRepository.save(publisherToBeDeleted);
        publisherRepository.save(createPublisher2());

        publisherRepository.delete(publisherToBeDeleted);
        List<Publisher> publishers = publisherRepository.findBySurname("Surname");
        assertEquals(1, publishers.size());
        assertEquals("SecondPublisher", publishers.get(0).getFirstName());
    }

    @Test
    public void verifyDeletePublisherByPublisherId() throws Exception {
        Publisher publisherToBeDeleted = createPublisher();
        publisherRepository.save(publisherToBeDeleted);
        publisherRepository.save(createPublisher2());

        List<Publisher> deletedPublishers = publisherRepository.deleteByPublisherId(publisherToBeDeleted.getPublisherId());
        List<Publisher> publishers = publisherRepository.findBySurname("Surname");
        assertEquals(1, publishers.size());
        assertEquals("SecondPublisher", publishers.get(0).getFirstName());

        assertEquals("Publisher", deletedPublishers.get(0).getFirstName());
    }
}
