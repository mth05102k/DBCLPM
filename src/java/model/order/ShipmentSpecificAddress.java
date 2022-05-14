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
public class ShipmentSpecificAddress {

    private String shipmentDistrictId;
    private String shipmentCityId;

    public ShipmentSpecificAddress() {

    }

    public ShipmentSpecificAddress(String shipmentDistrictId, String shipmentCityId) {
        this.shipmentDistrictId = shipmentDistrictId;
        this.shipmentCityId = shipmentCityId;
    }

    public void setShipmentDistrictId(String shipmentDistrictId) {
        this.shipmentDistrictId = shipmentDistrictId;
    }

    public void setShipmentCityId(String shipmentCityId) {
        this.shipmentCityId = shipmentCityId;
    }

    public String getShipmentDistrictId() {
        return shipmentDistrictId;
    }

    public String getShipmentCityId() {
        return shipmentCityId;
    }

}
