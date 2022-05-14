/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import model.book.BookItem;
import model.order.Order;
import model.user.User;

/**
 *
 * @author DELL
 * @param <T>
 */
public interface OrderDAO<T> {

    int addItemsToOrder(Pair<List<BookItem>, List<Integer>> listBookItemAndQuantity, int orderId);

    int createOrder(User user, Order order, List<Pair<Integer, Integer>> listItem);

    int updateOrder(int orderId, String shipType, float cost, Date createdDate, int status, float amount, String payType, String shipUnit);

    int deleteOrder(int orderId);

    T getOrderDetail(int orderId);

    List<T> getMultipleOrderInfoOnly(int userId, Integer status, int from, int to);

    List<Pair<BookItem, Integer>> getAllOrderBookItem(int orderId);
}
