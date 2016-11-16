package org.jw.vprc.persistence;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.jw.vprc.TestClock;
import org.jw.vprc.domain.ReportCard;
import org.jw.vprc.repository.PublisherRepository;
import org.jw.vprc.repository.ReportCardRepository;
import org.jw.vprc.repository.RepositoryBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersistenceServiceIntegrationTest extends RepositoryBaseTest {

    private PersistenceService persistenceService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReportCardRepository reportCardRepository;

    private TestClock customisedRealTime;

    @Before
    public void setUp(){
        customisedRealTime = new TestClock(DateTime.now().withMonthOfYear(2).withDayOfMonth(1));//Always 1st February
        persistenceService = new PersistenceService(customisedRealTime, reportCardRepository);
    }

    @Test
    public void verifyPublisherReportForMonth() throws Exception {
        ReportCard reportCard1 = createReportCard("publisher1", customisedRealTime.now());
        fillReportCard(reportCard1, "grp1", "cong1", "3.30", 5, 1, 3, 2);

        ReportCard reportCard2 = createReportCard("publisher1", customisedRealTime.now().plusDays(5));
        fillReportCard(reportCard2, "grp1", "cong1", "1.30", 2, 0, 1, 1);

        ReportCard reportCardMarch = createReportCard("publisher1", customisedRealTime.now().plusMonths(1));
        fillReportCard(reportCardMarch, "grp1", "cong1", "1.30", 2, 0, 1, 1);

        reportCardRepository.save(reportCard1);
        reportCardRepository.save(reportCard2);
        reportCardRepository.save(reportCardMarch);

        ReportCard publisherMonthlyReport = persistenceService.getPublisherMonthlyReport("publisher1", "February");
        assertReportCardValues(publisherMonthlyReport);

        publisherMonthlyReport = persistenceService.getPublisherMonthlyReport("publisher1");
        assertReportCardValues(publisherMonthlyReport);
    }

    private void fillReportCard(ReportCard reportCard2, String groupId, String congregationId,
                                String hours, int placements, int studies, int returnVisits, int videoShowings) {
        reportCard2.setGroupId(groupId);
        reportCard2.setCongregationId(congregationId);
        reportCard2.setHours(hours);
        reportCard2.setPlacements(placements);
        reportCard2.setStudies(studies);
        reportCard2.setReturnVisits(returnVisits);
        reportCard2.setVideoShowings(videoShowings);
    }

    private void assertReportCardValues(ReportCard publisherMonthlyReport) {
        assertThat(publisherMonthlyReport.getHours(), equalTo("5"));
        assertThat(publisherMonthlyReport.getPlacements(), equalTo(7));
        assertThat(publisherMonthlyReport.getStudies(), equalTo(1));
        assertThat(publisherMonthlyReport.getReturnVisits(), equalTo(4));
        assertThat(publisherMonthlyReport.getVideoShowings(), equalTo(3));
    }

    @Test
    public void verifyGetPublisherReportCardsForMonth() throws Exception {
        reportCardRepository.save(createReportCard("publisher2", customisedRealTime.now()));
        reportCardRepository.save(createReportCard("publisher1", customisedRealTime.now()));
        reportCardRepository.save(createReportCard("publisher1", customisedRealTime.now().plusDays(1)));
        reportCardRepository.save(createReportCard("publisher1", customisedRealTime.now().plusDays(5)));
        //Publishers record for March
        reportCardRepository.save(createReportCard("publisher1", customisedRealTime.now().plusMonths(1)));
        reportCardRepository.save(createReportCard("publisher1", customisedRealTime.now().plusMonths(1).plusDays(3)));

        List<ReportCard> reportCardsForFebruary2016 = persistenceService.getPublisherReportCardsForMonth("publisher1", "February");
        assertEquals(3, reportCardsForFebruary2016.size());

        reportCardsForFebruary2016 = persistenceService.getPublisherReportCardsForMonth("publisher1");
        assertEquals(3, reportCardsForFebruary2016.size());
    }
}