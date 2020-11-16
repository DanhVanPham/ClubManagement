<%-- 
    Document   : viewMember_Details
    Created on : Oct 26, 2020, 8:41:19 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>View Member Details</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Member'}">
            <jsp:include page="navBarAfLoginMem.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Leader'}">
            <jsp:include page="navBarAfLoginLeader.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId.roleName eq 'Admin'}">
            <jsp:include page="navBarAfLogin.jsp"/>
        </c:if>
        <h1 style="text-align: center;font-weight: bold;font-size: 250%;">View Member Details</h1>
        <c:if test="${not empty requestScope.USER_DETAIL}" var="check">
            <div class="header">
                <img src="${requestScope.USER_DETAIL.avatar}" style="width: 200px;height: 150px;margin-left: 44%;">
            </div>
            <div class="card" style="width: 90%;margin-top: 2%;margin-left: 5%;">
                <div class="card-body">
                    <h1 class="card-header" style="text-align: center;font-size: 200%;">Infomation Profile:</h1>
                    <div class="body" style="margin-left: 5%;">
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">UserID:</label>
                            <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER_DETAIL.userID}" readonly style="width: 70%;"/>
                        </div>
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">Fullname:</label>
                            <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER_DETAIL.fullname}" readonly style="width: 70%;"/>
                        </div>
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">Email:</label>
                            <input type="text" name="txtFullname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER_DETAIL.email}" readonly style="width: 70%;"/>
                        </div>
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">Get Notification:</label>
                            <input type="text" name="txtGetNotification" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER_DETAIL.getNotification}" readonly style="width: 70%;"/>
                        </div>
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">Status:</label>
                            <c:if test="${requestScope.USER_DETAIL.status}" var="checkStatusGroupDetail">
                                <input type="text"  name="txtStatus" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="ACTIVITIES" readonly style="width: 70%;"/>
                            </c:if>
                            <c:if test="${!checkStatusGroupDetail}">
                                <input type="text" name="txtStatus" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="BLOCKED" readonly style="width: 70%;"/>
                            </c:if>
                        </div>
                        <div class="form-group" style="margin-left: 18%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;font-size: 140%;">RoleName:</label>
                            <input type="text" name="txtRoleName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.USER_DETAIL.roleId.roleName}" readonly style="width: 70%;"/>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${!check}">
            <h2 style="text-align: center;color: red;">Can not find user details</h2>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
