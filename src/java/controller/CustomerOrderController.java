/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.cart.CartDAOImpl;
import dao.order.OrderDAO;
import dao.order.OrderDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.BookItem;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;
import model.user.User;
import utils.Parser;

/**
 *
 * @author Admin
 */
public class CustomerOrderController extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        orderDAO = new OrderDAOImpl();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request
     * @param response
     * @throws ServletException if a servlet-specific error occurs
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String route = request.getPathInfo();
        System.out.println(route);
        HttpSession session = request.getSession(false);
        Integer userId = getUserIdFromSession(session);

//        check login success
        if (userId == null) {
            response.sendRedirect("/g11/auth/logout");
            return;
        }

//        controller for customer role
        if ((Byte) session.getAttribute("role") != 0) {
            response.sendRedirect("/g11/error");
            return;
        }

        if (route == null) {
            Integer orderId = Parser.parseIntSafe(request.getParameter("orderid"), null);

//            get multiple order route
            if (orderId == null) {
                int from = 0;
                int to = 5;
                Integer status = Parser.parseIntSafe(request.getParameter("status"), null);

                List<Order> listOrder = getMultipleCustomerOrder(userId, status, from, to);
                List<List<Pair<BookItem, Integer>>> listBookItem = getAllOrderBookItem(listOrder);

                request.setAttribute("orderList", listOrder);
                request.setAttribute("listBookItem", listBookItem);
                forwardRequest(request, response, "/jsp/customer-order.jsp");
            } else {
                Order order = getOrder(orderId, userId);

                if (order == null) {
                    response.sendRedirect("/g11/error");
                } else {
                    forwardRequest(request, response, "");
                }
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String route = request.getPathInfo();

        Integer userId = getUserIdFromSession(request.getSession(false));

        if (userId == null) {
            response.sendRedirect("/g11/auth/logout");
            return;
        }

        if (route == null) {

        } else if (route.equalsIgnoreCase("/create")) {
            String selectedItemJSON = request.getParameter("selectedItemJSON");
            String selectedPaymentType = request.getParameter("selectedPaymentType");
            String selectedShipmentType = request.getParameter("selectedShipmentType");
            float shipmentCost = Float.parseFloat(request.getParameter("shipmentCost"));

            int orderId = createOrder(userId, selectedItemJSON, selectedPaymentType, selectedShipmentType, shipmentCost);
            if (orderId > 0) {
                sendResponse(response, "201", orderId + "");
            } else {
                sendResponse(response, "503", "");
            }
        }
    }

    private int createOrder(int userId, String selectedItemJSON, String selectedPaymentType, String selectedShipmentType, float shipmentCost) {
        List<Pair<Integer, Integer>> selectedItemIdAndQuantity = parseSelectedItem(selectedItemJSON);

//        pending order
        int orderStatus = 0;

//        unpaid
        int paymentStatus = 0;
        Order order = new Order(
                orderStatus,
                new Shipment(selectedShipmentType, shipmentCost, "ghn"),
                new Payment(shipmentCost, paymentStatus, selectedPaymentType)
        );

        return (orderDAO.createOrder(new User(userId, null, null), order, selectedItemIdAndQuantity));
    }

    private List<Pair<Integer, Integer>> parseSelectedItem(String selectedItemJSON) {
        List<Pair<Integer, Integer>> selectedItemIdAndQuantity = new ArrayList<>();

        String removedBracketStr = selectedItemJSON.substring(1, selectedItemJSON.length() - 1);
        String[] selectedItemArr = removedBracketStr.split(",");

        for (String selectedItem : selectedItemArr) {
            String[] tokens = selectedItem.split(":");
            int itemId = Integer.parseInt(tokens[0].substring(1, tokens[0].length() - 1));
            int quantity = Integer.parseInt(tokens[1]);

            selectedItemIdAndQuantity.add(new Pair(itemId, quantity));
        }

        return selectedItemIdAndQuantity;
    }

    private int getCart(int userId) {
        return new CartDAOImpl().getCartByUserID(new User(userId, null, null));
    }

    private float calcItemTotalPrice(BookItem item, int quantity) {
        return (item.getPrice() - item.getPrice() * item.getDiscount()) * quantity;
    }

    private Order getOrder(int orderId, int userId) {
        Order order = (Order) orderDAO.getOrderDetail(orderId);

        if (order.getUserId() == userId) {
            return order;
        }

        return null;
    }

    private List<Order> getMultipleCustomerOrder(int userId, Integer status, int from, int to) {
        return orderDAO.getMultipleOrderInfoOnly(userId, status, from, to);
    }

    private List<List<Pair<BookItem, Integer>>> getAllOrderBookItem(List<Order> listOrder) {
        List<List<Pair<BookItem, Integer>>> listBookItem = new ArrayList<>();

        for (Order order : listOrder) {
            List<Pair<BookItem, Integer>> listBookItemOfOneOrder = orderDAO.getAllOrderBookItem(order.getId());
            listBookItem.add(listBookItemOfOneOrder);
        }

        return listBookItem;
    }

    private Integer getUserIdFromSession(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            return null;
        }

        int userId = (Integer) session.getAttribute("userId");

        return userId;
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String forwardPath) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
        rd.forward(request, response);
    }

    private void sendResponse(HttpServletResponse response, String responseCode, String responseData) throws IOException {
        response.setHeader("Content-Type", "text/plain");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(responseCode + ";" + responseData);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
