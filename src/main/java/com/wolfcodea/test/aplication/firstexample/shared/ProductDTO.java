package com.wolfcodea.test.aplication.firstexample.shared;


public class ProductDTO {
    
    //#region Atributes
    private String id;

    private String name;

    private Integer quantity;

    private Double price;

    private String observartion;
    //#endregion

    //#region Getter and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getObservartion() {
        return observartion;
    }

    public void setObservartion(String observartion) {
        this.observartion = observartion;
    }
    //#endregion
}
