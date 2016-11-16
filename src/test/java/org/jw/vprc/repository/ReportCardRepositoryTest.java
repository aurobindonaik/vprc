package org.jw.vprc.repository;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.jw.vprc.TestClock;
import org.jw.vprc.clock.Clock;
import org.jw.vprc.domain.ReportCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReportCardRepositoryTest extends RepositoryBaseTest{

    @Autowired
    private ReportCardRepository reportCardRepository;

    @Before
    public void setUp(){
        customisedClock = new TestClock(DateTime.parse("04/02/2016 20:27:05", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @Test
    public void verifyFindByFindByReportDate() throws Exception {
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher2", customisedClock.now()));

        List<ReportCard> reportCardsByReportDate = reportCardRepository.findByReportDate(customisedClock.now().toDate());
        assertEquals(2, reportCardsByReportDate.size());
    }

    @Test
    public void verifyFindByPublisherId() throws Exception {
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.tick()));
        List<ReportCard> reportCardsByPublisherId = reportCardRepository.findByPublisherId("publisher1");
        assertEquals(2, reportCardsByPublisherId.size());
    }

    @Test
    public void verifyDeleteByPublisherId() throws Exception {
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher2", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.tick()));

        List<ReportCard> reportCardsDeletedByPublisherId = reportCardRepository.deleteByPublisherId("publisher1");
        assertEquals(2, reportCardsDeletedByPublisherId.size());
    }

    @Test
    public void verifyDeleteByPublisherIdAndReportDate() throws Exception {
        DateTime initialCustomisedTime = customisedClock.now();
        DateTime customisedTimeAfterTick = customisedClock.tick();
        reportCardRepository.save(createReportCard("publisher1", initialCustomisedTime));
        reportCardRepository.save(createReportCard("publisher2", initialCustomisedTime));
        reportCardRepository.save(createReportCard("publisher1", customisedTimeAfterTick));

        List<ReportCard> reportCardsDeletedByPublisherIdAndReportDate = reportCardRepository.deleteByPublisherIdAndReportDate("publisher1", customisedTimeAfterTick.toDate());
        assertEquals(1, reportCardsDeletedByPublisherIdAndReportDate.size());
        assertEquals(2, reportCardRepository.count());
    }

    @Test
    public void verifyFindByPublisherIdAndReportDateBetween() throws Exception {
        Date startDate = DateTime.parse("01/02/2016 00:00:00", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")).toDate();
        Date endDate = DateTime.parse("28/02/2016 23:59:59", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")).toDate();
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher2", customisedClock.now()));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now().plusDays(1)));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now().plusDays(5)));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now().plusMonths(1)));
        reportCardRepository.save(createReportCard("publisher1", customisedClock.now().plusMonths(1).plusDays(3)));

        List<ReportCard> reportCardsForFebruary2016 = reportCardRepository.findByPublisherIdAndReportDateBetween("publisher1", startDate, endDate);
        assertEquals(3, reportCardsForFebruary2016.size());
    }

}