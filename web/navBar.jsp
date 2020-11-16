<%-- 
    Document   : navBar
    Created on : Oct 16, 2020, 9:52:51 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <c:url value="MainController" var="linkViewCart">
            <c:param name="action" value="viewCart"/>
        </c:url>
        <c:url value="MainController" var="linkViewNotification">
            <c:param name="action" value="viewNotification"/>
            <c:param name="isInternal" value="False"/>
        </c:url>
        <nav class="navbar navbar-expand-sm bg-light navbar-light">           
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="viewEvents.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${linkViewCart}">View to cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${linkViewNotification}">Notification</a>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
