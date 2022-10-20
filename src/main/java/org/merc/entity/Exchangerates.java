package org.merc.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Exchangerates {

    private Rates rates;
    private String date;

    public Rates getRates() {
        return rates;
    }

    @XmlElement
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

}