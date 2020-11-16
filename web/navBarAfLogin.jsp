<%-- 
    Document   : navBarAfLogin
    Created on : Oct 16, 2020, 10:03:19 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>        
        <c:if test="${empty sessionScope.USER}">
            <% response.sendRedirect("login.jsp");%>
        </c:if>
        <c:if test="${not empty sessionScope.USER}"> 
            <c:url value="MainController" var="linkLogout">
                <c:param name="action" value="Log out"/>
                <c:param name="txtUsername" value="${sessionScope.USER.userID}"/>
            </c:url>
            <c:url value="MainController" var="linkEditProfile">
                <c:param name="action" value="Edit Account"/>
                <c:param name="userId" value="${sessionScope.USER.userID}"/>
            </c:url>
            <c:url value="MainController" var="linkCreateAccount">
                <c:param name="action" value="FormAccount"/>
            </c:url>
            <nav class="navbar navbar-expand-sm bg-light navbar-light" >
                <a class="navbar-brand" href="${linkEditProfile}">
                    <c:set var="avatar" value="${sessionScope.USER.avatar}"/>
                    <img src="${avatar}"  alt="Logo" style="width:100px;">
                </a>
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <c:url value="MainController" var="linkManageMem">
                            <c:param name="action" value="Manage Member"/>
                        </c:url>
                        <c:url value="MainController" var="linkManageEvent">
                            <c:param name="action" value="Manage Event"/>
                        </c:url>
                        <c:url value="MainController" var="linkManageGroup">
                            <c:param name="action" value="ManageGroup"/>
                        </c:url>
                        <c:url value="MainController" var="linkViewNotification">
                            <c:param name="action" value="viewNotification"/>
                            <c:param name="isInternal" value="True"/>
                        </c:url>
                        <c:url value="MainController" var="linkViewStatistic">
                            <c:param name="action" value="ViewStatistic"/>
                        </c:url>
                        <a class="nav-link" href="${linkManageMem}">Manage Member</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkManageEvent}">Manage Event</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkManageGroup}">Manage Group</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkCreateAccount}">Create Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkViewNotification}">Notification</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkViewStatistic}">Statistic</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkLogout}">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkEditProfile}">Edit Profile</a>
                    </li>
                </ul>
            </nav>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
