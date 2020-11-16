/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.CommentBlo;
import danhpv.blo.EventBlo;
import danhpv.blo.UserBlo;
import danhpv.dtos.EditProfileError;
import danhpv.entities.TblComment;
import danhpv.entities.TblEvent;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class CommentEventController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewEventsDetailsController";
    private static final String INVALID = "ViewEventsDetailsController";

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
            String content = request.getParameter("content").trim();
            String eventID = request.getParameter("eventID");
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

            if (content.length() == 0) {
                valid = false;
                error.setContentError("Content can not be BLANK.");
            } else {
                if (content.length() > 100) {
                    valid = false;
                    error.setContentError("Length of content smaller or equal 100.");
                }
            }
            UserBlo userBlo = new UserBlo();
            TblUser user = new TblUser();
            
            if (valid) {
                EventBlo eventBlo = new EventBlo();
                TblEvent event = eventBlo.findEventByEventID(eventID);
                if (event != null) {
                    TblComment comment = new TblComment();
                    try {
                        user = userBlo.findUserByUserID(userID);
                        comment.setMemberID(user);
                        comment.setStudentID(userID);
                    } catch (Exception e) {
                        comment.setStudentID(userID);
                    }
                    comment.setFullname(fullname);
                    comment.setEmail(email);
                    comment.setEventID(event);
                    comment.setContent(content);
                    comment.setTimeComment(new Date());
                    CommentBlo commentBlo = new CommentBlo();
                    boolean check = commentBlo.addComment(comment);
                    if (check) {
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Add Comment failed.");
                    }
                } else {
                    request.setAttribute("ERROR", "Add Comment failed.");
                }

            } else {
                url = INVALID;
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            url = ERROR;
            log("Error at CommentEventController: " + e.getMessage());
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
