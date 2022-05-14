/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.book.BookItem;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;
import model.user.User;

/**
 *
 * @author DELL
 */
public class OrderDAOImpl implements OrderDAO {

    private Connection conn;

    private final String GET_CART_ID = "INSERT INTO cart (UserID, TotalPrice) VALUES (?, ?);";
    private final String GET_ALL_ORDER = "SELECT * FROM ("
            + "(`order` INNER JOIN shipment ON `order`.shipmentId = shipment.id) "
            + "INNER JOIN payment ON `order`.paymentId = payment.id) "
            + "WHERE `order`.customerUserId = ?";
    private final String GET_ALL_ORDER_INFO_ONLY_SQL = "SELECT * FROM `order` WHERE `order`.customerUserId = ? LIMIT ?, ?";
    private final String GET_ALL_ORDER_INFO_ONLY_WITH_STATUS_SQL = "SELECT * FROM `order` WHERE `order`.customerUserId = ? AND status = ? LIMIT ?, ?";
    private final String GET_ORDER_DETAIL_SQL = "SELECT * FROM `order`, shipment, payment WHERE `order`.id = ? "
            + "AND `order`.shipmentId = shipment.id "
            + "AND `order`.paymentId = payment.id";
    private final String GET_ALL_ORDER_BOOK_ITEM_SQL = "SELECT bookitem.*, orderitem.quantity FROM `order`, orderitem, bookitem "
            + "WHERE `order`.id = ? "
            + "AND `order`.id = orderitem.orderId "
            + "AND orderitem.bookItemId = bookitem.id";

    private final String INSERT_PAYMENT_SQL = "INSERT INTO payment (status, amount, type) VALUES (?, ? ,?);";
    private final String INSERT_SHIPMENT_SQL = "INSERT INTO shipment (type, cost, shipUnit) VALUES (?, ? ,?);";
    private final String INSERT_ORDER_SQL = "INSERT INTO `order` (customerUserId, shipmentId, paymentId, status) VALUES (?, ?, ?, ?);";
    private final String INSERT_ITEM_TO_ORDER = "INSERT INTO orderitem (orderId, bookItemId, quantity) VALUES (?, ?, ?)";

    private final String DELETE_ORDER = "DELETE FROM order WHERE id=?";

    private final String ADD_ITEM_TO_ORDER = "INSERT INTO orderitem (Quantity, BookitemID, OrderID) VALUES (?, ?, ?)";

    public OrderDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int updateOrder(int orderID, String shipType, float cost, Date createdDate, int status, float amount, String payType, String shipUnit) {
//        String UPDATE_ORDER = "Update ORDER\n"
//                + "set createDate= ? , status = ? \n"
//                + "where id= " + orderID + ";";
//        String UPDATE_PAYMENT = "Update payment\n"
//                + "set Status=?,Amount=?,Type=?\n"
//                + "where id=?";
//        String GET_SHIPMENTID_FROM_ORDERID = "Select ShipmentID from order Where ID=" + orderID + ";";
//        String GET_PAYMENTID_FROM_ORDERID = "Select ShipmentID from order Where ID=" + orderID + ";";
//        String UPDATE_SHIPMENT = "Update shipment\n"
//                + "set Type=?,Cost=?,ShipUnit=?\n"
//                + "where id=?";
//
//        try {
//            preStatement = conn.prepareStatement(UPDATE_ORDER);
//            preStatement.setDate(1, new java.sql.Date(createdDate.getTime()));
//            preStatement.setInt(2, status);
//            int rowcout = preStatement.executeUpdate();
//            Statement1 = conn.createStatement();
//            rs = Statement1.executeQuery(GET_PAYMENTID_FROM_ORDERID);
//            int paymentid = 0;
//            while (rs.next()) {
//                paymentid = rs.getInt(1);
//            }
//            preStatement1 = conn.prepareStatement(UPDATE_PAYMENT);
//            preStatement1.setInt(1, status);
//            preStatement1.setFloat(2, amount);
//            preStatement1.setString(3, payType);
//            preStatement1.setInt(4, paymentid);
//            int rowcout1 = preStatement1.executeUpdate();
//            Statement = conn.createStatement();
//            rs = Statement.executeQuery(GET_SHIPMENTID_FROM_ORDERID);
//            int shipmentid = 0;
//            while (rs.next()) {
//                shipmentid = rs.getInt(1);
//            }
//            preStatement2 = conn.prepareStatement(UPDATE_SHIPMENT);
//            preStatement2.setString(1, shipType);
//            preStatement2.setFloat(2, cost);
//            preStatement2.setString(3, shipUnit);
//            preStatement2.setInt(4, shipmentid);
//            int rowcout2 = preStatement2.executeUpdate();
//            return rowcout2;
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
        return 1;
    }

