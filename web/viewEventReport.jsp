<%-- 
    Document   : viewEventReport
    Created on : Oct 27, 2020, 12:28:57 AM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Event Report Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <h1 style="text-align: center;font-size: 250%;">Event Details</h1>
        <c:set var="eventDetail" value="${requestScope.EVENT_DETAIL}"/>
        <c:if test="${eventDetail != null}">
            <c:if test="${not empty eventDetail}" var="checkEmpty">
                <div class="header">
                    <a href="${linkEventDetail}"><img src="${eventDetail.banner}" style="width: 200px;height: 150px;margin-left: 44%;"></a>
                </div>
                <div class="card" style="width: 90%;margin-left: 5%;">
                    <div class="card-body">
                        <h1 class="card-header" style="text-align: center;">${eventDetail.eventName}</h1>
                        <div class="body" style="margin-left: 4%;">
                            <p class="card-text" style="font-size: 150%;">Location:&nbsp;&nbsp;${eventDetail.locator}</p>
                            <p class="card-text" style="color: blue;font-weight: bold;font-size: 150%;">Number Register:&nbsp;&nbsp;${eventDetail.numRegister} / ${eventDetail.total}</p>
                            <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Time Close Register:&nbsp;&nbsp;<fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${eventDetail.timeCloseRegister}" /></p>
                            <c:if test="${eventDetail.eventStatus eq 'DELETED'}" var="checkStatus">
                                <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Status:&nbsp;&nbsp;${eventDetail.eventStatus}</p>
                            </c:if>
                            <c:if test="${!checkStatus}">
                                <p class="card-text" style="color: green;font-weight: bold;font-size: 150%;">Status:&nbsp;&nbsp;${eventDetail.eventStatus}</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <h1 style="text-align: center; font-weight: bold;font-size: 200%;margin-top: 2%;text-transform: uppercase">History:</h1>
            <c:if test="${requestScope.REPORT_EVENT != null && not empty requestScope.REPORT_EVENT}" var="check">
                <c:set var="report" value="${requestScope.REPORT_EVENT}"/>
                <table class="table" border="1" style="width: 90%;margin-left: 5%;margin-top: 2%;">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">NO.</th>
                            <th scope="col">UserID:</th>
                            <th scope="col">Fullname:</th>
                            <th scope="col">Email:</th>
                            <th scope="col">Register Time:</th>
                            <th scope="col">Status:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${report}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.studentID}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.registerTime}" /></td>
                                <td>${dto.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!check}">
                <h1 style="color: red;text-align: center;">Nothing!</h1>
            </c:if>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
