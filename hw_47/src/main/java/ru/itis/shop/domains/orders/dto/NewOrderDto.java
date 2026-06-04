package ru.itis.shop.domains.orders.dto;

public class NewOrderDto {

    private Long accountId;
    private String date;

    public NewOrderDto() {
    }

    public NewOrderDto(Long accountId, String date) {
        this.accountId = accountId;
        this.date = date;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
