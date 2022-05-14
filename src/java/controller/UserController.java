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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.Address;
import model.user.FullName;
import model.user.User;
import utils.Parser;

/**
 *
 * @author Admin
 */
public class UserController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        userDAO = new UserDAOImpl();
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

        Integer userId = getUserIdFromSession(request.getSession(false));
        if (userId == null) {
            sendToLogout(response);
            return;
        }

        if (route == null || route.equals("/account/profile")) {
            User user = getUserInfo(userId);

//            send to logout route to destroy session if user not exist
            if (user == null) {
                sendToLogout(response);
                return;
            }

            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/user-profile.jsp");
            rd.forward(request, response);
        } else if (route.equalsIgnoreCase("/api/get-address")) {
            Address address = getUserAddress(userId);

            sendResponse(response, "200", address.toJSON());
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

        Integer userId = getUserIdFromSession(request.getSession(false));
        if (userId == null) {
            sendToLogout(response);
            return;
        }

        if (route.equals("/update")) {
            User user = getUserInfoFromRequest(request, userId);
            updateUserInfo(user);

            response.sendRedirect("/g11/user/account/profile");
        }
    }

    private User getUserInfoFromRequest(HttpServletRequest request, int userId) {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phonenumber");
        String addressDetail = request.getParameter("detailaddress");
        String[] districtInfo = Parser.parseStringWithFilledNull(request.getParameter("district"), ";", 2);
        String[] cityInfo = Parser.parseStringWithFilledNull(request.getParameter("city"), ";", 2);

        User user = new User(userId, phoneNumber, email, gender, null,
                new FullName(fullName),
                new Address(addressDetail, districtInfo[1], cityInfo[1], districtInfo[0], cityInfo[0]),
                null
        );

        return user;
    }

    private User getUserInfo(int userId) {
        User user = userDAO.getUserById(userId);
        return user;
    }

    private Address getUserAddress(int userId) {
        return userDAO.getUserAddress(userId);
    }

    private int updateUserInfo(User user) {
        return userDAO.updateUser(user);
    }

    private void sendToLogout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/g11/auth/logout");
    }

    private Integer getUserIdFromSession(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            return null;
        }

        int userId = (Integer) session.getAttribute("userId");

        return userId;
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
