/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.GroupBlo;
import danhpv.entities.TblGroup;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class SearchGroupController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "manageGroups.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            int index = 1;
            String getIndex = request.getParameter("pageIndex");
            if (getIndex != null) {
                try {
                    index = Integer.parseInt(getIndex);
                } catch (Exception e) {
                    index = 1;
                }
            }
            List<TblGroup> listPaging = null;
            String txtSearch = request.getParameter("txtSearch");
            if ((txtSearch == null) || txtSearch.length() == 0) {
                GroupBlo blo = new GroupBlo();
                //tìm số lượng cần phân trang
                long countAll = blo.getCountAllGroup();
                int count = (int) countAll;
                if (count == 0) {
                    listPaging = new ArrayList<>();
                } else {
                    int pageSize = 4;
                    int endPage = 0;
                    endPage = count / pageSize;
                    if (count % pageSize != 0) {
                        endPage++;
                    }
                    listPaging = blo.getPaging((index - 1) * pageSize, pageSize);
                    request.setAttribute("ENDPAGE", endPage);
                }
            } else {
                GroupBlo event = new GroupBlo();
                long countAll = event.getCountAllGroupBySearchName(txtSearch);
                int count = (int) countAll;
                if (count == 0) {
                    listPaging = new ArrayList<>();
                } else {
                    int pageSize = 4;
                    int endPage = 0;
                    endPage = count / pageSize;
                    if (count % pageSize != 0) {
                        endPage++;
                    }
                    listPaging = event.getPagingBySearchName(txtSearch, (index - 1) * pageSize, pageSize);
                    request.setAttribute("ENDPAGE", endPage);
                }
            }
            request.setAttribute("LIST_GROUP", listPaging);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchGroupController: " + e.getMessage());
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
