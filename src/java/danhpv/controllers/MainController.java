/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SEARCH_EVENTS = "ViewEventsController";
    private static final String VIEW_EVENTS = "ViewEventsController";
    private static final String LOGIN = "login.jsp";
    private static final String LOGIN_CONTROL = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String EDIT_PROFILE = "EditProfController";
    private static final String MANAGE_MEM = "ManageMemController";
    private static final String MANAGE_EVENT = "ManageEventsController";
    private static final String CHANGE_STATUS = "ChangeStatusController";
    private static final String EDIT_ACCOUNT = "EditAccountController";
    private static final String UPDATE_ACCOUNT = "UpdateAccountController";
    private static final String CHANGE_PICTURE = "ChangePictureController";
    private static final String SEARCH_NAME = "SearchNameController";
    private static final String FORM_CREATE = "formCreate.jsp";
    private static final String CREATE_ACCOUNT = "CreateAccountController";
    private static final String ADD_EVENT_TO_CART = "AddEventToCartController";
    private static final String REMOVE_EVENT_CART = "RemoveEventCartController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String VIEW_EVENTS_DETAIL = "ViewEventsDetailsController";
    private static final String REGISTER_EVENTS = "RegisterEventsController";
    private static final String CHANGE_ROLE = "ChangeRoleController";
    private static final String MANAGE_EVENTS_REMOVE = "RemoveEventFromAdminController";
    private static final String FORM_CREATE_EVENT = "formCreateEvent.jsp";
    private static final String CREATE_NEW_EVENT = "CreateNewEventController";
    private static final String VIEW_GROUP_JOIN = "ViewGroupJoinController";
    private static final String GET_INFO_GROUP = "ViewInfoGroupController";
    private static final String LEAVE_GROUP = "LeaveTheGroupController";
    private static final String MANGAGE_GROUP = "ManageGroupController";
    private static final String CREATE_NEW_GROUP = "CreateNewGroupController";
    private static final String REMOVE_GROUP = "RemoveGroupController";
    private static final String FORM_CREATE_GROUP = "formCreateGroup.jsp";
    private static final String FORM_EDIT_GROUP = "editGroup.jsp";
    private static final String ADD_MEM_TO_CART = "AddMemToCartController";
    private static final String REGISTER_MEMBER = "RegisterMemberController";
    private static final String VIEW_CART_MEMBER = "viewCartMember.jsp";
    private static final String REMOVE_MEMBER_CART = "RemoveMemberFromCartController";
    private static final String VIEW_MEM_DETAILS = "ViewMemDetailsController";
    private static final String REMOVE_MEM_GROUP = "RemoveMemFromGroupController";
    private static final String COMMENT_EVENT = "CommentEventController";
    private static final String VIEW_EVENT_REPORT = "ViewEventReportController";
    private static final String SEARCH_GROUP = "SearchGroupController";
    private static final String VIEW_HISTORY_JOIN_EVENT = "ViewHistoryJoinController";
    private static final String VIEW_NOTIFICATION = "ViewNotificationController";
    private static final String VIEW_STATISTIC = "viewStatistic.jsp";
    private static final String FIND_STATISTIC = "FindStatisticController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("View Events")) {
                url = VIEW_EVENTS;
            } else if (action.equals("Search Events")) {
                url = SEARCH_EVENTS;
            } else if (action.equals("Login Account")) {
                url = LOGIN;
            } else if (action.equals("Login")) {
                url = LOGIN_CONTROL;
            } else if (action.equals("Log out")) {
                url = LOGOUT;
            } else if (action.equals("EditProfile")) {
                url = EDIT_PROFILE;
            } else if (action.equals("Manage Member")) {
                url = MANAGE_MEM;
            } else if (action.equals("Change Status")) {
                url = CHANGE_STATUS;
            } else if (action.equals("Manage Event")) {
                url = MANAGE_EVENT;
            } else if (action.equals("Edit Account")) {
                url = EDIT_ACCOUNT;
            } else if (action.equals("ChangeImage")) {
                url = CHANGE_PICTURE;
            } else if (action.equals("UpdateAccount")) {
                url = UPDATE_ACCOUNT;
            } else if (action.equals("SearchName")) {
                url = SEARCH_NAME;
            } else if (action.equals("FormAccount")) {
                url = FORM_CREATE;
            } else if (action.equals("Create Account")) {
                url = CREATE_ACCOUNT;
            } else if (action.equals("AddEventToCart")) {
                url = ADD_EVENT_TO_CART;
            } else if (action.equals("Remove Event From Cart")) {
                url = REMOVE_EVENT_CART;
            } else if (action.equals("viewCart")) {
                url = VIEW_CART;
            } else if (action.equals("ViewEventDetails")) {
                url = VIEW_EVENTS_DETAIL;
            } else if (action.equals("Register Event")) {
                url = REGISTER_EVENTS;
            } else if (action.equals("Change Role")) {
                url = CHANGE_ROLE;
            } else if (action.equals("RemoveEventFromAdmin")) {
                url = MANAGE_EVENTS_REMOVE;
            } else if (action.equals("Form Create Event")) {
                url = FORM_CREATE_EVENT;
            } else if (action.equals("CreateNewEvent")) {
                url = CREATE_NEW_EVENT;
            } else if (action.equals("FormCreateGroup")) {
                url = FORM_CREATE_GROUP;
            } else if (action.equals("CreateNewGroup")) {
                url = CREATE_NEW_GROUP;
            } else if (action.equals("EditGroup")) {
                url = FORM_EDIT_GROUP;
            } else if (action.equals("RemoveGroup")) {
                url = REMOVE_GROUP;
            } else if (action.equals("viewGroupJoin")) {
                url = VIEW_GROUP_JOIN;
            } else if (action.equals("ViewInfoGroup")) {
                url = GET_INFO_GROUP;
            } else if (action.equals("LeaveTheGroup")) {
                url = LEAVE_GROUP;
            } else if (action.equals("ManageGroup")) {
                url = MANGAGE_GROUP;
            } else if (action.equals("AddMemToCart")) {
                url = ADD_MEM_TO_CART;
            } else if (action.equals("RegisterMember")) {
                url = REGISTER_MEMBER;
            } else if (action.equals("viewCartMember")) {
                url = VIEW_CART_MEMBER;
            } else if (action.equals("RemoveMemberFromCart")) {
                url = REMOVE_MEMBER_CART;
            } else if (action.equals("ViewMemDetails")) {
                url = VIEW_MEM_DETAILS;
            } else if (action.equals("RemoveMemFromGroup")) {
                url = REMOVE_MEM_GROUP;
            } else if (action.equals("CommentEvent")) {
                url = COMMENT_EVENT;
            } else if (action.equals("ViewEventReport")) {
                url = VIEW_EVENT_REPORT;
            } else if (action.equals("Search Group")) {
                url = SEARCH_GROUP;
            } else if (action.equals("ViewHistoryJoin")) {
                url = VIEW_HISTORY_JOIN_EVENT;
            } else if (action.equals("viewNotification")) {
                url = VIEW_NOTIFICATION;
            } else if (action.equals("ViewStatistic")) {
                url = VIEW_STATISTIC;
            } else if (action.equals("FindStatistic")) {
                url = FIND_STATISTIC;
            } else {
                request.setAttribute("ERROR", "Your action is invalid!");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
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
