/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import model.user.Account;
import model.user.Address;
import model.user.User;

/**
 *
 * @author DELL
 * @param <T>
 */
public interface UserDAO<T> {

    boolean checkUsernameExist(String username);

    User getUserByUsername(String username);

    User getUserById(int id);

    int getUserID(String phone, String mail);

    Address getUserAddress(int userId);

    User createUserAccount(Account account);

    int updateUser(User user);
}
