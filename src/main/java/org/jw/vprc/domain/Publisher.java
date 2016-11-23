package org.jw.vprc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "publisher")
public class Publisher {
    @Id
    private String id;

    @Field
    private String publisherId;

    @Field
    private String congregationId;

    @Field
    private String groupId;

    @Field
    private String firstName;

    @Field
    private String middleName;

    @Field
    private String surname;

    @Field
    private String gender;

    @Field
    private Date dateOfBirth;

    @Field
    private Date dateOfBaptism;

    @Field
    private String publisherStatus;

    @Field
    private String address;

    @Field
    private String country;

    @Field
    private String phoneNumber;

    @Field
    private String email;

    @Field
    Privilege[] privileges;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBaptism() {
        return dateOfBaptism;
    }

    public void setDateOfBaptism(Date dateOfBaptism) {
        this.dateOfBaptism = dateOfBaptism;
    }

    public String getPublisherStatus() {
        return publisherStatus;
    }

    public void setPublisherStatus(String publisherStatus) {
        this.publisherStatus = publisherStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Privilege[] getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Privilege[] privileges) {
        this.privileges = privileges;
    }
}
