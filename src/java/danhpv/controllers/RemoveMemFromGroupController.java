/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.GroupBlo;
import danhpv.blo.GroupDetailBlo;
import danhpv.entities.TblGroup;
import danhpv.entities.TblGroupDetail;
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
public class RemoveMemFromGroupController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewInfoGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String groupID = request.getParameter("groupID");
            GroupDetailBlo groupDetailBlo = new GroupDetailBlo();
            GroupBlo groupBlo = new GroupBlo();
            TblGroupDetail groupDetail = groupDetailBlo.findGroupDetailByGroupIDAndMemID(groupID, userID, "ACTIVE");
            if (groupDetail != null) {
                boolean check = groupDetailBlo.removeMemFromGroup(groupDetail);
                if (check) {
                    boolean checkUpdate = groupBlo.differenceTotalMember(groupID);
                    if (checkUpdate) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Remove member from group failed.");
                    }
                } else {
                    request.setAttribute("ERROR", "Remove member from group failed.");
                }
            } else {
                request.setAttribute("ERROR", "Can not find Group Details.");
            }
        } catch (Exception e) {
            log("Error at RemoveMemFromGroupController: " + e.getMessage());
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
