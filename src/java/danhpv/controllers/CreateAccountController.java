/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.UserBlo;
import danhpv.dtos.EditProfileError;
import danhpv.entities.TblRole;
import danhpv.entities.TblUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class CreateAccountController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "formCreate.jsp";
    private static final String SUCCESS = "ManageMemController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("txtUserID").trim();
            String password = request.getParameter("txtPassword").trim();
            String fullname = request.getParameter("txtFullname").trim();
            String email = request.getParameter("txtEmail").trim();
            String role = request.getParameter("cboRole");
            int roleId = 0;
            System.out.println(fullname);
            if (role.equals("Admin")) {
                roleId = 1;
            } else if (role.equals("Leader")) {
                roleId = 2;
            } else if (role.equals("Member")) {
                roleId = 3;
            }
            boolean noti, statu = true;
            String notification = request.getParameter("checkNotifi");

            if (notification == null) {
                noti = false;
            } else {
                noti = true;
            }
            TblUser user = new TblUser();
            user.setGetNotification(noti);
            boolean valid = true;
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
                    } else {
                        try {
                            TblUser checkUserID = new UserBlo().findUserByUserID(userID);
                            if (checkUserID != null) {
                                valid = false;
                                error.setUserIDError("UserID have been existed in system.");
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            if (password.length() == 0) {
                valid = false;
                error.setPasswordError("Password can not be BLANK.");
            } else {
                if (password.length() > 20) {
                    valid = false;
                    error.setPasswordError("Length of Password smaller or equal 20.");
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
            if (valid) {
                TblRole tblRole = new TblRole(roleId, role);
                user.setUserID(userID);
                user.setPassword(password);
                user.setFullname(fullname);
                user.setEmail(email);
                user.setRoleId(tblRole);
                user.setStatus(statu);
                user.setAvatar("http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/game_boy_advance_7/SI_GBA_TheLegendOfZeldaTheMinishCap.jpg");
                UserBlo blo = new UserBlo();
                boolean check = blo.add(user);
                if (check) {
                    url = SUCCESS;
                } else {
                    url = INVALID;
                    request.setAttribute("ERROR", "Create new account failed.");
                }
            } else {
                url = INVALID;
                request.setAttribute("USER", user);
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", e.getMessage());
            log("Error at CreateAccountController: " + e);
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
