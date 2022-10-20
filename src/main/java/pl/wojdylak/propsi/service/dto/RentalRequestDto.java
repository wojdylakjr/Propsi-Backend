package pl.wojdylak.propsi.service.dto;

public class RentalRequestDto {
    private Long tenantId;
    private Long premisesId;
    private String name;

    public RentalRequestDto() {
    }

    public RentalRequestDto(Long tenantId, Long premisesId, String name) {
        this.tenantId = tenantId;
        this.premisesId = premisesId;
        this.name = name;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getPremisesId() {
        return premisesId;
    }

    public void setPremisesId(Long premisesId) {
        this.premisesId = premisesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RentalRequestDto{" +
                "tenantId=" + tenantId +
                ", premisesId=" + premisesId +
                ", name='" + name + '\'' +
                '}';
    }
}
