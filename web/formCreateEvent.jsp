<%-- 
    Document   : formCreateEvent
    Created on : Oct 21, 2020, 8:46:12 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Form Create Event Page</title>
    </head>
    <body>
        <input type="hidden" name="BACK" value="leader.jsp"/>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
            <jsp:include page="navBarAfLoginLeader.jsp"/>
        </c:if>  
        <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Form Create New Event</h3>   
        <form style="margin-left: 20%;" action="MainController" method="POST">
            <div class="form-group">
                <label for="exampleInputEmail1">Event Name:</label>
                <input type="text" name="txtEventName" class="form-control" id="exampleInputEmail1" placeholder="Enter Event Name:" aria-describedby="emailHelp" value="${param.txtEventName}" required style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.eventNameError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Event Description:</label>
                <input type="text" name="txtEventDesc" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Event Description" value="${param.txtEventDesc}" required style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.eventDescError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Date and time Close Registration:</label>
                <input class="form-control" name="txtTimeCloseRegis" type="datetime-local" value="${param.txtTimeCloseRegis}" id="example-datetime-local-input" required style="width: 70%;">
                <font color="red">${requestScope.INVALID.timeCloseRegisError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Date and time Start Event:</label>
                <input class="form-control" name="txtTimeStartEvent" type="datetime-local" value="${param.txtTimeStartEvent}" id="example-datetime-local-input" required style="width: 70%;">
                <font color="red">${requestScope.INVALID.timeStartEventError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Date and time End Event:</label>
                <input class="form-control" name="txtTimeCloseEvent" type="datetime-local" value="${param.txtTimeCloseEvent}" id="example-datetime-local-input" required style="width: 70%;">
                <font color="red">${requestScope.INVALID.timeCloseEventError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Locator:</label>
                <input type="text" name="txtLocator" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Locator:" value="${param.txtLocator}" required style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.locatorError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Total: </label>
                <input type="number" name="txtTotal" class="form-control" id="exampleInputEmail1" required aria-describedby="emailHelp" placeholder="Enter Total:" value="${param.txtTotal}" required style="width: 70%;" min="2"/>
                <font color="red">${requestScope.INVALID.totalError}</font>
            </div>
            <div class="form-group form-check">
                <c:if test="${requestScope.INTERNAL}">
                    <input type="checkbox" name="checkInternal" class="form-check-input" id="exampleCheck1" checked/>
                    <label class="form-check-label" for="exampleCheck1">Is Internal</label>
                </c:if>
                <c:if test="${!requestScope.INTERNAL}">
                    <input type="checkbox" name="checkInternal" class="form-check-input" id="exampleCheck1"/>
                    <label class="form-check-label" for="exampleCheck1">Is Internal</label>
                </c:if>
            </div>            
            <input type="hidden" name="txtAvartar" value="${requestScope.EVENT.banner}"/>
            <button type="submit" name="action" value="CreateNewEvent" class="btn btn-primary">Create Event</button>
            <font color="red">${requestScope.ERROR}</font>
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
