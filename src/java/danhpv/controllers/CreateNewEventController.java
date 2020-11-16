/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.NotificationsBlo;
import danhpv.dtos.EventErrorObj;
import danhpv.entities.TblEvent;
import danhpv.entities.TblNotifications;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class CreateNewEventController extends HttpServlet {

    private static final String INVALID = "formCreateEvent.jsp";
    private static final String ADMIN = "ManageEventsController";
    private static final String LEADER = "ManageGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = INVALID;
        try {
            String eventName = request.getParameter("txtEventName").trim();
            String eventDesc = request.getParameter("txtEventDesc").trim();
            String timeStartEvent = request.getParameter("txtTimeStartEvent");
            String timeEndEvent = request.getParameter("txtTimeCloseEvent");
            String timeCloseRegis = request.getParameter("txtTimeCloseRegis");
            String locator = request.getParameter("txtLocator").trim();
            String total = request.getParameter("txtTotal");
            String notification = request.getParameter("checkInternal");
            boolean noti;
            if (notification == null) {
                noti = false;
            } else {
                noti = true;
            }
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date timeStartEv = inputFormat.parse(timeStartEvent);
            Date timeEndEv = inputFormat.parse(timeEndEvent);
            Date timeEndReg = inputFormat.parse(timeCloseRegis);
            Date dateNow = new Date();
            EventErrorObj error = new EventErrorObj();
            boolean valid = true;
            if (eventName.length() == 0) {
                valid = false;
                error.setEventNameError("Event Name can not be BLANK.");
            }
            if (eventDesc.length() == 0) {
                valid = false;
                error.setEventDescError("Event Description can not be BLANK");
            }
            if (locator.length() == 0) {
                valid = false;
                error.setLocatorError("Locator can not be BLANK.");
            }
            if (total.length() == 0) {
                valid = false;
                error.setTotalError("Total can not be BLANK.");
            }
            if (timeStartEv.compareTo(dateNow) <= 0) {
                valid = false;
                error.setTimeStartEventError("Date and Time Start Event must be greater than current date time.");
            }
            if (timeEndEv.compareTo(dateNow) <= 0) {
                valid = false;
                error.setTimeCloseEventError("Date and Time End Event must be greater than current date time.");
            }
            if (timeEndReg.compareTo(dateNow) <= 0) {
                valid = false;
                error.setTimeCloseRegisError("Date and Time Close Registration must be greater than current date time.");
            }
            if (timeStartEv.compareTo(timeEndReg) <= 0) {
                valid = false;
                error.setTimeStartEventError("Date and Time Start Event must be greater than Date Time Close Registration.");
            }
            if (timeEndEv.compareTo(timeEndReg) <= 0) {
                valid = false;
                error.setTimeCloseEventError("Date and Time End Event must be greater than Date Time Close Registration.");
            }
            if (timeStartEv.compareTo(dateNow) > 0 && timeEndEv.compareTo(dateNow) > 0) {
                if (timeStartEv.compareTo(timeEndEv) >= 0) {
                    valid = false;
                    error.setTimeStartEventError("Date and Time Start Event must be smaller than Date Time End Event.");
                }
            }
            if (valid) {
                String eventID = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()).replace(" ", "-");
                System.out.println("VALID:" + valid);
                TblEvent event = new TblEvent();
                event.setEventID(eventID);
                event.setEventName(eventName);
                event.setEventDesc(eventDesc);
                event.setTimeStartEvent(timeStartEv);
                event.setTimeCloseRegister(timeEndReg);
                event.setTimeCloseEvent(timeEndEv);
                event.setLocator(locator);
                event.setTotal(Integer.parseInt(total));
                event.setIsInternal(noti);
                event.setEventStatus("REGISTER");
                event.setBanner("http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/game_boy_advance_7/SI_GBA_TheLegendOfZeldaTheMinishCap.jpg");
                EventBlo blo = new EventBlo();
                boolean check = blo.addEvent(event);
                if (check) {
                    TblNotifications notifications = new TblNotifications();
                    NotificationsBlo notificationsBlo = new NotificationsBlo();
                    String date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                    String replaceDate = date.replace(" ", "-");
                    notifications.setNotifiID(eventID + "-" + replaceDate);
                    notifications.setNotifiContent(eventName + " is coming! Register Event now.");
                    notifications.setNotifiTimeCreated(new Date());
                    notifications.setEventID(event);
                    notifications.setIsInternal(noti);
                    System.out.println("NOTI: " + notifications.getNotifiID());
                    boolean checkAddNoti = notificationsBlo.addNotifications(notifications);
                    if (checkAddNoti) {
                        HttpSession session = request.getSession();
                        TblUser user = (TblUser) session.getAttribute("USER");
                        if (user.getRoleId().getRoleName().equals("Admin")) {
                            url = ADMIN;
                        } else if (user.getRoleId().getRoleName().equals("Leader")) {
                            url = LEADER;
                        } else {
                            request.setAttribute("ERROR", "Something is wrong");
                        }
                    } else {
                        request.setAttribute("ERROR", "Add notifications failed.");
                    }
                } else {
                    request.setAttribute("ERROR", "Create New Event failed.Some thing is wrong, check again.");
                }
            } else {
                request.setAttribute("INVALID", error);
                request.setAttribute("INTERNAL", noti);
            }
        } catch (Exception e) {
            log("Error at CreateNewEventController: " + e.getMessage());
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
