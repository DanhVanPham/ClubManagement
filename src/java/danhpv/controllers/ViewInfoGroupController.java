/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.GroupBlo;
import danhpv.blo.GroupDetailBlo;
import danhpv.blo.UserBlo;
import danhpv.entities.TblGroup;
import danhpv.entities.TblGroupDetail;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author DELL
 */
public class ViewInfoGroupController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewGroupJoinMemDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String groupID = request.getParameter("groupID");  
            String leaderID = request.getParameter("leaderID");
            GroupBlo blo = new GroupBlo();
            GroupDetailBlo groupDetBlo = new GroupDetailBlo();
            TblGroup group = blo.findGroupByGroupID(groupID);
            if (group != null) {
                List<TblGroupDetail> listGroupDetail = groupDetBlo.getGroupDetailsByGroupID(groupID);
                if (listGroupDetail.size() > 0) {
                    request.setAttribute("GROUP_DETAIL", listGroupDetail);
                    List<String> listUser = groupDetBlo.getListMemberAdd(group, leaderID);
                    if(!listUser.isEmpty()) {
                        request.setAttribute("LIST_USER", listUser);
                    }
                }else {
                    UserBlo userBlo = new UserBlo();
                    List<String> listUser = userBlo.getListMemberAdd(null, leaderID);
                    System.out.println(listUser.size());
                    request.setAttribute("LIST_USER", listUser);
                }
                url = SUCCESS;
                request.setAttribute("GROUP", group);
            } else {
                request.setAttribute("ERROR", "Can not find group with this groupID");
            }
        } catch (Exception e) {
            log("Error at ViewInfoGroupController: " + e.getMessage());
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
