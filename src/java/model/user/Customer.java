/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author DELL
 */
public class Customer extends User{

    public Customer() {
    }

    public Customer(int id, String phone, String mail, String gender, String avatar, FullName fullName, Address address, Account account) {
        super(id, phone, mail, gender, avatar, fullName, address, account);
    }
    
}
