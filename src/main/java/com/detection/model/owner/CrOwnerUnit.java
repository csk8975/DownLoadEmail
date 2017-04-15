package com.detection.model.owner;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="owner_unit")
public class CrOwnerUnit {

    @Id
    @GeneratedValue
    private int id;
    
    private String dutyTel;

    private String ownerName;

    private String email;

    private long timeStamp;
    
    private String dutyPerson;

    private String token;

    private String authorizedReportNum;
    
    private Date registTime;
    
    private Date loginTime;
    
    private Date tokenTime;

    public CrOwnerUnit() {
        super();
    }

    public String getDutyTel() {
        return dutyTel;
    }

    public void setDutyTel(String dutyTel) {
        this.dutyTel = dutyTel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDutyPerson() {
        return dutyPerson;
    }

    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getAuthorizedReportNum() {
        return authorizedReportNum;
    }

    public void setAuthorizedReportNum(String authorizedReportNum) {
        this.authorizedReportNum = authorizedReportNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
