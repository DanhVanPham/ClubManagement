<%-- 
    Document   : viewGroupJoinMemDetail
    Created on : Oct 22, 2020, 7:39:22 AM
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
        <title>Group Join Details Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
            <jsp:include page="navBarAfLoginLeader.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <h1 style="text-align: center;font-size: 250%;">Group Details</h1>
        <c:set var="groupInfo" value="${requestScope.GROUP}"/>
        <c:if test="${groupInfo != null}">
            <c:if test="${not empty groupInfo}" var="checkEmpty">
                <div class="card" style="width: 90%;margin-left: 5%;">
                    <div class="card-body">
                        <h1 class="card-header" style="text-align: center;font-size: 200%;">${groupInfo.groupName}</h1>
                        <div class="body">
                            <p class="card-text"  style="font-weight: bold;font-size: 140%;">Group Description: ${groupInfo.groupDesc}</p>
                            <p class="card-text" style="font-weight: bold;font-size: 120%;color: blue;margin-left: 5%;">Total Member: ${groupInfo.totalMember}</p>
                            <p class="card-text" style="text-transform: uppercase; font-weight: bold;font-size: 150%;color: green;">Leader:</p>
                            <p class="card-text" style="font-weight: bold;font-size: 120%;margin-left: 5%;">Name:${groupInfo.leaderId.fullname}</p>
                            <p class="card-text" style="font-weight: bold;font-size: 120%;margin-left: 5%;">Email: ${groupInfo.leaderId.email}</p>
                            <p class="card-text" style="text-transform: uppercase; font-weight: bold;font-size: 150%;color: green;" >Member:</p>

                            <c:if test="${not empty requestScope.GROUP_DETAIL}" var="checkList">
                                <table class="table" border="1" style="width: 85%;margin-left: 5%;">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col" style="text-align: center;">NO.</th>
                                            <th scope="col" style="text-align: center;">Fullname</th>
                                            <th scope="col" style="text-align: center;">Email</th>
                                            <th scope="col" style="text-align: center;">Status</th>
                                            <th scope="col" style="text-align: center;">Add Time:</th>
                                            <th scope="col" style="text-align: center;">Remove Time:</th>
                                                <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}" var="check">
                                                <th scope="col" style="text-align: center;">Action:</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="dto" items="${requestScope.GROUP_DETAIL}" varStatus="counter">
                                            <c:url value="MainController" var="linkRemoveMem">
                                                <c:param name="action" value="RemoveMemFromGroup"/>
                                                <c:param name="userID" value="${dto.memberID.userID}"/>
                                                <c:param name="leaderID" value="${sessionScope.USER.userID}"/>
                                                <c:param name="groupID" value="${requestScope.GROUP.groupID}"/>
                                            </c:url>
                                            <c:url value="MainController" var="linkMemDetail">
                                                <c:param name="action" value="ViewMemDetails"/>
                                                <c:param name="groupID" value="${requestScope.GROUP.groupID}"/>
                                                <c:param name="userID" value="${dto.memberID.userID}"/>
                                            </c:url>
                                            <tr>
                                                <th scope="row" style="text-align: center;">${counter.count}</th>
                                                <td>${dto.memberID.fullname}</td>
                                                <td>${dto.memberID.email}</td>
                                                <td>${dto.status}</td> 
                                                <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.addTime}"/></td>
                                                <c:if test="${dto.removeTime != null}">
                                                    <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.removeTime}"/></td>
                                                    <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}" var="check">
                                                        <td>
                                                            <input type="submit" class="btn btn-secondary"  value="Remove" disabled>
                                                            <a href="${linkMemDetail}" class="btn btn-primary">Member Details</a>
                                                        </td>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${dto.removeTime == null}">
                                                    <td> </td>
                                                    <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}" var="check">
                                                        <td>
                                                            <c:if test="${sessionScope.USER.userID ne dto.memberID.userID}">
                                                                <a href="${linkRemoveMem}" class="btn btn-danger">Remove</a>
                                                            </c:if>
                                                            <a href="${linkMemDetail}" class="btn btn-primary">Member Details</a>
                                                        </td>
                                                    </c:if>
                                                </c:if>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${!checkList}">
                                <h1 style="color: red;text-align: center;">No record found!</h1>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!checkEmpty}">
                <h2 style="color:red;">Group does not have member.</h2>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}">
            <h1 style="color: blue;text-align: center;font-size: 150%;margin-top: 2%;">ADD NEW MEMBER:</h1>
            <c:if test="${not empty requestScope.LIST_USER}" var="checkList">
                <table class="table" border="1" style="width: 70%;margin-left: 14%;">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col" style="text-align: center;">NO.</th>
                            <th scope="col" style="text-align: center;">UserID: </th>
                            <th scope="col" style="text-align: center;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.LIST_USER}" varStatus="counter">
                            <c:url value="MainController" var="linkMemDetail">
                                <c:param name="action" value="ViewMemDetails"/>
                                <c:param name="userID" value="${dto}"/>
                            </c:url>
                        <form action="MainController" method="POST">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${dto}</td>
                                <td style="text-align: center;">
                                    <input type="hidden" name="leaderID" value="${sessionScope.USER.userID}"/>
                                    <input type="hidden"name="groupID" value="${requestScope.GROUP.groupID}"/>
                                    <input type="hidden" name="userID" value="${dto}"/>
                                    <button type="submit" class="btn btn-primary" name="action" value="AddMemToCart">Add to cart</button>
                                    <a href="${linkMemDetail}" class="btn btn-primary">Member Details</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${!checkList}">
            <h2 style="text-align: center;color: red;">No record found</h2>
        </c:if>
    </c:if>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
