package pl.wojdylak.propsi.service.dto.payu;

import javax.persistence.Column;

public class OwnerPayUCredentialsDto {
    private String payUClientId;
    private String payUClientSecret;

    public OwnerPayUCredentialsDto() {
    }

    public OwnerPayUCredentialsDto(String payUClientId, String payUClientSecret) {
        this.payUClientId = payUClientId;
        this.payUClientSecret = payUClientSecret;
    }

    public String getPayUClientId() {
        return payUClientId;
    }

    public void setPayUClientId(String payUClientId) {
        this.payUClientId = payUClientId;
    }

    public String getPayUClientSecret() {
        return payUClientSecret;
    }

    public void setPayUClientSecret(String payUClientSecret) {
        this.payUClientSecret = payUClientSecret;
    }
}
