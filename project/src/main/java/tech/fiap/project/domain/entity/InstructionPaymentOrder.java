package tech.fiap.project.domain.entity;

import java.math.BigDecimal;

public class InstructionPaymentOrder {
    private String id;
    private String description;
    private String externalReference;
    private String title;
    private String notificationUrl;
    private String qrData;
    private BigDecimal amount;

    public InstructionPaymentOrder(String id, String description, String externalReference, String title, String notificationUrl, String qrData, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.externalReference = externalReference;
        this.title = title;
        this.notificationUrl = notificationUrl;
        this.qrData = qrData;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}