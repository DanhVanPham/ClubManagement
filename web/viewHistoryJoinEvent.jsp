<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : viewHistoryJoinEvent
    Created on : Oct 28, 2020, 1:17:30 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>History Join Event Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
            <jsp:include page="navBarAfLoginMem.jsp"/>
        </c:if>
        <h1 style="font-weight: bold;font-size: 300%;text-align: center;margin-bottom: 4%;margin-top: 2%;">History Join Event:</h1>
        <c:if test="${not empty requestScope.HISTORY_JOIN && requestScope.HISTORY_JOIN.size() != 0}" var="checkEmpty">
            <div class="card" style="width: 90%;margin-left: 5%;">
                <div class="card-body">
                    <h2 class="card-header" style="font-size: 150%;">Fullname:${requestScope.USER.fullname}</h2>
                    <h2 class="card-header" style="font-size: 150%;">Email: ${requestScope.USER.email}</h2>
                    <h2 class="card-header" style="font-size: 150%;">Role: ${requestScope.USER.roleId.roleName}</h2>
                </div>
                <c:forEach var="dto" items="${requestScope.HISTORY_JOIN}" varStatus="counter">
                    <div class="card mt-3" style="margin-left: 1%;">
                        <div class="card-body">
                            <div class="body">
                                <h1 class="card-title" style="color: blue;">Event Name: ${dto.eventID.eventName}</h1>
                                <p class="card-text" style="font-size: 150%;">&emsp;&emsp;Register Time:&nbsp;&nbsp;<fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.registerTime}" /></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${!checkEmpty}">
            <h2 style="text-align: center;color: red;font-size: 150%;">Nothing!</h2>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
