<%-- 
    Document   : navBarAfLoginMem
    Created on : Oct 21, 2020, 11:39:48 PM
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
            <c:url value="MainController" var="linkViewCart">
                <c:param name="action" value="viewCart"/>
            </c:url>
            <c:url value="MainController" var="linkViewGroup">
                <c:param name="action" value="viewGroupJoin"/>
                <c:param name="userID" value="${sessionScope.USER.userID}"/>
            </c:url>
            <c:url value="MainController" var="linkViewHistoryJoin">
                <c:param name="action" value="ViewHistoryJoin"/>
            </c:url>
            <c:url value="MainController" var="linkViewNotification">
                <c:param name="action" value="viewNotification"/>
                <c:param name="isInternal" value="True"/>
            </c:url>
            <nav class="navbar navbar-expand-sm bg-light navbar-light" > 
                <a class="navbar-brand" href="${linkEditProfile}">
                    <c:set var="avatar" value="${sessionScope.USER.avatar}"/>
                    <img src="${avatar}"  alt="Logo" style="width:100px;">
                </a>
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="MainController?action=View Events">Home(Events)</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${linkViewGroup}">Group</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkViewCart}">View to cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkViewHistoryJoin}">History Join Event</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkViewNotification}">Notification</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkLogout}">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${linkEditProfile}">Edit Profile</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Contact
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="https://www.facebook.com/pham.vn.7/"><i style="margin-right: 0.5em; color: #EEEEEE;" class="fab fa-facebook fa-2x"></i>Facebook</a>
                            <a class="dropdown-item" href="https://mail.google.com/mail/u/0/?view=cm&fs=1&to=danhpvse141028@gmail.com&tf=1"><i style="margin-right: 0.5em; color: #EEEEEE;" class="fas fa-envelope fa-2x"></i>Gmail</a>
                            <a class="dropdown-item" href="#"><i class="fas fa-phone-square fa-2x" ></i>Phone:(0902472118)</a>
                        </div>
                    </li>
                </ul>
            </nav>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
