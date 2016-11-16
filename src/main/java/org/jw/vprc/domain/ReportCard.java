package org.jw.vprc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "reportcard")
public class ReportCard {
    @Id
    private String id;

    @Field
    private String congregationId;

    @Field
    private String groupId;

    @Field
    private String publisherId;

    @Field
    private Date reportDate;

    @Field
    private String hours;

    @Field
    private int placements;

    @Field
    private int videoShowings;

    @Field
    private int returnVisits;

    @Field
    private int studies;

    public ReportCard() {
    }

    public ReportCard(String publisherId, Date reportDate) {
        this.publisherId = publisherId;
        this.reportDate = reportDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(String congregationId) {
        this.congregationId = congregationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public int getPlacements() {
        return placements;
    }

    public void setPlacements(int placements) {
        this.placements = placements;
    }

    public int getVideoShowings() {
        return videoShowings;
    }

    public void setVideoShowings(int videoShowings) {
        this.videoShowings = videoShowings;
    }

    public int getReturnVisits() {
        return returnVisits;
    }

    public void setReturnVisits(int returnVisits) {
        this.returnVisits = returnVisits;
    }

    public int getStudies() {
        return studies;
    }

    public void setStudies(int studies) {
        this.studies = studies;
    }
}
