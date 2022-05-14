/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.bookitem.BookItemDAO;
import dao.bookitem.BookItemDAOImpl;
import dao.cart.CartDAO;
import dao.cart.CartDAOImpl;
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
import model.user.User;

/**
 *
 * @author Admin
 */
public class CartController extends HttpServlet {

    private CartDAO cartDAO;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param config
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String route = request.getPathInfo();

        Integer userId = getUserIdFromSession(request.getSession(false));

        if (userId == null) {
            response.sendRedirect("/g11/auth/logout");
            return;
        }

        if (route == null) {
            Pair<List<BookItem>, List<Integer>> listItemAndQuantity = getCartItem(userId);
            List<BookItem> listItem = listItemAndQuantity.getKey();
            List<Integer> listQuantity = listItemAndQuantity.getValue();

            request.setAttribute("listItem", listItem);
            request.setAttribute("listQuantity", listQuantity);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/cart.jsp");
            rd.forward(request, response);
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
        String itemId = request.getParameter("itemId");
        String itemQuantity = request.getParameter("quantity");

        Integer userId = getUserIdFromSession(request.getSession(false));

        if (userId == null) {
            sendResponse(response, "401;");
            return;
        }

        if (route == null) {
            response.sendRedirect("/error");
        } else if (route.equalsIgnoreCase("/api/add-to-cart")) {

            int rowAffected = addItemToCart(itemId, userId, itemQuantity);

            if (rowAffected <= 0) {
                sendResponse(response, "503;");
            } else {
                sendResponse(response, "201;");
            }
        } else if (route.equalsIgnoreCase("/api/delete")) {
            if (deleteItemInCart(Integer.parseInt(itemId), userId)) {
                sendResponse(response, "201");
            } else {
                sendResponse(response, "503");
            }
        }
    }

    private Pair<List<BookItem>, List<Integer>> getCartItem(int userId) {
        Pair<List<Integer>, List<Integer>> listBookItemIdAndQuantity = new CartDAOImpl().getBookInCartByUserId(userId);

        BookItemDAO bookDAO = new BookItemDAOImpl();

        List<Integer> listBookItemId = listBookItemIdAndQuantity.getKey();
        List<BookItem> listBookItem = new ArrayList<>();

        for (Integer bookItemId : listBookItemId) {
            BookItem bookItem = bookDAO.getBookItem(bookItemId);
            listBookItem.add(bookItem);
        }

        return new Pair(listBookItem, listBookItemIdAndQuantity.getValue());
    }

    private int checkItemExistInCart(int userId, int bookItemId) {
        return cartDAO.checkItemExistInCart(userId, bookItemId);
    }

    private int addItemToCart(String itemId, int userId, String itemQuantity) {
        int itemIdNumber = Integer.parseInt(itemId);
        int itemQuantityNumber = Integer.parseInt(itemQuantity);
        int rowAffected = 0;

        int cartItemId = checkItemExistInCart(userId, itemIdNumber);

//        item existed in cart -> add more
        if (cartItemId > 0) {
            rowAffected = cartDAO.addExistedItemToCart(cartItemId, itemQuantityNumber);
        } else {
            int cartId = cartDAO.getCartByUserID(new User(userId, null, null));
            if (cartId > 0) {
                rowAffected = cartDAO.addItemToCart(itemQuantityNumber, cartId, itemIdNumber);
            }
        }

        return rowAffected;
    }

    private boolean deleteItemInCart(int itemId, int userId) {
        int cartId = cartDAO.getCartByUserID(new User(userId, null, null));

        int rowCount = cartDAO.deleteItemInCartByItemID(itemId, cartId);
        if (rowCount > 0) {
            return true;
        }

        return false;
    }

    private Integer getUserIdFromSession(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            return null;
        }

        int userId = (Integer) session.getAttribute("userId");

        return userId;
    }

    private void sendResponse(HttpServletResponse response, String responseData) throws IOException {
        response.setHeader("Content-Type", "text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.write(responseData);
        writer.close();
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
