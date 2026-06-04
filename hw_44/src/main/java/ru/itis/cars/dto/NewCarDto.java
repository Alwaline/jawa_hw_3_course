package ru.itis.cars.dto;

public class NewCarDto {

    private String model;
    private String color;
    private Double price;

    public NewCarDto(String model, String color, Double price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public NewCarDto() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
