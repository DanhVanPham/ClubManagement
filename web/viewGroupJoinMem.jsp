<%-- 
    Document   : viewGroupJoinMem
    Created on : Oct 22, 2020, 12:24:24 AM
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
        <title>Group Join Page</title>
    </head>
    <body>
        <c:if test="${not empty requestScope.SUCCESS}">
            <script>
                alert(${requestScope.SUCCESS});
            </script>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <jsp:include page="navBar.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
            <jsp:include page="navBarAfLoginMem.jsp"/>
        </c:if>
        <c:if test="${param.action == null}">
            <%
                request.getRequestDispatcher("MainController?action=View Events").forward(request, response);
            %>
        </c:if>
        <h1 style="text-align: center;">GROUP: </h1>       
        <c:if test="${param.action != null}">
            <c:if test="${requestScope.GROUPDETAIL != null}">
                <c:if test="${not empty requestScope.GROUPDETAIL}" var="checkList">
                    <div class="card-deck" style="padding-left: 10%;display: flex; ">
                        <c:forEach var="group" items="${requestScope.GROUPDETAIL}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="BACK" value="viewGroupJoinMem.jsp"/>
                                <c:url value="MainController" var="linkInfoGroup">
                                    <c:param name="action" value="ViewInfoGroup"/>
                                    <c:param name="groupID" value="${group.groupID.groupID}"/>
                                </c:url>
                                <c:url value="MainController" var="linkLeaveGroup">
                                    <c:param name="action" value="LeaveTheGroup"/>
                                    <c:param name="userID" value="${group.memberID.userID}"/>
                                    <c:param name="groupID" value="${group.groupID.groupID}"/>
                                    <c:param name="groupDetailID" value="${group.groupDetailID}"/>
                                </c:url>
                                <c:if test="${sessionScope.USER.roleId.roleName  eq 'Member'}">
                                    <div class="card" style="width: 20rem;margin-top: 5%;">
                                        <div class="card-body">
                                            <h5 class="card-title" style="font-weight: bold;text-align: center;color: #128BE5;">${group.groupID.groupName}</h5>
                                            Fullname:&nbsp;&nbsp;<p class="card-text" style="font-weight: bold;color: blue;display: inline;">${group.memberID.fullname}</p><br/>
                                            Status:&nbsp;&nbsp;<p style="font-weight: bold;color: green;display: inline;">${group.status}</p><br/>
                                            Add Time:&nbsp;&nbsp;<p style="color: green;display: inline;"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${group.addTime}" /></p><br/>
                                            <br/><a href="${linkInfoGroup}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Infor Group</a>
                                            <a href="${linkLeaveGroup}" class="btn btn-danger" style="margin-top: 5px;display: inline;">Leave Group</a>
                                        </div>
                                    </div>
                                </c:if>
                            </form>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${!checkList}">
                    <h2 style="text-align: center;color: red;">Can not find group join</h2>
                </c:if>
            </c:if>   
        </c:if>  
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
