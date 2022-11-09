package pl.wojdylak.propsi.service.dto;

import java.time.Instant;

public class PremisesCostDetailRequestDto {
    private Long premisesCostId;
    private Double costValue;
    private String unit;
    private Instant date;

    public PremisesCostDetailRequestDto() {
    }

    public PremisesCostDetailRequestDto(Long premisesCostId, Double costValue, String unit, Instant date) {
        this.premisesCostId = premisesCostId;
        this.costValue = costValue;
        this.unit = unit;
        this.date = date;
    }

    public Long getPremisesCostId() {
        return premisesCostId;
    }

    public void setPremisesCostId(Long premisesCostId) {
        this.premisesCostId = premisesCostId;
    }

    public Double getCostValue() {
        return costValue;
    }

    public void setCostValue(Double costValue) {
        this.costValue = costValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }


}
