/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.book;

import utils.Jsonlizable;

/**
 *
 * @author DELL
 */
public class BookItem implements Jsonlizable {

    private int ID;
    private float price;
    private float discount;
    private int sellingStatus;
    private String description;
    private String image;
    private String name;
    private String category;

    public BookItem() {
    }

    public BookItem(int ID, float price, float discount, int sellingStatus, String description, String image, String name, String category) {
        this.ID = ID;
        this.price = price;
        this.discount = discount;
        this.sellingStatus = sellingStatus;
        this.description = description;
        this.image = image;
        this.name = name;
        this.category = category;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getSellingStatus() {
        return sellingStatus;
    }

    public void setSellingStatus(int sellingStatus) {
        this.sellingStatus = sellingStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String getAttributeCheckNull(String attribute) {
        return attribute == null ? null : "\"" + attribute + "\"";
    }

    @Override
    public String toJSON() {
        return "{"
                + "\"id\": " + ID
                + ", \"name\": " + getAttributeCheckNull(name)
                + ", \"price\": " + price
                + ", \"discount\": " + discount
                + ", \"sellingStatus\": " + sellingStatus
                + ", \"image\": " + getAttributeCheckNull(image)
                + ", \"category\": " + getAttributeCheckNull(category)
                + "}";
    }

}
