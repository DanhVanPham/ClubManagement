/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.EventDetailBlo;
import danhpv.dtos.CartObj;
import danhpv.entities.TblEvent;
import danhpv.entities.TblEventDetail;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AddEventToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewEventsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartObj shoppingCart = (CartObj) session.getAttribute("cart");
            if (shoppingCart == null) {
                shoppingCart = new CartObj();
            }
            String eventID = request.getParameter("eventID");
            EventBlo blo = new EventBlo();
            TblEvent event = blo.findEventByEventID(eventID);
            if (event != null) {
                if (event.getNumRegister() == event.getTotal()) {
                    request.setAttribute("ERROR", "Number Register Of Event is full. Can not register.");
                } else {
                    if (session.getAttribute("USER") == null) {
                        if (shoppingCart.checkExistedCart(event)) {
                            request.setAttribute("ERROR", "Event have been existed in the cart.");
                        } else {
                            boolean checkOverlapDate = false;
                            for (TblEvent eventCart : shoppingCart.getCart().values()) {
                                if (event.getTimeStartEvent().compareTo(eventCart.getTimeCloseEvent()) <= 0
                                        && event.getTimeCloseEvent().compareTo(eventCart.getTimeStartEvent()) >= 0) {
                                    checkOverlapDate = true;
                                    break;
                                }
                            }
                            if (checkOverlapDate) {
                                request.setAttribute("ERROR", "The event timing coincides with other events you have added in the cart.");
                            } else {
                                if (shoppingCart.addCart(event)) {
                                    session.setAttribute("cart", shoppingCart);
                                    url = SUCCESS;
                                } else {
                                    request.setAttribute("ERROR", "Event have been existed in the cart.");
                                }
                            }
                        }
                    } else {
                        TblUser user = (TblUser) session.getAttribute("USER");
                        EventDetailBlo evDetailBlo = new EventDetailBlo();
                        List<TblEventDetail> eventDetail = evDetailBlo.getListEventDetailByEventIDAndUserID(eventID, user);
                        if (eventDetail.isEmpty()) {
                            List<TblEventDetail> listEventRegis = evDetailBlo.getListEventDetailByUserID(user.getUserID());
                            if (listEventRegis.isEmpty()) {
                                if (shoppingCart.checkExistedCart(event)) {
                                    request.setAttribute("ERROR", "Event have been existed in the cart.");
                                } else {
                                    boolean checkOverlapDate = false;
                                    for (TblEvent eventCart : shoppingCart.getCart().values()) {
                                        if (event.getTimeStartEvent().compareTo(eventCart.getTimeCloseEvent()) <= 0
                                                && event.getTimeCloseEvent().compareTo(eventCart.getTimeStartEvent()) >= 0) {
                                            checkOverlapDate = true;
                                            break;
                                        }
                                    }
                                    if (checkOverlapDate) {
                                        request.setAttribute("ERROR", "The event timing coincides with other events you have added in the cart.");
                                    } else {
                                        if (shoppingCart.addCart(event)) {
                                            session.setAttribute("cart", shoppingCart);
                                            url = SUCCESS;
                                        } else {
                                            request.setAttribute("ERROR", "Event have been existed in the cart.");
                                        }
                                    }
                                }
                            } else {
                                if (shoppingCart.checkExistedCart(event)) {
                                    request.setAttribute("ERROR", "Event have been existed in the cart.");
                                } else {
                                    boolean checkOverlapDateCart = false;
                                    for (TblEvent eventCart : shoppingCart.getCart().values()) {
                                        if (event.getTimeStartEvent().compareTo(eventCart.getTimeCloseEvent()) <= 0
                                                && event.getTimeCloseEvent().compareTo(eventCart.getTimeStartEvent()) >= 0) {
                                            checkOverlapDateCart = true;
                                            break;
                                        }
                                    }
                                    if (checkOverlapDateCart) {
                                        request.setAttribute("ERROR", "The event timing coincides with other events you have added in the cart.");
                                    } else {
                                        boolean checkOverlapDate = false;
                                        for (TblEventDetail evRegis : listEventRegis) {
                                            if (event.getTimeStartEvent().compareTo(evRegis.getEventID().getTimeCloseEvent()) <= 0
                                                    && event.getTimeCloseEvent().compareTo(evRegis.getEventID().getTimeStartEvent()) >= 0) {
                                                checkOverlapDate = true;
                                                break;
                                            }
                                        }
                                        if (checkOverlapDate) {
                                            request.setAttribute("ERROR", "The event timing coincides with other events you have registered.");
                                        } else {
                                            if (shoppingCart.addCart(event)) {
                                                session.setAttribute("cart", shoppingCart);
                                                url = SUCCESS;
                                            } else {
                                                request.setAttribute("ERROR", "Event have been existed in the cart.");
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            request.setAttribute("ERROR", "You have registered for the event.");
                        }
                    }
                }
            } else {
                request.setAttribute("ERROR", "Can not find event with this id");
            }
        } catch (Exception e) {
            log("ERROR at AddEventToCartController: " + e.getMessage());
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
