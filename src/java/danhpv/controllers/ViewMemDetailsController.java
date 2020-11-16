/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.GroupDetailBlo;
import danhpv.blo.UserBlo;
import danhpv.entities.TblGroupDetail;
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
public class ViewMemDetailsController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewMember_Details.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String groupID = request.getParameter("groupID");
            UserBlo userBlo = new UserBlo();
            TblUser user = userBlo.findUserByUserID(userID);
            if (groupID  != null) {
                GroupDetailBlo groupDetailBlo = new GroupDetailBlo();
                TblGroupDetail groupdetail = groupDetailBlo.findGroupDetailByGroupIDAndMemID(groupID, userID, null);
                if (groupdetail != null) {
                    request.setAttribute("GROUP_DETAIL", groupdetail);
                    url = SUCCESS;
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Can not find user details with this id");
                }
            }
            if (user != null) {
                request.setAttribute("USER_DETAIL", user);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Can not find user details with this id");
            }
            System.out.println(url);
        } catch (Exception e) {
            log("Error at ViewMemDetailsController: " + e.getMessage());
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
