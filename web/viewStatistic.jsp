<%-- 
    Document   : viewStatistic
    Created on : Oct 29, 2020, 1:21:43 PM
    Author     : DELL
--%>
<%@page import="danhpv.entities.TblEvent"%>
<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    // --- String Join Function converts from Java array to javascript string.  
    public String join(ArrayList<?> arr, String del) {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {

            if (i > 0) {
                output.append(del);
            }

            // --- Quote strings, only, for JS syntax  
            if (arr.get(i) instanceof String) {
                output.append("\"");
            }
            output.append(arr.get(i));
            if (arr.get(i) instanceof String) {
                output.append("\"");
            }
        }

        return output.toString();
    }
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>
        <title>View Statistic Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <h1 style="margin-top: 2%;margin-bottom: 2%;font-weight: bold;text-align: center;text-transform: uppercase;">View Statistic</h1>
        <form style="margin-left: 20%;" action="MainController" method="POST">
            <div class="form-group">
                <label for="exampleInputEmail1">Date and time From:</label>
                <input class="form-control" name="txtTimeFrom" type="date" value="${param.txtTimeFrom}" id="example-datetime-local-input" required style="width: 70%;">
                <font color="red">${requestScope.INVALID.timeStartEventError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Date and time To:</label>
                <input class="form-control" name="txtTimeTo" type="date" value="${param.txtTimeTo}" id="example-datetime-local-input" required style="width: 70%;">
                <font color="red">${requestScope.INVALID.timeCloseEventError}</font>
            </div>
            <font color="red">${requestScope.ERROR}</font>
            <button type="submit" name="action" value="FindStatistic" class="btn btn-primary" style="margin-bottom: 2%;">Find:</button>
        </form>

        <c:if test="${requestScope.LIST_EVENTS != null || requestScope.LIST_EVENTS.size() != 0}" var="check">
            <c:if test="${not empty requestScope.LIST_EVENTS}" var="checkList">
                <c:if test="${param.txtSearch.length() != 0}" var="checkInterval">
                    <c:set var="txtTimeFrom" value="${param.txtTimeFrom}"/>
                    <c:set var="txtTimeTo" value="${param.txtTimeTo}"/>
                </c:if>
                <table class="table" border="1" style="width: 90%;margin-left: 5%;">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col" style="text-align: center;">NO.</th>
                            <th scope="col" style="text-align: center;">Event Name</th>
                            <th scope="col" style="text-align: center;">Number Register:</th>
                            <th scope="col" style="text-align: center;">Total</th>
                            <th scope="col" style="text-align: center;">Locator</th>
                            <th scope="col" style="text-align: center;">Internal</th>
                            <th scope="col" style="text-align: center;">Event Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.LIST_EVENTS}" varStatus="counter">
                        <form action="MainController" method="POST">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${dto.eventName}</td>
                                <td>${dto.numRegister}</td>
                                <td>${dto.total}</td>
                                <td>${dto.locator}</td>
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
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example" style="margin-left: 42%;margin-top: 2%;margin-right: 42%;">
                <ul class="pagination pagination-sm" style="margin-left: 45%;">
                    <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="i">
                        <c:if test="${checkInterval}" var="checkStatus">
                            <c:url value="MainController" var="linkPagningEvent">
                                <c:param name="action" value="FindStatistic"/>
                                <c:param name="pageIndex" value="${i}"/>
                                <c:param name="txtTimeFrom" value="${param.txtTimeFrom}"/>
                                <c:param name="txtTimeTo" value="${param.txtTimeTo}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                            </c:if>
                            <c:if test="${!checkInterval}">
                                <c:url value="MainController" var="linkPagningEvent">
                                    <c:param name="action" value="FindStatistic"/>
                                    <c:param name="pageIndex" value="${i}"/>
                                </c:url>
                            <li class="page-item"><a class="page-link" href="${linkPagningEvent}" style="font-size: 250%;">${i}</a></li>
                            </c:if>
                        </c:forEach>
                </ul>
            </nav>
        </c:if>
        <c:if test="${!checkList}">
            <h1 style="text-align: center;color: red;">${requestScope.ERROR1}</h1>
        </c:if>
    </c:if>
    <c:if test="${!check}">
        <h1 style="text-align: center;color: red;">${requestScope.ERROR1}</h1>
    </c:if>
    <script>
        <%
            // --- Create two Java Arrays  
            ArrayList<String> events = new ArrayList<String>();
            ArrayList<Integer> numOfRegis = new ArrayList<Integer>();

            List<TblEvent> listEvents = (List<TblEvent>) request.getAttribute("LIST_ALL_EVENTS");
            if (listEvents != null && !listEvents.isEmpty()) {
                for (TblEvent listEvent : listEvents) {
                    events.add(listEvent.getEventName());
                    numOfRegis.add(listEvent.getNumRegister());
                }
            }
        %>

        // --- add a comma after each value in the array and convert to javascript string representing an array  
        var eventsData = [<%= join(events, ",")%>];
        var numOfRegisData = [<%= join(numOfRegis, ",")%>];

    </script>  
    <script>
        window.onload = function () {
            zingchart.render({
                id: "myChart",
                width: "100%",
                height: 400,
                data: {
                    "type": "bar",
                    "title": {
                        "text": "Bar chart",
                    },
                    scaleX: {
                        "labels": eventsData,
                        label: {
                            text: 'Event Name',
                            backgroundColor: '#ffe6e6',
                            borderColor: 'red',
                            borderRadius: 3,
                            borderWidth: 1,
                            fontColor: 'red',
                            fontFamily: 'Georgia',
                            fontSize: 16,
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            padding: '5px 20px'
                        }
                    },
                    scaleY: {
                        label: {
                            text: 'Number Of Register',
                            backgroundColor: '#ffe6e6',
                            borderColor: 'red',
                            borderRadius: 3,
                            borderWidth: 1,
                            fontColor: 'red',
                            fontFamily: 'Georgia',
                            fontSize: 16,
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            padding: '5px 20px'
                        }
                    },
                    plotarea: {margin: 'dynamic'},
                    "plot": {
                        "line-width": 1
                    },
                    "series": [{
                            "values": numOfRegisData,
                            "font-color": "#7e7e7e",
                        }]
                }
            });
        };
    </script>

    <h1 style="margin-top: 2%;margin-bottom: 2%;font-weight: bold;text-align: center;text-transform: uppercase;">Data From JSP Page Using Docker</h1>  
    <div id="myChart" ></div>  
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
