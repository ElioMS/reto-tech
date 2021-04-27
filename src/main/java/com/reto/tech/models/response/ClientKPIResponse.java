package com.reto.tech.models.response;

import java.io.Serializable;

public class ClientKPIResponse implements Serializable {
    private Integer average;
    private Double sd;

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }
}
