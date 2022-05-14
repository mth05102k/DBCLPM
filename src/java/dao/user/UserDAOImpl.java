/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user.Account;
import model.user.Address;
import model.user.FullName;
import model.user.User;

/**
 *
 * @author Admin
 */
public class UserDAOImpl implements UserDAO {

    private Connection conn;

    private final String CHECK_USERNAME_EXIST_SQL = "SELECT 1 FROM account WHERE username = ?";
    private final String CREATE_EMPTY_USER_SQL = "INSERT INTO user VALUES ()";
    private final String CREATE_EMPTY_USER_FULLNAME_SQL = "INSERT INTO fullname (userid) VALUES (?)";
    private final String CREATE_EMPTY_USER_ADDRESS_SQL = "INSERT INTO address (userid) VALUES (?)";
    private final String CREATE_USER_ACCOUNT_SQL = "INSERT INTO account (userid, username, password) values (?, ?, ?)";

    private final String GET_USER_BY_USERNAME_SQL = "SELECT * FROM user, account, fullname, address "
            + "WHERE account.username = ? AND user.id = account.userid AND user.id = fullname.userid AND user.id = address.userid";
    private final String GET_USER_BY_USERID_SQL = "SELECT * FROM user, account, fullname, address "
            + "WHERE user.id = ? AND user.id = account.userid AND user.id = fullname.userid AND user.id = address.userid";
    private final String GET_USER_ADDRESS_BY_USER_ID_SQL = "SELECT * FROM address WHERE userid = ?";

    private final String UPDATE_USER_SQL = "UPDATE user SET phone = IFNULL(?, phone), email = IFNULL(?, email), gender = IFNULL(?, gender) WHERE id = ?";
    private final String UPDATE_ADDRESS_SQL = "UPDATE address SET addressdetail = IFNULL(?, addressdetail), "
            + "district = IFNULL(?, district), "
            + "city = IFNULL(?, city), "
            + "shipmentDistrictId = IFNULL(?, shipmentDistrictId), "
            + "shipmentCityId = IFNULL(?, shipmentCityId) "
            + "WHERE userid = ?";
    private final String UPDATE_FULLNAME_SQL = "UPDATE fullname SET firstname = IFNULL(?, firstname), "
            + "midname=IFNULL(?, midname), "
            + "lastname=IFNULL(?, lastname) "
            + "WHERE userid=?";

    public UserDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public boolean checkUsernameExist(String username) {
        try (PreparedStatement ps = conn.prepareStatement(CHECK_USERNAME_EXIST_SQL)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public User createUserAccount(Account account) {
        User user = null;
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(CREATE_EMPTY_USER_SQL, Statement.RETURN_GENERATED_KEYS);

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int userId = rs.getInt(1);

                    ps = conn.prepareStatement(CREATE_EMPTY_USER_FULLNAME_SQL);
                    ps.setInt(1, userId);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(CREATE_EMPTY_USER_ADDRESS_SQL);
                    ps.setInt(1, userId);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(CREATE_USER_ACCOUNT_SQL);
                    ps.setInt(1, userId);
                    ps.setString(2, account.getUsername());
                    ps.setString(3, account.getPassword());

                    rowAffected = ps.executeUpdate();

                    if (rowAffected > 0) {
                        user = new User();
                        user.setId(userId);
                        user.setAccount(account);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }

    }

    @Override
    public Address getUserAddress(int userId) {
        Address address = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_ADDRESS_BY_USER_ID_SQL)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = Mapper.mapAddress(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return address;
        }
    }

    @Override
    public int getUserID(String phone, String email
    ) {
        String sql = "Insert into user (Phone,email)  values (?,?);";
        PreparedStatement prestatement;
        ResultSet rs;

        try {
            prestatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prestatement.setString(1, phone);
            prestatement.setString(2, email);
            int rowcount = prestatement.executeUpdate();
            rs = prestatement.getGeneratedKeys();
            int idValue = 0;
            if (rs.next()) {
                idValue = rs.getInt(1);
            }
            return idValue;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int updateUser(User user) {
        int rowAffected = 0;

        try (PreparedStatement prestatement = conn.prepareStatement(UPDATE_USER_SQL)) {
            prestatement.setString(1, user.getPhone());
            prestatement.setString(2, user.getMail());
            prestatement.setString(3, user.getGender());
            prestatement.setInt(4, user.getId());

            rowAffected = prestatement.executeUpdate();

            rowAffected += updateFullName(user.getFullName(), user.getId());
            rowAffected += updateAddress(user.getAddress(), user.getId());
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            return rowAffected;
        }
    }

    private int updateFullName(FullName fullName, int userId) {
        int rowAffected = 0;
        if (fullName == null) {
            return rowAffected;
        }

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_FULLNAME_SQL)) {
            ps.setString(1, fullName.getFirstName());
            ps.setString(2, fullName.getMidName());
            ps.setString(3, fullName.getLastName());
            ps.setInt(4, userId);

            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowAffected;
        }
    }

    private int updateAddress(Address address, int userId) {
        int rowAffected = 0;
        if (address == null) {
            return rowAffected;
        }

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_ADDRESS_SQL)) {
            ps.setString(1, address.getAddressDetail());
            ps.setString(2, address.getDistrict());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getShipmentDistrictId());
            ps.setString(5, address.getShipmentCityId());
            ps.setInt(6, userId);

            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowAffected;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_BY_USERNAME_SQL)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            System.out.println(username);

            if (rs.next()) {
                user = Mapper.mapUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_BY_USERID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = Mapper.mapUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }
    }
}
