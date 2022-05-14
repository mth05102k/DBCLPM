/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.bookitem.BookItemDAOImpl;
import dao.user.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.BookItem;
import model.user.User;
import utils.Jsonlizable;
import utils.Parser;

/**
 *
 * @author Admin
 */
public class CheckoutController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            response.sendRedirect("/g11/error");
        } else if (route.equalsIgnoreCase("/fill-info")) {
            User user = new UserDAOImpl().getUserById(userId);

            request.setAttribute("user", user);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/checkout.jsp");
            rd.forward(request, response);
        } else if (route.equalsIgnoreCase("/api/order-item-info")) {
            String itemIdStr = request.getParameter("orderitemid");
            int[] itemIdArray = Parser.parseStringToIntegerArray(itemIdStr, ",");

            String bookItemArrayJSON = createArrayJSON(getBookItems(itemIdArray));

            sendResponse(response, "200", bookItemArrayJSON);
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
    }

    private List<BookItem> getBookItems(int[] bookItemIdArray) {
        return new BookItemDAOImpl().getMultipleBookItem(bookItemIdArray);
    }

    private Integer getUserIdFromSession(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            return null;
        }

        int userId = (Integer) session.getAttribute("userId");

        return userId;
    }

    private <T extends Jsonlizable> String createArrayJSON(List<T> listItem) {
        StringBuilder arrayJSON = new StringBuilder("[");

        for (T item : listItem) {
            arrayJSON.append(item.toJSON());
            arrayJSON.append(",");
        }

        arrayJSON.deleteCharAt(arrayJSON.length() - 1);
        arrayJSON.append("]");

        return arrayJSON.toString();
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
