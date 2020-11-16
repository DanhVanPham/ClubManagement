/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.GroupBlo;
import danhpv.blo.GroupDetailBlo;
import danhpv.entities.TblGroup;
import danhpv.entities.TblGroupDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class RemoveGroupController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManageGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String groupID = request.getParameter("groupID");
            GroupBlo blo = new GroupBlo();
            GroupDetailBlo groupDetailBlo = new GroupDetailBlo();
            boolean checkRmv = true;
            TblGroup group = blo.findGroupByGroupID(groupID);
            if (group != null) {
                //check groupID có trong TblGroupDetail
                List<TblGroupDetail> list = groupDetailBlo.getGroupDetailsByGroupID(groupID);
                if (list.size() != 0) {
                    for (TblGroupDetail tblGroupDetail : list) {
                        checkRmv = groupDetailBlo.deleteGroupDetails(tblGroupDetail);
                        if (!checkRmv) {
                            break;
                        }
                    }
                }
                if (checkRmv) {
                    boolean check = blo.deleteGroup(group);
                    if (check) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Remove Group Failed.");
                    }
                }
            } else {
                request.setAttribute("ERROR", "Can not find Group with this ID");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Remove Group Failed.");
            log("Error at RemoveGroupController: " + e.getMessage());
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
