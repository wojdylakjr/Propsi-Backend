package pl.wojdylak.propsi.model.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class PremisesCostDetailRequestDto {
    private Long premisesCostId;
    private BigDecimal costValue;
    private String unit;
    private Instant date;

    public PremisesCostDetailRequestDto() {
    }

    public PremisesCostDetailRequestDto(Long premisesCostId, BigDecimal costValue, String unit, Instant date) {
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

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
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
