/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import utils.Jsonlizable;

/**
 *
 * @author Admin
 */
public class Shipment implements Jsonlizable {

    private int id;
    private float cost;
    private String shipUnit;

    /**
     * Type of shipment.
     * 1 - express, 2 - standard, 3 - saving
     */
    private String type;

    public Shipment() {
    }

    public Shipment(int id, String type, float cost, String shipUnit) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.shipUnit = shipUnit;
    }

    public Shipment(String type, float cost, String shipUnit) {
        this.type = type;
        this.cost = cost;
        this.shipUnit = shipUnit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public float getCost() {
        return cost;
    }

    public String getShipUnit() {
        return shipUnit;
    }

    public void setShipUnit(String shipUnit) {
        this.shipUnit = shipUnit;
    }

    private String getAttributeCheckNull(String attribute) {
        return attribute == null ? null : "\"" + attribute + "\"";
    }

    @Override
    public String toJSON() {
        return "{"
                + "\"id\": " + id
                + ", \"type\": " + getAttributeCheckNull(type)
                + ", \"cost\": " + cost
                + ", \"shipUnit\": " + getAttributeCheckNull(shipUnit)
                + "}";
    }

}
