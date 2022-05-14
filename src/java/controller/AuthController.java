/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.cart.CartDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.Account;
import model.user.User;
import utils.HashGenerator;

/**
 *
 * @author Admin
 */
public class AuthController extends HttpServlet {

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

        if (route.equalsIgnoreCase("/logout")) {
            logout(request, response);
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (route.equalsIgnoreCase("/login")) {
            login(username, password, request, response);
        } else if (route.equalsIgnoreCase("/register")) {
            register(username, password, request, response);
        }
    }

    private void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new UserDAOImpl().getUserByUsername(username);
        String hashedPassword = HashGenerator.hashPassword(password);

        if (user != null && hashedPassword.equals(user.getAccount().getPassword())) {
            createSession(user, request);

            String redirectPath = "/g11/home";
            if (user.getAccount().getRole() == 1) {
                redirectPath = "/g11/staff";
            }

            sendResponse(response, "200", redirectPath);
        } else {
            sendResponse(response, "401", "Unauthorized");
        }
    }

    private void register(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDAO userDAO = new UserDAOImpl();
        boolean isUsernameExist = userDAO.checkUsernameExist(username);

        if (isUsernameExist == false) {
            String hashedPassword = HashGenerator.hashPassword(password);
            User user = userDAO.createUserAccount(new Account(username, hashedPassword));

            if (user != null) {
                int cartId = new CartDAOImpl().createCartByUserID(user.getId());
                createSession(user, request);
                sendResponse(response, "201", "Create resource successfully");
            }

            sendResponse(response, "503", "Unexpected server error");
        } else {
            sendResponse(response, "409", "Resource already exist");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        destroySession(request);
        response.sendRedirect("/g11/home");
    }

    private HttpSession createSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getAccount().getUsername());
        session.setAttribute("role", user.getAccount().getRole());
        session.setAttribute("avatar", user.getAvatar());

        return session;
    }

    private void destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
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
