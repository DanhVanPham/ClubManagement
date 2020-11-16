/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.UserBlo;
import danhpv.dtos.EditProfileError;
import danhpv.entities.TblUser;
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
public class UpdateAccountController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "editAccount.jsp";
    private static final String ADMIN = "ManageMemController";
    private static final String MEMBER = "ViewEventsController";
    private static final String LEADER = "ViewEventsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("txtUserID");
            String fullname = request.getParameter("txtFullname");
            String email = request.getParameter("txtEmail");
            String status = request.getParameter("cboStatus");
            String notification = request.getParameter("checkNotifi");
            boolean noti;
            if (notification == null) {
                noti = false;
            } else {
                noti = true;
            }
            boolean valid = true;
            UserBlo blo = new UserBlo();
            TblUser user = new TblUser();
            user.setUserID(userID);
            user.setFullname(fullname);
            user.setEmail(email);
            if (status.equals("ACTIVITIES")) {
                user.setStatus(true);
            } else if (status.equals("BLOCK")) {
                user.setStatus(false);
            }
            user.setGetNotification(noti);

            EditProfileError error = new EditProfileError();

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
                }
            }

            if (valid) {
                user.setAvatar("http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/game_boy_advance_7/SI_GBA_TheLegendOfZeldaTheMinishCap.jpg");
                boolean check = blo.updateProfile(user);
                if (check) {
                    HttpSession session = request.getSession();
                    TblUser tblUser = (TblUser) session.getAttribute("USER");
                    if (null != tblUser.getRoleId().getRoleID()) {
                        switch (tblUser.getRoleId().getRoleID()) {
                            case 1:
                                url = ADMIN;
                                break;
                            case 3:
                                url = MEMBER;
                                break;
                            case 2:
                                url = LEADER;
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("ERROR", "Update failed.");
                }
            } else {
                url = INVALID;
                request.setAttribute("USER", user);
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", e.getMessage());
            log("Error at UpdateAccountController: " + e.getMessage());
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
