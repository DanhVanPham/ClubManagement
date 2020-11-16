/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.UserBlo;
import danhpv.dtos.LoginErrorObj;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    private static final String INVALID = "login.jsp";
    private static final String MEMBER = "ViewEventsController";
    private static final String LEADER = "ManageGroupController";
    private static final String GETLISTMEM = "ManageMemController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = INVALID;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            LoginErrorObj error = new LoginErrorObj();
            boolean valid = true;
            if (username.length() == 0) {
                error.setUsernameError("Username can not be BLANK.");
                valid = false;
            }
            if (password.length() == 0) {
                valid = false;
                error.setPasswordError("Password can not be BLANK.");
            }
            if (valid) {
                UserBlo blo = new UserBlo();
                TblUser user = blo.checkLogin(username, password);
                if (user == null) {
                    request.setAttribute("ERROR", "Username or password is invalid!!!");
                } else {
                    if (user.getStatus()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);
                        if (user.getRoleId().getRoleName().equals("Admin")) {
                            url = GETLISTMEM;
                        } else if (user.getRoleId().getRoleName().equals("Leader")) {
                            url = LEADER;
                        } else if (user.getRoleId().getRoleName().equals("Member")) {
                            url = MEMBER;
                        } else {
                            request.setAttribute("ERROR", "Your role is invalid!!!");
                        }
                    } else {
                        url = INVALID;
                        request.setAttribute("ERROR", "Your account have been blocked!!!");
                    }
                }
            }
            else {
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Username or password is invalid!!!");
            log("Error at LoginController: " + e.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
