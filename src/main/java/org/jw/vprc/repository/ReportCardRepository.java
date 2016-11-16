package org.jw.vprc.repository;

import org.jw.vprc.domain.ReportCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportCardRepository extends MongoRepository<ReportCard, String> {
    List<ReportCard> findByPublisherId(String publisherId);

    List<ReportCard> findByReportDate(Date reportDate);

    List<ReportCard> findByPublisherIdAndReportDateBetween(String publisherId, Date startDate, Date endDate);

    List<ReportCard> deleteByPublisherId(String publisherId);

    List<ReportCard> deleteByPublisherIdAndReportDate(String publisherId, Date reportDate);
}
