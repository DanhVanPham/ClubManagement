<%-- 
    Document   : manageGroups
    Created on : Oct 22, 2020, 9:59:59 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="danhpv.entities.TblGroup"%>
<%@page import="danhpv.blo.GroupBlo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Manage Groups Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <% response.sendRedirect("login.jsp");%>
        </c:if>
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
                <jsp:include page="navBarAfLogin.jsp"/>
            </c:if>
            <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
                <jsp:include page="navBarAfLoginLeader.jsp"/>
            </c:if>        
            <c:if test="${requestScope.LIST_GROUP != null}">
                <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Manage Groups</h3>  
                <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader'}">
                    <a href="MainController?action=FormCreateGroup" class="btn btn-primary" style="margin-left: 5%;">Create Group</a><br/>
                </c:if>
                <form style="width: 121%;margin-left: 5%;" action="MainController" method="POST">
                    <div class="form-group"  >
                        <input type="text" name="txtSearch" class="form-control" aria-describedby="emailHelp" value="${param.txtSearch}" style="width: 70%;margin-bottom: 1%;margin-top: 1%;" placeholder="Enter search group name:"/>
                        <input type="submit" name="action" value="Search Group" class="btn btn-primary" />
                    </div>
                </form>
                <c:if test="${not empty requestScope.LIST_GROUP && requestScope.LIST_GROUP.size() != 0}" var="checkList">
                    <c:if test="${param.txtSearch.length() != 0}" var="checkSearchName">
                        <c:set var="txtSearch" value="${param.txtSearch}"/>
                    </c:if>
                    <table class="table" border="1" style="width: 85%;margin-left: 5%;margin-top: 2%;">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" style="text-align: center;">NO.</th>
                                <th scope="col" style="text-align: center;">Group ID:</th>
                                <th scope="col" style="text-align: center;">Group Name:</th>
                                <th scope="col" style="text-align: center;">Total Member:</th>
                                <th scope="col" style="text-align: center;">Name Leader:</th>
                                <th scope="col" style="text-align: center;">Action:</th>
                            </tr>
                        </thead>
                        <tbody>                       
                            <c:forEach var="dto" items="${requestScope.LIST_GROUP}" varStatus="counter">
                                <c:if test="${sessionScope.USER.roleId.roleName eq 'Leader' and sessionScope.USER.userID eq dto.leaderId.userID}" var="check">
                                <form action="MainController" method="POST">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${dto.groupID}</td>
                                        <td>${dto.groupName}</td>
                                        <td>${dto.totalMember}</td>
                                        <td>${dto.leaderId.fullname}</td>
                                        <td>
                                            <input type="hidden" name="groupID" value="${dto.groupID}"/>
                                            <input type="hidden" name="leaderID" value="${sessionScope.USER.userID}"/>
                                            <button type="submit" class="btn btn-success" name="action" value="ViewInfoGroup">View Details and Add Member</button>
                                            <button type="submit" class="btn btn-danger" name="action" value="RemoveGroup">Remove Group</button>
                                        </td>
                                    </tr>
                                </form>
                            </c:if>
                            <c:if test="${sessionScope.USER.roleId.roleName eq 'Admin'}">
                                <form action="MainController" method="POST">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${dto.groupID}</td>
                                        <td>${dto.groupName}</td>
                                        <td>${dto.totalMember}</td>
                                        <td>${dto.leaderId.fullname}</td>
                                        <td>
                                            <input type="hidden" name="groupID" value="${dto.groupID}"/>
                                            <button type="submit" class="btn btn-primary" name="action" value="ViewInfoGroup">View Group Details</button>
                                            <button type="submit" class="btn btn-danger" name="action" value="RemoveGroup">Remove Group</button>
                                        </td>
                                    </tr>
                                </form>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 42%;margin-top: 2%;margin-right: 42%;">
                    <ul class="pagination pagination-sm" >
                        <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="i">
                            <c:if test="${checkSearchName}" var="checkStatus">
                                <c:url value="MainController" var="linkPagningGroup">
                                    <c:param name="action" value="Search Group"/>
                                    <c:param name="pageIndex" value="${i}"/>
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                </c:url>
                                <li class="page-item"><a class="page-link" href="${linkPagningGroup}" style="font-size: 250%;">${i}</a></li>
                                </c:if>
                                <c:if test="${!checkSearchName}">
                                    <c:url value="MainController" var="linkPagningGroup">
                                        <c:param name="action" value="ManageGroup"/>
                                        <c:param name="pageIndex" value="${i}"/>
                                    </c:url>
                                <li class="page-item"><a class="page-link" href="${linkPagningGroup}" style="font-size: 250%;">${i}</a></li>
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
