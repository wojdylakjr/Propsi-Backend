package pl.wojdylak.propsi.model.payu;

public class PayUAddOrderResponse {
    private Status status;
    private String redirectUri;
    private String orderId;
    private String extOrderId;

    public PayUAddOrderResponse() {
    }

    public PayUAddOrderResponse(Status status, String redirectUri, String orderId, String extOrderId) {
        this.status = status;
        this.redirectUri = redirectUri;
        this.orderId = orderId;
        this.extOrderId = extOrderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

    public static class Status {
        private String statusCode;

        public Status() {
        }

        public Status(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }
    }


}
