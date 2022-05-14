/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

/**
 *
 * @author Admin
 */
public class Payment {

    private int id;
    private float amount;
    private String type;

    /**
     * Have paid or not.
     * 0 - unpaid, 1 - paid
     */
    private int status;

    public Payment() {
    }

    public Payment(int id, float amount, int status, String type) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public Payment(float amount, int status, String type) {
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
