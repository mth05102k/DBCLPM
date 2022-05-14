/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import model.order.ShipmentSpecificAddress;
import utils.Jsonlizable;

/**
 *
 * @author Admin
 */
public class Address extends ShipmentSpecificAddress implements Jsonlizable {

    private int id;
    private String addressDetail;
    private String district;
    private String city;

    public Address() {
        super();
    }

    public Address(String addressDetail, String district, String city) {
        this.addressDetail = addressDetail;
        this.district = district;
        this.city = city;
    }

    public Address(int id, String addressDetail, String district, String city) {
        this.id = id;
        this.addressDetail = addressDetail;
        this.district = district;
        this.city = city;
    }

    public Address(String addressDetail, String district, String city, String shipmentDistrictId, String shipmentCityId) {
        super(shipmentDistrictId, shipmentCityId);
        this.addressDetail = addressDetail;
        this.district = district;
        this.city = city;
    }

    public Address(int id, String addressDetail, String district, String city, String shipmentDistrictId, String shipmentCityId) {
        super(shipmentDistrictId, shipmentCityId);
        this.id = id;
        this.addressDetail = addressDetail;
        this.district = district;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    private String getAttributeCheckNull(String attribute) {
        return attribute == null ? null : "\"" + attribute + "\"";
    }

    @Override
    public String toJSON() {
        return "{"
                + "\"id\": " + id
                + ", \"addressDetail\": " + getAttributeCheckNull(addressDetail)
                + ", \"district\": " + getAttributeCheckNull(district)
                + ", \"city\": " + getAttributeCheckNull(city)
                + ", \"shipmentDistrictId\": " + getAttributeCheckNull(getShipmentDistrictId())
                + ", \"shipmentCityId\": " + getAttributeCheckNull(getShipmentCityId())
                + "}";
    }

}
