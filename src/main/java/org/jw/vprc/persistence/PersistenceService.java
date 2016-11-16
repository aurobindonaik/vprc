package org.jw.vprc.persistence;

import org.joda.time.DateTime;
import org.jw.vprc.clock.Clock;
import org.jw.vprc.domain.ReportCard;
import org.jw.vprc.repository.ReportCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jw.vprc.clock.DateTimeHelper.*;

@Service
public class PersistenceService {

    private Clock clock;

    private ReportCardRepository reportCardRepository;

    @Autowired
    public PersistenceService(Clock clock, ReportCardRepository reportCardRepository) {
        this.clock = clock;
        this.reportCardRepository = reportCardRepository;
    }

    public List<ReportCard> getPublisherReportCardsForMonth(String publisherId, String month) {
        DateTime startDateOfMonth = getStartOfMonthAtStartOfDay(clock, month);
        DateTime endDateOfMonth = getEndOfMonth(startDateOfMonth);
        return reportCardRepository.findByPublisherIdAndReportDateBetween(publisherId, startDateOfMonth.toDate(), endDateOfMonth.toDate());
    }

    public List<ReportCard> getPublisherReportCardsForMonth(String publisherId) {
        return getPublisherReportCardsForMonth(publisherId, clock.now().monthOfYear().getAsText());
    }

    public ReportCard getPublisherMonthlyReport(String publisherId, String month) {
        DateTime endDateOfMonth = getEndOfMonth(getStartOfMonthAtStartOfDay(clock, month));
        ReportCard combinedMonthlyReportCard = new ReportCard();

        List<ReportCard> publisherReportCardsForMonth = getPublisherReportCardsForMonth(publisherId, month);

        int totalMinutes = 0;
        int totalPlacements = 0;
        int totalVideoShowings = 0;
        int totalReturnVisits = 0;
        int totalStudies = 0;
        String congregationId = null;
        String groupId = null;

        for (ReportCard card : publisherReportCardsForMonth) {
            totalMinutes += getTotalMinutes(card.getHours());
            totalPlacements += card.getPlacements();
            totalVideoShowings += card.getVideoShowings();
            totalReturnVisits += card.getReturnVisits();
            totalStudies += card.getStudies();
            congregationId = card.getCongregationId();
            groupId = card.getGroupId();
        }

        combinedMonthlyReportCard.setCongregationId(congregationId);
        combinedMonthlyReportCard.setGroupId(groupId);
        combinedMonthlyReportCard.setPublisherId(publisherId);
        combinedMonthlyReportCard.setReportDate(endDateOfMonth.toDate());
        combinedMonthlyReportCard.setHours(getTotalHours(totalMinutes));
        combinedMonthlyReportCard.setPlacements(totalPlacements);
        combinedMonthlyReportCard.setVideoShowings(totalVideoShowings);
        combinedMonthlyReportCard.setReturnVisits(totalReturnVisits);
        combinedMonthlyReportCard.setStudies(totalStudies);

        return combinedMonthlyReportCard;
    }

    public ReportCard getPublisherMonthlyReport(String publisherId) {
        return getPublisherMonthlyReport(publisherId, clock.now().monthOfYear().getAsText());
    }
}
