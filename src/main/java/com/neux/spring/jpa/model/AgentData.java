package com.neux.spring.jpa.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TW_LH_SD_Agent_Data")
public class AgentData {
    @Column(name = "OrganizationalUnit")
    private String organizationalUnit = null;

    @Id
    @Column(name = "AgentID")
    private String agentID = null;

    @Column(name = "AgentName")
    private String agentName = null;

    @Column(name = "OfficeName")
    private String officeName = null;

    @Column(name = "Gender")
    private String gender = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OBMonth")
    private Date oBMonth = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LeavingDate")
    private Date leavingDate = null;

    @Column(name = "CreateBy")
    private String createBy = null;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CreateTime")
    private Date createTime = null;

    @Column(name = "UpdateBy")
    private String updateBy = null;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "UpdateTime")
    private Date updateTime = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataTime")
    private Date dataTime = null;


    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getOBMonth() {
        return oBMonth;
    }

    public void setOBMonth(Date oBMonth) {
        this.oBMonth = oBMonth;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }
}
