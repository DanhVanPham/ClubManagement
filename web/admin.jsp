<%-- 
    Document   : admin
    Created on : Oct 20, 2020, 11:02:10 AM
    Author     : DELL
--%>

<%@page import="java.util.List"%>
<%@page import="danhpv.blo.UserBlo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="danhpv.entities.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Admin Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <% response.sendRedirect("login.jsp");%>
        </c:if>
        <c:if test="${not empty sessionScope.USER}">
            <input type="hidden" name="BACK" value="leader.jsp"/>
            <jsp:include page="navBarAfLogin.jsp"/>          
            <c:if test="${requestScope.LIST_USER == null}">
                <h1 style="color: red; text-align: center;">Can not find List User</h1>
            </c:if>
            <c:if test="${requestScope.LIST_USER != null}">
                <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Manage Members</h3>  
                <jsp:include page="navBarSearch.jsp"/>
                <c:if test="${not empty requestScope.LIST_USER}" var="checkList">
                    <c:if test="${param.txtSearch.length() != 0}" var="checkSearchName">
                        <c:set var="txtSearch" value="${param.txtSearch}"/>
                    </c:if>
                    <table class="table" border="1" style="width: 85%;margin-left: 5%;">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" style="text-align: center;">NO.</th>
                                <th scope="col" style="text-align: center;">Fullname</th>
                                <th scope="col" style="text-align: center;">Email</th>
                                <th scope="col" style="text-align: center;">Status</th>
                                <th scope="col" style="text-align: center;">Role</th>
                                <th scope="col" style="text-align: center;">Change Status</th>
                                <th scope="col" style="text-align: center;">View_Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${requestScope.LIST_USER}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <c:url value="MainController" var="linkMemDetail">
                                    <c:param name="action" value="ViewMemDetails"/>
                                    <c:param name="userID" value="${dto.userID}"/>
                                </c:url>
                                <tr>
                                    <th scope="row">${counter.count}</th>
                                    <td>${dto.fullname}</td>
                                    <td>${dto.email}</td>
                                    <td>
                                        <c:if test="${dto.status eq 'False'}">
                                            BLOCKED
                                        </c:if>
                                        <c:if test="${dto.status eq 'True'}">
                                            ACTIVITIES
                                        </c:if>
                                    </td> 
                                    <td>
                                        ${dto.roleId.roleName}
                                    </td>
                                    <td>
                                        <input type="hidden" name="userId" value="${dto.userID}"/>
                                        <input type="submit" class="btn btn-success" name="action" value="Change Status"/>
                                    </td>
                                    <td>
                                        <a href="${linkMemDetail}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Member Details</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 42%;margin-top: 2%;margin-right: 42%;">
                    <ul class="pagination pagination-sm" >
                        <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="i">
                            <c:if test="${checkSearchName}" var="checkStatus">
                                <c:url value="MainController" var="linkPagningEvent">
                                    <c:param name="action" value="SearchName"/>
                                    <c:param name="pageIndex" value="${i}"/>
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                </c:url>
                                <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                                </c:if>
                                <c:if test="${!checkSearchName}">
                                    <c:url value="MainController" var="linkPagningEvent">
                                        <c:param name="action" value="Manage Member"/>
                                        <c:param name="pageIndex" value="${i}"/>
                                    </c:url>
                                <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                                </c:if>
                            </c:forEach>
                    </ul>
                </nav>
            </c:if>
            <c:if test="${!checkList}">
                <h1 style="color: red;text-align: center;">No record found</h1>
            </c:if>
        </c:if>
    </c:if>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
