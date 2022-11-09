package pl.wojdylak.propsi.service.dto;

import java.time.Instant;

public class MeterMeasurementRequestDto {
    private Long meterId;
    private Double value;
    private String unit;
    private Instant date;

    public MeterMeasurementRequestDto() {
    }

    public MeterMeasurementRequestDto(Long meterId, Double value, String unit, Instant date) {
        this.meterId = meterId;
        this.value = value;
        this.unit = unit;
        this.date = date;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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

    @Override
    public String toString(){
        return "meter id: " + meterId;
    }

}
