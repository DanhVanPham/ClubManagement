<%-- 
    Document   : viewCartMember
    Created on : Oct 25, 2020, 11:12:03 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Cart Member Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
            <jsp:include page="navBarAfLoginLeader.jsp"/>
        </c:if>
        <c:if test="${sessionScope.cartMember != null and sessionScope.cartMember.cart.size() != 0}">
            <h1 style="text-align: center;">View To Cart</h1>
            <form action="MainController" method="POST">
                <c:forEach var="dtoGroup" items="${sessionScope.cartMember.cart}">
                    <h1 style="text-align: center;font-weight: bold;">GROUP:${dtoGroup.key}</h1>
                    <input type="hidden" name="BACK" value="viewCartMember.jsp"/>
                    <table class="table" border="1" style="margin-left: 14%;margin-top: 2%;width: 70%;">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" style="text-align: center;">NO.</th>
                                <th scope="col" style="text-align: center;">UserID:</th>
                                <th scope="col" style="text-align: center;">Fullname:</th>
                                <th style="text-align: center;">Action:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dtoUser" items="${dtoGroup.value}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <c:url value="MainController" var="linkMemDetail">
                                    <c:param name="action" value="ViewMemDetails"/>
                                    <c:param name="userID" value="${dtoUser.value.userID}"/>
                                </c:url>
                                <tr>
                                    <th scope="row">${counter.count}</th>
                                    <td>${dtoUser.value.userID}</td>
                                    <td>${dtoUser.value.fullname}</td>
                                    <td style="text-align: center;">
                                        <div class="form-group">
                                            <input type="hidden" name="groupID" value="${dtoGroup.key}"/>
                                            <input type="hidden" name="userID" value="${dtoUser.value.userID}"/>
                                            <button type="submit" name="action" class="btn btn-danger" value="RemoveMemberFromCart" style="margin-right: 5%;">Remove Member</button>
                                            <a href="${linkMemDetail}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Member Details</a>
                                        </div>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <form action="MainController" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="groupID" value="${dtoGroup.key}"/>
                            <button type="submit" name="action" class="btn btn-primary" value="RegisterMember" style="margin-left: 46%;">Register Member</button><br/>
                        </div>
                    </form>
                </form>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.cartMember == null or sessionScope.cartMember.cart.size() == 0}">
            <h2 style="color: red;margin-top: 10%;text-align: center;text-transform: uppercase;">Can not find Member in cart</h2>
        </c:if>
        <c:if test="${not empty requestScope.SUCCESS}">
            <script>
                alert("Register Member successful.");
            </script>
        </c:if> 
        <c:url value="MainController" var="linkManageGroup">
            <c:param name="action" value="ManageGroup"/>
            <c:param name="userID" value="${sessionScope.USER.userID}"/>
        </c:url>
        <a href="${linkManageGroup}" style="margin-left: 45%;font-size: 150%;font-weight: bold;text-transform: uppercase;">Back to Home</a>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
