<%-- 
    Document   : viewEvent_Details
    Created on : Oct 16, 2020, 7:56:20 PM
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
        <title>Event Details Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <jsp:include page="navBar.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
            <jsp:include page="navBarAfLoginMem.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
            <jsp:include page="navBarAfLoginLeader.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <h1 style="text-align: center;font-size: 250%;">Event Details</h1>
        <c:set var="eventDetail" value="${requestScope.EVENT_DETAIL}"/>
        <c:if test="${eventDetail != null}">
            <c:if test="${not empty eventDetail}" var="checkEmpty">
                <div class="header">
                    <a href=${linkEventDetail}><img src="${eventDetail.banner}" style="width: 200px;height: 150px;margin-left: 44%;"></a>
                </div>
                <div class="card" >
                    <div class="card-body">
                        <h1 class="card-header" >${eventDetail.eventName}</h1>
                        <div class="body" style="margin-left: 5%;">
                            <p class="card-text" style="font-weight: bold;font-size: 150%;">Description:${eventDetail.eventDesc}</p>
                            <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Time Start Event: <fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${eventDetail.timeStartEvent}" /></p>
                            <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Time End Event:<fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${eventDetail.timeCloseEvent}" /></p>
                            <p class="card-text" style="font-weight: bold;font-size: 150%;">Location:${eventDetail.locator}</p>
                            <p class="card-text" style="color: blue;font-weight: bold;font-size: 150%;">Number Register:${eventDetail.numRegister} / ${eventDetail.total}</p>
                            <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Time Close Register:&nbsp;&nbsp;<fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${eventDetail.timeCloseRegister}" /></p>
                            <c:if test="${eventDetail.eventStatus eq 'DELETED'}" var="check">
                                <p class="card-text" style="color: red;font-weight: bold;font-size: 150%;">Status:${eventDetail.eventStatus}</p>
                            </c:if>
                            <c:if test="${!check}">
                                <p class="card-text" style="color: green;font-weight: bold;font-size: 150%;">Status:${eventDetail.eventStatus}</p>
                            </c:if>
                        </div>
                    </div>
                </div>
                <h1 style="font-weight: bold;text-align: center;font-size: 200%;">Comment about: ${eventDetail.eventName}</h1>
                <form class="ui reply form" action="MainController" method="POST" style="margin-left: 20%;">
                    <div class="form-group">
                        <label for="exampleInputEmail1">UserID: </label>
                        <input type="text" name="txtUserID" required="required" class="form-control" id="exampleInputEmail1" placeholder="Enter User ID:" aria-describedby="emailHelp" value="${sessionScope.USER.userID}" style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.userIDError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Fullname: </label>
                        <input type="text" name="txtFullname" required="required" class="form-control" id="exampleInputEmail1" placeholder="Enter Fullname: " aria-describedby="emailHelp" value="${sessionScope.USER.fullname}" style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.fullnameError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email: </label>
                        <input type="text" name="txtEmail" required="required" class="form-control" id="exampleInputEmail1" placeholder="Enter Email:" aria-describedby="emailHelp" value="${sessionScope.USER.email}" style="width: 70%;"/>
                        <font color="red">${requestScope.INVALID.emailError}</font>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Content: </label><br/>
                        <textarea class="dropfirst textarea" required="required" id="content" name="content" style="overflow-y: visible;width: 80%;" rows="4" cols="100"></textarea><br/>
                        <font color="red">${requestScope.INVALID.contentError}</font>
                    </div>
                    <input type="hidden" name="eventID" value="${eventDetail.eventID}"/>
                    <button type="submit" name="action" value="CommentEvent" class="btn btn-primary" style="margin-left: 40%;">Comment</button><br/>
                </form>
                <c:if test="${requestScope.LIST_COMMENT != null}">
                    <div class="ui comments" style="margin-left: 2%;">
                        <div class="card mt-3" >
                            <h3 class="card-header" style="text-align: center;font-weight: bold;text-transform: uppercase; ">Comments:</h3>
                            <c:forEach var="dto" items="${requestScope.LIST_COMMENT}">
                                <c:if test="${dto.memberID != null}" var="check">
                                    <c:set var="role" value="${dto.memberID.roleId.roleName}"/>
                                </c:if>
                                <c:if test="${!check}">
                                    <c:set var="role" value="Guest"/>
                                </c:if>
                                <div class="card-body">
                                    <div class="body">
                                        <p class="card-text" style="font-weight: bold;">*${dto.fullname}(${role}):&nbsp; <fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.timeComment}" /></p>
                                        <p class="card-text"  style="margin-left: 3%;">&nbsp;&nbsp;${dto.content}</p>            
                                    </div>
                                </div>
                            </c:forEach>           
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${!checkEmpty}">
                <h2 style="color:red;">Can not find Event Details</h2>
            </c:if>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
