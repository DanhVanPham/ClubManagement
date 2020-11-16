<%-- 
    Document   : viewEvents
    Created on : Oct 15, 2020, 9:46:01 PM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>View Events Page</title>
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
        <c:if test="${param.action == null}">
            <%
                request.getRequestDispatcher("MainController?action=View Events").forward(request, response);
            %>
        </c:if>
        <h1 style="text-align: center;">EVENTS: </h1>       
        <c:if test="${param.action != null}">
            <jsp:include page="navBarSearchEvent.jsp"/>
            <c:if test="${requestScope.LISTEVENTS != null}">
                <c:if test="${not empty requestScope.LISTEVENTS}" var="checkList">
                    <div class="card-deck" style="padding-left: 10%;display: flex; ">
                        <c:forEach var="event" items="${requestScope.LISTEVENTS}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="BACK" value="viewEvents.jsp"/>
                                <c:if test="${param.txtSearch.length() != 0}" var="checkSearchName">
                                    <c:set var="txtSearch" value="${param.txtSearch}"/>
                                </c:if>
                                <c:url value="MainController" var="linkEventDetail">
                                    <c:param name="action" value="ViewEventDetails"/>
                                    <c:param name="eventID" value="${event.eventID}"/>
                                </c:url>
                                <c:url value="MainController" var="linkAddEvent">
                                    <c:param name="action" value="AddEventToCart"/>
                                    <c:param name="eventID" value="${event.eventID}"/>
                                    <c:param name="pageIndex" value="${param.pageIndex}"/>
                                </c:url>
                                <c:if test="${sessionScope.USER.roleId.roleName  eq 'Member' or sessionScope.USER.roleId.roleName  eq 'Leader'}">
                                    <c:set var="desc" value="${event.eventDesc}"/>
                                    <div class="card mt-3" style="width: 18rem;margin-top: 5%;min-height: 200px;">
                                        <a href="${linkEventDetail}"><img class="card-img-top" src="${event.banner}" alt="Card image cap"></a>
                                        <div class="card-body">
                                            <h5 class="card-title" style="font-weight: bold;text-align: center;color: #128BE5;">${event.eventName}</h5>
                                            Location: <p class="card-text" style="color: green;display: inline;">${event.locator}</p><br/>
                                            Time Start Event:<p class="card-text" style="color: blue;display: inline;"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${event.timeStartEvent}" /></p><br/>  
                                            Time End Event:<p class="card-text" style="color: blue;display: inline;"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${event.timeCloseEvent}" /></p><br/>
                                            Number Register:<p class="card-text" style="color: red;display: inline;">${event.numRegister}/${event.total}</p><br/>
                                            <c:if test="${event.eventStatus eq 'DELETED'}" var="checkStatus">
                                                Status:<p style="color: red;display: inline;font-weight: bold;">${event.eventStatus}</p><br/>
                                                <br/><a href="${linkEventDetail}" class="btn btn-danger" style="margin-left: 25%;margin-top: 5px;display: inline;">Event Details</a>
                                            </c:if>
                                            <c:if test="${event.eventStatus eq 'REGISTER'}">
                                                Status:<p style="color: green;display: inline;font-weight: bold;">${event.eventStatus}</p><br/>
                                                <br/><a href="${linkEventDetail}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Event Details</a>
                                                <a href="${linkAddEvent}" class="btn btn-primary" style="margin-top: 5px;display: inline;">Add to Cart</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${empty sessionScope.USER}">
                                    <c:if test="${!event.isInternal}">
                                        <c:set var="desc" value="${event.eventDesc}"/>
                                        <div class="card" style="width: 18rem;margin-top: 5%;">
                                            <a href="${linkEventDetail}"><img class="card-img-top" src="${event.banner}" alt="Card image cap"/></a>
                                            <div class="card-body">
                                                <h5 class="card-title" style="font-weight: bold;text-align: center;color: #128BE5;">${event.eventName}</h5>
                                                Location: <p class="card-text" style="color: green;display: inline;">${event.locator}</p><br/>
                                                Time Start Event:<p class="card-text" style="color: blue;display: inline;"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${event.timeStartEvent}" /></p><br/>  
                                                Time End Event:<p class="card-text" style="color: blue;display: inline;"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${event.timeCloseEvent}" /></p><br/>
                                                Number Register:<p class="card-text" style="color: red;display: inline;">${event.numRegister}/${event.total}</p><br/>
                                                <c:if test="${event.eventStatus eq 'DELETED'}" var="checkStatus">
                                                    Status:<p style="color: red;display: inline; font-weight: bold;">${event.eventStatus}</p><br/>
                                                    <br/><a href="${linkEventDetail}" class="btn btn-danger" style="margin-left: 25%;margin-top: 5px;display: inline;">Event Details</a>
                                                </c:if>
                                                <c:if test="${event.eventStatus eq 'REGISTER'}">
                                                    Status:<p style="color: green;display: inline;font-weight: bold;">${event.eventStatus}</p><br/>
                                                    <br/><a href="${linkEventDetail}" class="btn btn-primary" style="margin-left: 5%;margin-top: 5px;display: inline;">Event Details</a>
                                                    <a href="${linkAddEvent}" class="btn btn-primary" style="margin-top: 5px;display: inline;">Add to Cart</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:if>
                            </form>
                        </c:forEach>
                        <nav aria-label="Page navigation example" style="margin-left: 40%;margin-top: 2%;margin-right: 40%;">
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
                                                <c:param name="action" value="View Events"/>
                                                <c:param name="pageIndex" value="${i}"/>
                                            </c:url>
                                        <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                                        </c:if>
                                    </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </c:if>
                <c:if test="${!checkList}">
                    <h2 style="text-align: center;color: red;">Can not find Event</h2>
                </c:if>
            </c:if>   
        </c:if>  
        <c:if test="${not empty requestScope.SUCCESS}">
            <script>
                alert("Register Event successful.");
            </script>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
