<%-- 
    Document   : editAccount
    Created on : Oct 17, 2020, 12:46:45 AM
    Author     : DELL
--%>

<%@page import="danhpv.entities.TblUser"%>
<%@page import="danhpv.blo.UserBlo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Edit Profile Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <% response.sendRedirect("login.jsp");%>
        </c:if>
        <c:if test="${not empty sessionScope.USER}">
            <input type="hidden" name="BACK" value="leader.jsp"/>
            <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
                <jsp:include page="navBarAfLoginMem.jsp"/>
            </c:if>
            <c:if test="${sessionScope.USER.roleId.roleName eq 'Admin'}">
                <jsp:include page="navBarAfLogin.jsp"/>     
            </c:if>
            <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}">
                <jsp:include page="navBarAfLoginLeader.jsp"/>     
            </c:if>
            <c:if test="${requestScope.USER == null}">
                <% response.sendRedirect("leader.jsp");%>
            </c:if>
            <c:if test="${requestScope.USER != null}">
                <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Edit Profile</h3>   
                <div class="form-group" >
                    <c:if test="${not empty requestScope.USER.avatar && requestScope.USER.avatar != null}" var="checkAvatar">
                        <img src="${requestScope.USER.avatar}" alt="Logo" style="width:25%;display: block;margin-left: auto;margin-right: auto;margin-top: 2%;"/>
                    </c:if>
                    <c:if test="${!checkAvatar}">
                        <img src="${param.txtAvartar}" alt="Logo" style="width:25%;display: block;margin-left: auto;margin-right: auto;margin-top: 2%;"/>
                    </c:if>
                </div>             
                <form style="margin-left: 20%;" action="MainController" method="POST">
                    <div class="form-group">
                        <label for="exampleInputEmail1">UserID:</label>
                        <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER.userID}" required readonly="true" style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.userIDError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Fullname:</label>
                        <input type="text" name="txtFullname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Fullname" value="${requestScope.USER.fullname}" required style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.fullnameError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address:</label>
                        <input type="email" name="txtEmail" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" readonly="true" placeholder="Enter Email Address" value="${requestScope.USER.email}" required style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.emailError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Status</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="cboStatus" style="width: 70%;">                            
                            <option <c:if test="${requestScope.USER.status}"> selected="true"</c:if>>ACTIVITIES</option>
                            <option <c:if test="${!requestScope.USER.status}"> selected="true"</c:if>>BLOCK</option>
                            </select>
                        </div>
                        <div class="form-group form-check">
                        <c:if test="${requestScope.USER.getNotification}" var="check">
                        </c:if>
                        <c:if test="${check}">
                            <input type="checkbox" name="checkNotifi" class="form-check-input" id="exampleCheck1" checked />
                        </c:if>
                        <c:if test="${!check}">
                            <input type="checkbox" name="checkNotifi" class="form-check-input" id="exampleCheck1" />
                        </c:if>
                        <label class="form-check-label" for="exampleCheck1">Get Notification</label>
                    </div>
                    <font color="red">${requestScope.ERROR}</font>
                            <c:if test="${checkAvatar}">
                                <input type="hidden" name="txtAvartar" value="${requestScope.USER.avatar}"/>
                            </c:if>
                            <c:if test="${!checkAvatar}">
                                <input type="hidden" name="txtAvartar" value="${param.txtAvartar}"/>
                            </c:if>
                    <input type="submit" name="action" value="UpdateAccount" class="btn btn-primary"/>
                </form>
            </c:if>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
