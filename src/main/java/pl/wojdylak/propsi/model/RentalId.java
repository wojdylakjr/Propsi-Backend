package pl.wojdylak.propsi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RentalId implements Serializable {

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "premises_id")
    private Long premisesId;

    public RentalId() {
    }

    public RentalId(Long tenantId, Long premisesId) {
        this.tenantId = tenantId;
        this.premisesId = premisesId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RentalId that = (RentalId) o;
        return Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(premisesId, that.premisesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId, premisesId);
    }

    @Override
    public String toString() {
        return "RentalId{" +
                "tenantId=" + tenantId +
                ", premisesId=" + premisesId +
                '}';
    }
}
