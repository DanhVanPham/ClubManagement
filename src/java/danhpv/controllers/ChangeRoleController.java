/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.UserBlo;
import danhpv.entities.TblRole;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class ChangeRoleController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManageMemController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userId");
            String role = request.getParameter("cboRole");
            int roleID = 0;
            if (role.equals("Admin")) {
                roleID = 1;
            } else if (role.equals("Leader")) {
                roleID = 2;
            } else if (role.equals("Member")) {
                roleID = 3;
            }
            UserBlo blo = new UserBlo();
            TblUser user = blo.findUserByUserID(userID);
            if (user != null) {
                TblRole rol = new TblRole(roleID, role);
                if (!user.getRoleId().getRoleName().equals(rol.getRoleName())) {
                    user.setRoleId(rol);
                    System.out.println(user.getRoleId().getRoleName());
                    if (blo.changeRole(user)) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Change Role failed");
                    }
                }
                else {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR", "Can not find User with this ID");
            }
        } catch (Exception e) {
            log("Error at ChangeRoleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
