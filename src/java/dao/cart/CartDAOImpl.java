/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cart;

import dao.utils.ConDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.user.User;

/**
 *
 * @author DELL
 */
public class CartDAOImpl implements CartDAO {

    private Connection conn;
    private final String GET_CART_BY_USERID = "SELECT * FROM cart where userid= ?;";
    private final String CREATE_CART_BY_USERID = "INSERT INTO cart (UserID) VALUES (?);";
    private final String DELETE_ITEM_IN_CART_BY_ITEMID = "DELETE FROM cartitem WHERE bookitemid = ? AND cartid = ?";
    private final String ADD_ITEM_TO_CART = "INSERT INTO cartitem (Quantity, CartID, bookitemid) VALUES (?, ?, ?);";
    private final String UPDATE_ITEM_AMOUNT = "UPDATE cartitem SET quantity = ? WHERE itemid = ? AND cartid = ?";
    private final String ADD_EXISTED_ITEM_TO_CART = "UPDATE cartitem SET quantity = quantity + ? WHERE id = ?";
    private final String GET_ITEM_AMOUNT_IN_CART_BY_ID_SQL = "SELECT quantity FROM cart_item WHERE itemid = ? AND cartid = ?";
    private final String GET_ITEM_IN_CART_BY_USERID = "SELECT cartitem.* FROM cartitem, cart WHERE cartitem.cartid = cart.id AND cart.userid = ? ";
    private final String CHECK_ITEM_EXIST_IN_CART = "SELECT cartitem.id FROM cart, cartitem "
            + "WHERE cart.userid = ? "
            + "AND cart.id = cartitem.cartid "
            + "AND cartitem.bookitemid = ?";

    public CartDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int getCartByUserID(User user) {
        int cartId = -1;

        try (PreparedStatement ps = conn.prepareStatement(GET_CART_BY_USERID)) {
            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cartId = rs.getInt("cart.id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return cartId;
        }
    }

    public Pair<List<Integer>, List<Integer>> getBookInCartByUserId(int userId) {

        List<Integer> listQuantity = new ArrayList<>();
        List<Integer> listBookId = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_ITEM_IN_CART_BY_USERID)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listQuantity.add(rs.getInt("cartitem.quantity"));
                listBookId.add(rs.getInt("cartitem.bookitemid"));
            }

            Pair<List<Integer>, List<Integer>> listBookIdAndQuantity = new Pair(listBookId, listQuantity);
            return listBookIdAndQuantity;
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

    @Override
    public int createCartByUserID(int userId) {
        int cartId = 0;

        try (PreparedStatement ps = conn.prepareStatement(CREATE_CART_BY_USERID, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, userId);

            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    cartId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return cartId;
        }
    }

    @Override
    public int deleteItemInCartByItemID(int itemId, int cartId) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(DELETE_ITEM_IN_CART_BY_ITEMID)) {
            ps.setInt(1, itemId);
            ps.setInt(2, cartId);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int addItemToCart(int quantity, int cartID, int itemID) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(ADD_ITEM_TO_CART)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartID);
            ps.setInt(3, itemID);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int updateItemAmountByItemID(int quantity, int itemId, int cartId) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_ITEM_AMOUNT)) {
            ps.setInt(1, quantity);
            ps.setInt(2, itemId);
            ps.setInt(3, cartId);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int addExistedItemToCart(int cartItemId, int quantity) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(ADD_EXISTED_ITEM_TO_CART)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int getItemAmountById(int cartId, int itemId) {
        int quantity = 0;

        try (PreparedStatement ps = conn.prepareStatement(GET_ITEM_AMOUNT_IN_CART_BY_ID_SQL)) {
            ps.setInt(1, itemId);
            ps.setInt(2, cartId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return quantity;
        }
    }

    @Override
    public int checkItemExistInCart(int userId, int bookItemId) {
        int cartItemId = -1;

        try (PreparedStatement ps = conn.prepareStatement(CHECK_ITEM_EXIST_IN_CART)) {
            ps.setInt(1, userId);
            ps.setInt(2, bookItemId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cartItemId = rs.getInt("cartitem.id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return cartItemId;
        }
    }
}
