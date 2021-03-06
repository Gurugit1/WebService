package com.mitchellInternational.vo;


import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "LossInfo")
public
class LossInformation {

    private String causeOfLoss;

    @XmlJavaTypeAdapter(TimeStamp.class)
    private Timestamp ReportedDate;
    private String LossDescription;

    @XmlElement(name = "CauseOfLoss")
    public void setCauseOfLoss(String causeOfLoss) {
        this.causeOfLoss = causeOfLoss;
    }

    @XmlElement(name = "ReportedDate")
    public void setReportedDate(Timestamp ReportedDate) {
        this.ReportedDate = ReportedDate;
    }

    @XmlElement(name = "LossDescription")
    public void setLossDescription(String LossDescription) {
        this.LossDescription = LossDescription;
    }

    public String getCauseOfLoss() {
        return causeOfLoss;
    }

    public Timestamp getReportedDate() {
        return ReportedDate;
    }

    public String getLossDescription() {
        return LossDescription;
    }
}

