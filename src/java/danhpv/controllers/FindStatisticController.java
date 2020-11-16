/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.dtos.EventErrorObj;
import danhpv.entities.TblEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class FindStatisticController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewStatistic.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String dateFrom = request.getParameter("txtTimeFrom");
            String dateTo = request.getParameter("txtTimeTo");
            System.out.println(dateFrom);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateF = format.parse(dateFrom);
                Date dateT = format.parse(dateTo);
                Date dateNow = new Date();
                EventErrorObj error = new EventErrorObj();
                boolean valid = true;
                if (dateF.compareTo(dateNow) >= 0) {
                    valid = false;
                    error.setTimeStartEventError("Required input date from smaller than current time");
                }
                if (dateF.compareTo(dateT) > 0) {
                    valid = false;
                    error.setTimeCloseEventError("Required input date to greater than date from.");
                }
                if (valid) {
                    int index = 1;
                    String getIndex = request.getParameter("pageIndex");
                    if (getIndex != null) {
                        try {
                            index = Integer.parseInt(getIndex);
                        } catch (Exception e) {
                            index = 1;
                        }
                    }
                    EventBlo blo = new EventBlo();
                    //tìm số lượng cần phân trang
                    long countAllInterval = blo.getCountAllEventInTheInterval(dateF, dateT);
                    int count = (int) countAllInterval;
                    if (count == 0) {
                        url = SUCCESS;
                        request.setAttribute("ERROR1", "Can not find event in the interval.");
                    } else {
                        int pageSize = 4;
                        int endPage = 0;
                        endPage = count / pageSize;
                        if (count % pageSize != 0) {
                            endPage++;
                        }
                        List<TblEvent> listEvent = blo.getPagingEventInterval(dateF, dateT, (index - 1) * pageSize, pageSize);
                        List<TblEvent> listAllEvents = blo.getPagingEventInterval(dateF, dateT, 0, (int)countAllInterval);
                        if (listEvent != null && !listEvent.isEmpty() && listAllEvents != null && !listAllEvents.isEmpty()) {
                            url = SUCCESS;
                            request.setAttribute("ENDPAGE", endPage);
                            request.setAttribute("LIST_EVENTS", listEvent);
                            request.setAttribute("LIST_ALL_EVENTS", listAllEvents);
                        } else {
                            url = SUCCESS;
                            request.setAttribute("ERROR1", "Can not find event in the interval.");
                        }
                    }
                    // List<TblEvent> listEvent = new EventBlo().findAllEventInTheInterval(dateF, dateT);
                } else {
                    url = SUCCESS;
                    request.setAttribute("INVALID", error);
                }
            } catch (Exception e) {
                log("Error at FindStatisticController: " + e.getMessage());
                url = ERROR;
                request.setAttribute("ERROR", "Some thing is wrong. Check again!");
            }
        } catch (Exception e) {
            url = ERROR;
            request.setAttribute("ERROR", "Some thing is wrong. Check again!");
            log("Error at FindStatisticController: " + e.getMessage());
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
