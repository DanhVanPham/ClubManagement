/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.controllers;

import danhpv.blo.GroupBlo;
import danhpv.blo.GroupDetailBlo;
import danhpv.blo.UserBlo;
import danhpv.dtos.GroupErrorObj;
import danhpv.entities.TblGroup;
import danhpv.entities.TblGroupDetail;
import danhpv.entities.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class CreateNewGroupController extends HttpServlet {
    
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ManageGroupController";
    private static final String INVALID = "formCreateGroup.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = INVALID;       
        try {
            String groupID = request.getParameter("txtGroupID").trim();
            String groupName = request.getParameter("txtGroupName").trim();
            String groupDesc = request.getParameter("txtGroupDesc").trim();
            String leaderID = request.getParameter("txtleaderID").trim();
            boolean valid = true;
            GroupErrorObj error = new GroupErrorObj();
            if (groupID.length() == 0) {
                valid = false;
                error.setGroupID("GroupID can not be Blank");
            } else {
                if (groupID.length() > 20) {
                    valid = false;
                    error.setGroupID("Length of GroupID smaller or equal 20");
                } else {
                    try {
                        TblGroup checkGroupExisted = new GroupBlo().findGroupByGroupID(groupID);
                        if (checkGroupExisted != null) {
                            valid = false;
                            error.setGroupID("GroupID existed in the system. Input another GroupID");
                        }
                    } catch (Exception e) {
                    }
                }
            }
            if (groupName.length() == 0) {
                valid = false;
                error.setGroupName("Group Name can not be Blank");
            } else {
                if (groupName.length() > 30) {
                    valid = false;
                    error.setGroupName("Length of Group Name smaller or equal 30");
                }
            }
            if (groupDesc.length() == 0) {
                valid = false;
                error.setGroupDesc("Group Description can not be Blank");
            } else {
                if (groupDesc.length() > 100) {
                    valid = false;
                    error.setGroupDesc("Length of Group Description smaller or equal 100");
                }
            }
            if (valid) {
                UserBlo userBlo = new UserBlo();
                TblUser user = userBlo.findUserByUserID(leaderID);
                if (user != null) {
                    TblGroup group = new TblGroup();
                    group.setGroupID(groupID);
                    group.setGroupName(groupName);
                    group.setGroupDesc(groupDesc);
                    group.setLeaderId(user);
                    GroupBlo groupBlo = new GroupBlo();
                    boolean check = groupBlo.add(group);
                    if (check) {
                        TblGroupDetail groupDetail = new TblGroupDetail();
                        groupDetail.setGroupID(group);
                        groupDetail.setMemberID(user);
                        groupDetail.setStatus("ACTIVE");
                        Date date = new Date();
                        groupDetail.setAddTime(date);
                        HashMap<String, TblUser> lsUser = new HashMap<>();
                        lsUser.put(user.getUserID(), user);
                        GroupDetailBlo groupDetailBlo = new GroupDetailBlo();
                        boolean checkAdd = groupDetailBlo.registerMember(lsUser, groupID);
                        if (checkAdd) {
                            url = SUCCESS;
                        } else {
                            request.setAttribute("ERROR", "Register Member for leader failed.");
                        }
                    } else {
                        request.setAttribute("ERROR", "Create new group failed.");
                    }
                } else {
                    request.setAttribute("ERROR", "Can not find User");
                }
            } else {
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            url = ERROR;
            log("Error at CreateNewGroupController: " + e.getMessage());
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
