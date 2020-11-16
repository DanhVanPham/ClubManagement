<%-- 
    Document   : manageEvents
    Created on : Oct 16, 2020, 11:10:32 PM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="danhpv.entities.TblEvent"%>
<%@page import="danhpv.entities.TblEvent"%>
<%@page import="danhpv.blo.EventBlo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Manage Events Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <% response.sendRedirect("login.jsp");%>
        </c:if>
        <c:if test="${not empty sessionScope.USER}">
            <input type="hidden" name="BACK" value="leader.jsp"/>
            <jsp:include page="navBarAfLogin.jsp"/>       
            <c:if test="${requestScope.LIST_EVENTS == null}">
                <h1 style="color: red; text-align: center;">Can not find List Event</h1>
            </c:if>
            <c:if test="${requestScope.LIST_EVENTS != null}">
                <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;margin-bottom: 3%;">Manage Events</h3>
                <jsp:include page="navBarSearchEvent.jsp"/>
                <form action="MainController" method="POST">
                    <button type="submit" class="btn btn-primary" style="margin-left: 45%;margin-bottom: 1%;" name="action" value="Form Create Event">Create Event</button>
                </form>
                <c:if test="${not empty requestScope.LIST_EVENTS}" var="checkList">
                    <c:if test="${param.txtSearch.length() != 0}" var="checkSearchName">
                        <c:set var="txtSearch" value="${param.txtSearch}"/>
                    </c:if>
                    <table class="table" border="1" style="width: 90%;margin-left: 5%;">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" style="text-align: center;">NO.</th>
                                <th scope="col" style="text-align: center;">Event Name</th>
                                <th scope="col" style="text-align: center;">Time Of Close Register:</th>
                                <th scope="col" style="text-align: center;">Locator</th>
                                <th scope="col" style="text-align: center;">Total</th>
                                <th scope="col" style="text-align: center;">Internal</th>
                                <th scope="col" style="text-align: center;">Event Status</th>
                                <th scope="col" style="text-align: center;">Action:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${requestScope.LIST_EVENTS}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <tr>
                                    <th scope="row">${counter.count}</th>
                                    <td>${dto.eventName}</td>
                                    <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${dto.timeCloseRegister}" /></td>
                                    <td>${dto.locator}</td>
                                    <td>${dto.total}</td>
                                    <td>${dto.isInternal}</td>
                                    <c:if test="${dto.eventStatus eq 'REGISTER'}">
                                        <td>
                                            <button type="submit" class="btn btn-primary" name="action" disabled>${dto.eventStatus}</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${dto.eventStatus eq 'DELETED'}">
                                        <td>
                                            <button type="submit" class="btn btn-danger" name="action" disabled>${dto.eventStatus}</button>
                                        </td>
                                    </c:if>
                                    <td>
                                        <input type="hidden" name="eventID" value="${dto.eventID}"/>
                                        <button type="submit" class="btn btn-primary" name="action" value="ViewEventDetails">View Event Details</button>
                                        <button type="submit" class="btn btn-danger" name="action" value="RemoveEventFromAdmin">Remove Event</button>
                                        <button type="submit" name="action" class="btn btn-success" value="ViewEventReport">Event Report</button>
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
                                    <c:param name="action" value="View Events"/>
                                    <c:param name="pageIndex" value="${i}"/>
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                </c:url>
                                <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                                </c:if>
                                <c:if test="${!checkSearchName}">
                                    <c:url value="MainController" var="linkPagningEvent">
                                        <c:param name="action" value="Manage Event"/>
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
