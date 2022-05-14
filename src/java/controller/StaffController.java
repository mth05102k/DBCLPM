/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.book.BookDAO;
import dao.book.BookDAOImpl;
import dao.bookitem.BookItemDAOImpl;
import dao.cart.CartDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;
import utils.Parser;

/**
 *
 * @author Admin
 */
public class StaffController extends HttpServlet {
    
    private BookDAO bookDAO;
    
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
        HttpSession session = request.getSession(false);
        Integer userId = getUserIdFromSession(session);
        
        if (userId == null || (Byte) session.getAttribute("role") != 1) {
            response.sendRedirect("/g11/auth/logout");
            return;
        }
        
        if (route == null) {
            response.sendRedirect("staff/book");
        } else if (route.equalsIgnoreCase("/book")) {
            int from = 1;
            int to = 10;
            List<Book> listBook = getMultipleBook(from, to);
            int bookQuantity = countBook();
            
            request.setAttribute("listBook", listBook);
            request.setAttribute("bookQuantity", bookQuantity);
            forwardRequest(request, response, "/jsp/staff/book-management.jsp");
            
        } else if (route.equalsIgnoreCase("/book/edit")) {
            
            Integer bookId = Parser.parseIntSafe(request.getParameter("id"), null);
            
            if (bookId == null) {
                
            } else {
                Book book = getBookDetail(bookId);
                
                request.setAttribute("book", book);
                forwardRequest(request, response, "/jsp/staff/book-detail.jsp");
            }
            
        } else if (route.equalsIgnoreCase("/book-item/edit")) {
            
            Integer bookItemId = Parser.parseIntSafe(request.getParameter("id"), null);
            if (bookItemId == null) {
                System.err.println("Route: " + route + " | bookitemid null");
                return;
            }
            
            BookItem bookItem = getBookItemDetail(bookItemId);
            request.setAttribute("book", bookItem);
            forwardRequest(request, response, "/jsp/staff/book-item-detail.jsp");
            
        } else if (route.contains("/book/author")) {
            
            Integer bookId = Parser.parseIntSafe(request.getParameter("bookid"), null);
            if (bookId == null) {
                System.err.println("Route: " + route + " | bookid null");
                return;
            }
            
            if (route.contains("add")) {
                
                request.setAttribute("action", "add");
                request.setAttribute("bookId", bookId);
                forwardRequest(request, response, "/jsp/staff/author-detail.jsp");
                
            } else if (route.contains("edit")) {
                
                Integer authorId = Parser.parseIntSafe(request.getParameter("authorid"), null);
                if (authorId == null) {
                    return;
                }
                
                Author author = getAuthor(authorId);
                
                request.setAttribute("action", "edit");
                request.setAttribute("author", author);
                request.setAttribute("bookId", bookId);
                forwardRequest(request, response, "/jsp/staff/author-detail.jsp");
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
        request.setCharacterEncoding("UTF-8");
        
        String route = request.getPathInfo();
        HttpSession session = request.getSession(false);
        Integer userId = getUserIdFromSession(session);
        
        if (userId == null || (Byte) session.getAttribute("role") != 1) {
            response.sendRedirect("/g11/auth/logout");
            return;
        }
        
        if (route == null) {
            
        } else if (route.contains("/book/edit")) {
            Integer bookId = Parser.parseIntSafe(request.getParameter("id"), null);
            
            if (bookId == null) {
                System.err.println("Route: " + route + " | bookid null");
                return;
            }
            
            if (route.contains("/status")) {
                
                Boolean status = Parser.parseBooleanSafe(request.getParameter("status"), null);
                if (status == null) {
                    System.err.println("Route: " + route + " | status null");
                    return;
                }
                
                boolean isUpdated = updateBookStatus(bookId, status);
                
                if (isUpdated) {
                    sendResponse(response, "201", "");
                } else {
                    sendResponse(response, "503", "");
                }
                
            } else {
                Book book = getBookParamFromRequest(request, bookId);
                updateBookInfo(book);
                
                response.sendRedirect(request.getRequestURI() + "?id=" + bookId);
            }
            
        } else if (route.contains("/book/delete")) {
            
            Integer bookId = Parser.parseIntSafe(request.getParameter("id"), null);
            
            if (bookId == null) {
                System.err.println("Route: " + route + " | bookid null");
                return;
            }
            
            deleteBook(bookId);
            response.sendRedirect("/g11/staff/book");
            
        } else if (route.contains("/book/author")) {
            
            Author author = getAuthorParamFromRequest(request);
            Integer bookId = Parser.parseIntSafe(request.getParameter("bookid"), null);
            
            if (bookId == null) {
                return;
            }
            
            if (route.contains("add")) {
                
                addBookAuthor(author, bookId);
                response.sendRedirect("/g11/staff/book/edit?id=" + bookId);
                
            } else if (route.contains("edit")) {
                
                updateBookAuthor(author);
                response.sendRedirect("/g11/staff/book/edit?id=" + bookId);
                
            } else if (route.contains("delete")) {
                
                deleteBookAuthor(bookId, author.getId());
                sendResponse(response, "204", "/g11/staff/book/edit?id=" + bookId);
                
            }
        } else if (route.contains("/book-item/edit")) {
            Integer bookItemId = Parser.parseIntSafe(request.getParameter("id"), null);
            if (bookItemId == null) {
                return;
            }
            
            BookItem bookItem = getBookItemParamFromRequest(request, bookItemId);
            updateBookItem(bookItem);
            response.sendRedirect("/g11/staff/book-item/edit?id=" + bookItemId);
        }
    }
    
    private List<Book> getMultipleBook(int from, int to) {
        return bookDAO.getMultipleBook(from, to);
    }
    
    private BookItem getBookItemDetail(int bookItemId) {
        return new BookItemDAOImpl().getBookItem(bookItemId);
    }
    
    private int countBook() {
        return bookDAO.getBookCounting();
    }
    
    private Book getBookDetail(int bookId) {
        return bookDAO.getBook(bookId);
    }
    
    private Author getAuthor(int authorId) {
        return bookDAO.getAuthorById(authorId);
    }
    
    private boolean updateBookInfo(Book book) {
        int rowCount = 0;
        
        rowCount = bookDAO.updateBook(book);
        rowCount = bookDAO.updatePublisher(book.getPub(), book.getId());
        
        return rowCount > 0;
    }
    
    private boolean updateBookItem(BookItem bookItem) {
        int rowCount = new BookItemDAOImpl().updateBookItem(bookItem);
        return rowCount > 0;
    }
    
    private boolean deleteBook(int bookId) {
        int rowCount = bookDAO.deleteBook(bookId);
        return rowCount > 0;
    }
    
    private boolean addBookAuthor(Author author, int bookId) {
        int rowCount = bookDAO.addBookAuthor(author, bookId);
        return rowCount == 2;
    }
    
    private boolean updateBookAuthor(Author author) {
        int rowCount = bookDAO.updateAuthor(author);
        return rowCount > 0;
    }
    
    private boolean updateBookStatus(int bookId, boolean status) {
        int statusInt = (status == true) ? 1 : 0;
        int rowCount = bookDAO.updateBookStatus(bookId, statusInt);
        
        return rowCount > 0;
    }
    
    private boolean deleteBookAuthor(int bookId, int authorId) {
        int rowCount = bookDAO.deleteBookAuthor(bookId, authorId);
        
        return rowCount > 0;
    }
    
    private Book getBookParamFromRequest(HttpServletRequest request, int bookId) {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String publicationYear = request.getParameter("publishyear");
        Integer numberOfPage = Parser.parseIntSafe(request.getParameter("page"), 0);
        Integer remainingQuantity = Parser.parseIntSafe(request.getParameter("remaining"), 0);
        Float cost = Parser.parseFloatSafe(request.getParameter("price"), (float) 0);
        String language = request.getParameter("language");
        Publisher publisher = new Publisher(request.getParameter("publisher"));
        
        Book book = new Book(
                bookId, isbn, title, summary, publicationYear,
                numberOfPage, remainingQuantity, cost, language
        );
        book.setPub(publisher);
        
        return book;
    }
    
    private BookItem getBookItemParamFromRequest(HttpServletRequest request, int bookItemId) {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        Float price = Parser.parseFloatSafe(request.getParameter("price"), (float) 0);
        Float discount = Parser.parseFloatSafe(request.getParameter("discount"), (float) 0);
        String description = request.getParameter("description");
        
        return new BookItem(bookItemId, price, discount, bookItemId, description, image, name, category);
    }
    
    private Author getAuthorParamFromRequest(HttpServletRequest request) {
        int authorId = Parser.parseIntSafe(request.getParameter("authorid"), 0);
        String name = request.getParameter("name");
        String biography = request.getParameter("biography");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        
        return new Author(authorId, name, biography, email, address);
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