    @Override
    public int deleteOrder(int orderID) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(DELETE_ORDER);) {
            ps.setInt(1, orderID);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int addItemsToOrder(Pair listBookItemAndQuantity, int orderID) {
        List<BookItem> bookItem = (List<BookItem>) listBookItemAndQuantity.getKey();
        List<Integer> quantity = (List< Integer>) listBookItemAndQuantity.getValue();

        int rowCount = 0;

        for (int i = 0; i < bookItem.size(); i++) {
            try (PreparedStatement ps = conn.prepareStatement(ADD_ITEM_TO_ORDER)) {
                ps.setInt(1, quantity.get(i));
                ps.setInt(2, bookItem.get(i).getID());
                ps.setInt(3, orderID);

                rowCount += ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rowCount;
    }

    @Override
    public int createOrder(User user, Order order, List listItem) {
        Shipment shipmentInfo = order.getShipmentInfo();
        Payment paymentInfo = order.getPaymentInfo();

        int shipmentId = insertShipmentInfo(shipmentInfo.getType(), shipmentInfo.getCost(), shipmentInfo.getShipUnit());
        int paymentId = insertPaymentInfo(paymentInfo.getStatus(), paymentInfo.getAmount(), paymentInfo.getType());

        int orderId = -1;

        try (PreparedStatement ps = conn.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, shipmentId);
            ps.setInt(3, paymentId);
            ps.setInt(4, order.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
                insertItemOrder(orderId, listItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return orderId;
        }
    }

    private int insertItemOrder(int orderId, List<Pair<Integer, Integer>> listItem) {
        int rowCount = 0;

        for (Pair<Integer, Integer> item : listItem) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_TO_ORDER)) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getKey());
                ps.setInt(3, item.getValue());

                rowCount += ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return rowCount;
    }

    private int insertPaymentInfo(int status, float amount, String type) {
        int paymentId = -1;

        try (PreparedStatement ps = conn.prepareStatement(INSERT_PAYMENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, status);
            ps.setFloat(2, amount);
            ps.setString(3, type);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paymentId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return paymentId;
        }
    }

    private int insertShipmentInfo(String shipmentType, float shipmentCost, String shipUnit) {
        int shipmentId = -1;

        try (PreparedStatement ps = conn.prepareStatement(INSERT_SHIPMENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, shipmentType);
            ps.setFloat(2, shipmentCost);
            ps.setString(3, shipUnit);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                shipmentId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return shipmentId;
        }
    }

    @Override
    public Order getOrderDetail(int orderId) {
        Order order = null;
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_ORDER_DETAIL_SQL)) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = Mapper.mapOrder(rs);
                user = Mapper.mapUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return order;
        }
    }

    @Override
    public List<Order> getMultipleOrderInfoOnly(int userId, Integer status, int from, int to) {
        List<Order> listOrder = new ArrayList<>();

        try {
            PreparedStatement ps;
            if (status == null) {
                ps = conn.prepareStatement(GET_ALL_ORDER_INFO_ONLY_SQL);
                ps.setInt(1, userId);
                ps.setInt(2, from);
                ps.setInt(3, to);
            } else {
                ps = conn.prepareStatement(GET_ALL_ORDER_INFO_ONLY_WITH_STATUS_SQL);
                ps.setInt(1, userId);
                ps.setInt(2, status);
                ps.setInt(3, from);
                ps.setInt(4, to);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = Mapper.mapOrderInfoOnly(rs);
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listOrder;
        }
    }

    @Override
    public List<Pair<BookItem, Integer>> getAllOrderBookItem(int orderId) {
        List<Pair<BookItem, Integer>> listBookItem = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_ALL_ORDER_BOOK_ITEM_SQL)) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookItem bookItem = Mapper.mapBookItem(rs);
                int quantity = rs.getInt("quantity");

                listBookItem.add(new Pair(bookItem, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listBookItem;
        }
    }
}
