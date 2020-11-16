/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.NotificationsBlo;
import danhpv.entities.TblEvent;
import danhpv.entities.TblNotifications;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DELL
 */
public class RemoveEventFromAdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManageEventsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String eventID = request.getParameter("eventID");
            EventBlo blo = new EventBlo();
            TblEvent event = blo.findEventByEventID(eventID);
            if (event != null) {
                if (!event.getEventStatus().equalsIgnoreCase("DELETED")) {
                    if (blo.deleteEvent(event)) {
                        TblNotifications notifications = new TblNotifications();
                        NotificationsBlo notificationsBlo = new NotificationsBlo();
                        notifications.setNotifiID(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
                        notifications.setNotifiContent(event.getEventName() + " is cancel!");
                        notifications.setNotifiTimeCreated(new Date());
                        notifications.setEventID(event);
                        notifications.setIsInternal(event.getIsInternal());
                        boolean checkAddNoti = notificationsBlo.addNotifications(notifications);
                        if (checkAddNoti) {
                            url = SUCCESS;
                        } else {
                            request.setAttribute("ERROR", "Add notifications failed.");
                        }
                    } else {
                        request.setAttribute("ERROR", "Remove Event Failed.");
                    }
                }
                else {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at RemoveEventFromAdminController: " + e.getMessage());
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
