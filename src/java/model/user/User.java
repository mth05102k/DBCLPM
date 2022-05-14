/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import model.user.FullName;
import model.user.Address;
import model.user.Account;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
 
    private String phone;
    private String mail;
    private String gender;
    private String avatar;
    private FullName fullName;
    private Address address;
    private Account account;

    public User() {
    }

    public User(int id, String phone, String mail) {
        this.id = id;
        this.phone = phone;
        this.mail = mail;
    }

    public User(int id, String phone, String mail, String gender, String avatar, FullName fullName, Address address, Account account) {
        this.id = id;
        this.phone = phone;
        this.mail = mail;
        this.gender = gender;
        this.avatar = avatar;
        this.fullName = fullName;
        this.address = address;
        this.account = account;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    public Account getAccount() {
        return account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
