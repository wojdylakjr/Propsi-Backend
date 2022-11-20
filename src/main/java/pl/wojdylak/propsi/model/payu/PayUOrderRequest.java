package pl.wojdylak.propsi.model.payu;

import java.util.List;

public class PayUOrderRequest {
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private String totalAmount;
    private String continueUrl;
    private String extOrderId;
    private Buyer buyer;
    private List<Product> products;

    public PayUOrderRequest() {
    }

    public PayUOrderRequest(String notifyUrl, String customerIp, String merchantPosId, String description, String currencyCode, String totalAmount, String continueUrl, String extOrderId) {
        this.notifyUrl = notifyUrl;
        this.customerIp = customerIp;
        this.merchantPosId = merchantPosId;
        this.description = description;
        this.currencyCode = currencyCode;
        this.totalAmount = totalAmount;
        this.continueUrl = continueUrl;
        this.extOrderId = extOrderId;
    }

    public PayUOrderRequest(String notifyUrl, String customerIp, String merchantPosId, String description, String currencyCode, String totalAmount, String continueUrl, String extOrderId, Buyer buyer, List<Product> products) {
        this.notifyUrl = notifyUrl;
        this.customerIp = customerIp;
        this.merchantPosId = merchantPosId;
        this.description = description;
        this.currencyCode = currencyCode;
        this.totalAmount = totalAmount;
        this.continueUrl = continueUrl;
        this.extOrderId = extOrderId;
        this.buyer = buyer;
        this.products = products;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getMerchantPosId() {
        return merchantPosId;
    }

    public void setMerchantPosId(String merchantPosId) {
        this.merchantPosId = merchantPosId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getContinueUrl() {
        return continueUrl;
    }

    public void setContinueUrl(String continueUrl) {
        this.continueUrl = continueUrl;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static class Buyer {
        private String email;
        private String firstName;
        private String lastName;

        public Buyer() {
        }

        public Buyer(String email, String firstName, String lastName) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static class Product {
        private String name;
        private String unitPrice;
        private String quantity;

        public Product() {
        }

        public Product(String name, String unitPrice, String quantity) {
            this.name = name;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
