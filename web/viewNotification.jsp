<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%--
    Document   : viewNotification
    Created on : Oct 28, 2020, 8:36:33 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Notification Page</title>
    </head>
    <body>
        <h1 style="font-weight: bold;text-align: center;">View Notification</h1>
        <c:if test="${sessionScope.USER.getNotification or empty sessionScope.USER}" var="checkNoti">
            <c:if test="${not empty requestScope.LIST_NOTI and requestScope.LIST_NOTI.size() != 0}" var="checkEmpty">
                <c:forEach var="notification" items="${requestScope.LIST_NOTI}" varStatus="counter">
                    <c:url value="MainController" var="linkEventDetails">
                        <c:param name="action" value="ViewEventDetails"/>
                        <c:param name="eventID" value="${notification.eventID.eventID}"/>
                    </c:url>
                    <div class="card" >
                        <div class="card-body">
                            <h1 class="card-header" style="text-align: center;font-size: 200%;"><a href="${linkEventDetails}">${notification.eventID.eventName}</a></h1>
                            <div class="body">
                                <c:if test="${fn:contains(notification.notifiContent, 'cancel')}" var="checkStatus">
                                    <p class="card-text"  style="font-weight: bold;font-size: 140%;"><a href="${linkEventDetails}" style="color: red;">${notification.notifiContent}</a> </p>
                                </c:if>
                                <c:if test="${!checkStatus}">
                                    <p class="card-text"  style="font-weight: bold;font-size: 140%;"><a href="${linkEventDetails}" style="color: green;">${notification.notifiContent}</a> </p>
                                </c:if>
                                <p class="card-text" style="font-weight: bold;font-size: 80%;color: lavender;margin-left: 5%;">Notification Time Created:<fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${notification.notifiTimeCreated}" /></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </c:if>
            <c:if test="${!checkEmpty}">
                <h2 style="text-align: center;color: red;">Can find notification event</h2>
            </c:if>
        </c:if>
            <c:if test="${!checkNoti}">
                <h2 style="text-align: center;color: blue;">Turn on get notification to get more news event</h2>
            </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
