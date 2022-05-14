/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.book.BookDAO;
import dao.book.BookDAOImpl;
import dao.bookitem.BookItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.book.Book;
import model.book.BookItem;

/**
 *
 * @author Admin
 */
public class BookController extends HttpServlet {

    private BookDAO bookDAO;
    private final int limitItemPerPage = 15;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        bookDAO = new BookDAOImpl();
    }

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

//        path = "/product/" -> get product list
        if (route == null) {
            String page = request.getParameter("page");
            String searchQuery = request.getParameter("q");

            int pageNumber = getPageNumber(page);

            List<BookItem> listItem = getItems(pageNumber, searchQuery);

            request.setAttribute("listItem", listItem);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/list-item.jsp");
            rd.forward(request, response);

        } else {

            int bookItemId = 0;

//            item id not number -> not found
            try {
                bookItemId = Integer.parseInt(route.substring(1));
            } catch (NumberFormatException e) {
                System.err.println(e);
                response.sendRedirect(request.getContextPath() + "/not-found");
                return;
            }

            Pair<BookItem, Book> bookDetail = getBookDetail(bookItemId);
            request.setAttribute("bookItem", bookDetail.getKey());
            request.setAttribute("book", bookDetail.getValue());

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/item-detail.jsp");
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

        if (route.equalsIgnoreCase("/api/available")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = getBookWithBookItemId(itemId);

            if (checkBookAvailable(quantity, book)) {
                sendResponse(response, "200");
            } else {
                sendResponse(response, "406");
            }
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

    private List<BookItem> getItems(int page, String searchQuery) {
        int from = page * limitItemPerPage + limitItemPerPage;
        List<BookItem> listItem = new BookItemDAOImpl().getNewItems(limitItemPerPage, from, searchQuery);
        return listItem;
    }

    private int getPageNumber(String page) {
        if (page != null) {
            return Integer.parseInt(page);
        }

        return 0;
    }

    private boolean checkBookAvailable(int quantity, Book book) {
        return book.getRemainingQuantity() >= quantity;
    }
//    private BookItem getBookItem(int bookItemId) {
//        return new BookDAOImpl().getBookItemById(bookItemId);
//    }

    private Book getBookWithBookItemId(int bookItemId) {
        return bookDAO.getBookWithBookitemID(bookItemId);
    }

    protected Pair<BookItem, Book> getBookDetail(int bookItemId) {
        return bookDAO.getBookAllSttWithBookID(bookItemId);
    }

    private void sendResponse(HttpServletResponse response, String responseData) throws IOException {
        response.setHeader("Content-Type", "text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.write(responseData);
        writer.close();
    }
}
