/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.EventBlo;
import danhpv.blo.EventDetailBlo;
import danhpv.blo.UserBlo;
import danhpv.dtos.CartObj;
import danhpv.entities.TblUser;
import danhpv.entities.TblEventDetail;
import danhpv.dtos.EditProfileError;
import danhpv.entities.TblEvent;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class RegisterEventsController extends HttpServlet {

    private static final String INVALID = "viewCart.jsp";
    private static final String SUCCESS = "ViewEventsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = INVALID;
        try {
            String userID = request.getParameter("txtUserID").trim();
            String fullname = request.getParameter("txtFullname").trim();
            String email = request.getParameter("txtEmail").trim();
            EditProfileError error = new EditProfileError();
            HttpSession session = request.getSession();
            boolean valid = true;
            if (userID.length() == 0) {
                valid = false;
                error.setUserIDError("UserID can not be BLANK.");
            } else {
                if (userID.length() > 8) {
                    valid = false;
                    error.setUserIDError("Length of UserID smaller or equal 8.");
                } else {
                    if (!userID.matches("^SE\\d{6}$")) {
                        valid = false;
                        error.setUserIDError("Required input UserID with format (SE + 6 digit numbers)");
                    } else {
                        if (session.getAttribute("USER") == null) {
                            try {
                                TblUser checkUserID = new UserBlo().findUserByUserID(userID);
                                if (checkUserID != null) {
                                    valid = false;
                                    error.setUserIDError("UserID have been registered account in system. Required log in account and register event.");
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }

            if (fullname.length() == 0) {
                valid = false;
                error.setFullnameError("Fullname can not be BLANK.");
            } else {
                if (fullname.length() > 30) {
                    valid = false;
                    error.setFullnameError("Length of Fullname smaller or equal 30.");
                }
            }

            if (email.length() == 0) {
                valid = false;
                error.setEmailError("Email can not be BLANK.");
            } else if (email.length() > 40) {
                valid = false;
                error.setEmailError("Length of Email smaller or equal 40.");
            } else {
                if (!email.matches("^[a-zA-Z0-9]*?@fpt.edu\\.vn")) {
                    valid = false;
                    error.setEmailError("Email is invalid. Required input email fpt edu! Enter email with format(abc@fpt.edu.vn)");
                } else {
                    if (session.getAttribute("USER") == null) {
                        try {
                            TblUser checkEmail = new UserBlo().checkConfirmEmail(email);
                            if (checkEmail != null) {
                                valid = false;
                                error.setEmailError("Email used existed in system.");
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            if (valid) {
                UserBlo userBlo = new UserBlo();
                TblUser userFind = new TblUser();
                try {
                    userFind = userBlo.findUserByUserID(userID);
                } catch (Exception e) {
                    userFind = null;
                }
                TblUser user = new TblUser();
                user.setUserID(userID);
                user.setFullname(fullname);
                user.setEmail(email);
                CartObj shoppingCart = (CartObj) session.getAttribute("cart");
                EventDetailBlo blo = new EventDetailBlo();
                boolean check, checkRegistered = false;
                for (TblEvent event : shoppingCart.getCart().values()) {
                    if (blo.checkUserIDRegisteredEvent(event.getEventID(), userID)) {
                        error.setUserIDError("UserID have been registed event.");
                        checkRegistered = true;
                        break;
                    }
                    if (blo.checkEmailRegisteredEvent(event.getEventID(), email)) {
                        error.setEmailError("Email have been registered event.");
                        checkRegistered = true;
                        break;
                    }
                }
                if (checkRegistered) {
                    request.setAttribute("INVALID", error);
                } else {
                    check = blo.registerEvent(user, shoppingCart.getCart(), userFind);
                    if (check) {
                        url = SUCCESS;
                        session.removeAttribute("cart");
                        request.setAttribute("SUCCESS", "Register Event successful.");
                    }
                }
            } else {
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            log("ERROR at RegisterEvents Controller: " + e.getMessage());
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
