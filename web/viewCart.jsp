<%-- 
    Document   : viewCart
    Created on : Oct 20, 2020, 2:16:32 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Cart Event Page</title>
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
        <c:if test="${sessionScope.cart != null and sessionScope.cart.cart.size() != 0}">
            <h1 style="text-align: center;">View To Cart</h1>
            <form action="MainController" method="POST">
                <div style="margin-left: 20%;">
                    <input type="hidden" name="BACK" value="viewCart.jsp"/>
                    <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
                        <div class="form-group" >
                            <label for="exampleInputEmail1">UserID:</label>
                            <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${sessionScope.USER.userID}"  readonly="true" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.userIDError}</font>
                        </div>
                        <div class="form-group" >
                            <label for="exampleInputEmail1">Fullname:</label>
                            <input type="text" name="txtFullname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${sessionScope.USER.fullname}" required readonly="true" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.fullnameError}</font>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address:</label>
                            <input type="email" name="txtEmail" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email Address" value="${sessionScope.USER.email}" readonly="true" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.emailError}</font>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.USER}">
                        <div class="form-group">
                            <label for="exampleInputEmail1">UserID:</label>
                            <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${param.txtUserID}" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.userIDError}</font>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Fullname:</label>
                            <input type="text" name="txtFullname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${param.txtFullname}" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.fullnameError}</font>
                        </div>
                        <div class="form-group" >
                            <label for="exampleInputEmail1">Email address:</label>
                            <input type="email" name="txtEmail" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email Address" value="${param.txtEmail}" style="width: 70%;"/>
                            <font color="red">${requestScope.INVALID.emailError}</font>
                        </div>
                    </c:if>
                </div>
                <table class="table" border="1" style="margin-left: 14%;margin-top: 2%;width: 70%;">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col" style="text-align: center;">NO.</th>
                            <th scope="col" style="text-align: center;">EventID:</th>
                            <th scope="col" style="text-align: center;">EventName:</th>
                            <th style="text-align: center;">Action:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${sessionScope.cart.getCart().values()}" varStatus="counter">
                            <c:url value="MainController" var="linkEventDetail">
                                <c:param name="action" value="ViewEventDetails"/>
                                <c:param name="eventID" value="${dto.eventID}"/>
                            </c:url>
                        <form action="MainController" method="POST">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${dto.eventID}</td>
                                <td>${dto.eventName}</td>
                                <td style="text-align: center;">
                                    <div class="form-group">
                                        <input type="hidden" name="eventID" value="${dto.eventID}"/>
                                        <button type="submit" name="action" class="btn btn-danger" value="Remove Event From Cart" style="margin-right: 5%;">Remove Event</button>
                                        <a href="${linkEventDetail}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Event Details</a>
                                    </div>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="form-group">
                    <input type="submit" name="action" class="btn btn-primary" value="Register Event"
                           style="margin-left: 46%;"/><br/>
                </div>
            </form>
        </c:if>
        <c:if test="${sessionScope.cart == null or sessionScope.cart.cart.size() == 0}">
            <h2 style="color: red;text-align: center;text-transform: uppercase;margin-top: 10%;">Can not find event to shopping</h2>
        </c:if>
        <a href="viewEvents.jsp" style="margin-left: 42%;font-size: 200%;font-weight: bold;text-transform: uppercase;">Back to Home</a>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
                integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
        crossorigin="anonymous"></script>
    </body>
</html>
