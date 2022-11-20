package pl.wojdylak.propsi.service.dto.payu;

import java.util.ArrayList;
import java.util.Date;

public class PayUPaymentNotification {
    private Order order;
    private Date localReceiptDateTime;
    private ArrayList<Property> properties;

    public PayUPaymentNotification() {
    }

    public PayUPaymentNotification(Order order, Date localReceiptDateTime, ArrayList<Property> properties) {
        this.order = order;
        this.localReceiptDateTime = localReceiptDateTime;
        this.properties = properties;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getLocalReceiptDateTime() {
        return localReceiptDateTime;
    }

    public void setLocalReceiptDateTime(Date localReceiptDateTime) {
        this.localReceiptDateTime = localReceiptDateTime;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public static class Buyer {
        private String customerId;
        private String email;
        private String firstName;
        private String lastName;

        public Buyer() {
        }

        public Buyer(String customerId, String email, String firstName, String lastName) {
            this.customerId = customerId;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
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

    public static class Order {
        private String orderId;
        private String extOrderId;
        private Date orderCreateDate;
        private String notifyUrl;
        private String customerIp;
        private String merchantPosId;
        private String description;
        private String currencyCode;
        private String totalAmount;
        private Buyer buyer;
        private PayMethod payMethod;
        private String status;
        private ArrayList<Product> products;

        public Order() {
        }

        public String getOrderId() {
            return orderId;
        }

        public String getExtOrderId() {
            return extOrderId;
        }

        public Date getOrderCreateDate() {
            return orderCreateDate;
        }

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public String getCustomerIp() {
            return customerIp;
        }

        public String getMerchantPosId() {
            return merchantPosId;
        }

        public String getDescription() {
            return description;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public Buyer getBuyer() {
            return buyer;
        }

        public PayMethod getPayMethod() {
            return payMethod;
        }

        public String getStatus() {
            return status;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setExtOrderId(String extOrderId) {
            this.extOrderId = extOrderId;
        }

        public void setOrderCreateDate(Date orderCreateDate) {
            this.orderCreateDate = orderCreateDate;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }

        public void setCustomerIp(String customerIp) {
            this.customerIp = customerIp;
        }

        public void setMerchantPosId(String merchantPosId) {
            this.merchantPosId = merchantPosId;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public void setBuyer(Buyer buyer) {
            this.buyer = buyer;
        }

        public void setPayMethod(PayMethod payMethod) {
            this.payMethod = payMethod;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setProducts(ArrayList<Product> products) {
            this.products = products;
        }
    }

    public static class PayMethod {
        private String amount;
        private String type;

        public PayMethod() {
        }

        public PayMethod(String amount, String type) {
            this.amount = amount;
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

    public static class Property {
        private String name;
        private String value;

        public Property() {
        }

        public Property(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}


