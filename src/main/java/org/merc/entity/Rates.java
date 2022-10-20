package org.merc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rates {
    @JsonProperty("EUR")
    private double eur;
    @JsonProperty("USD")
    private double usd;
    @JsonProperty("GBP")
    private double gbp;
    @JsonProperty("CHF")
    private double chf;
}
