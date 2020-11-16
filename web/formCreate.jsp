<%-- 
    Document   : formCreate
    Created on : Oct 19, 2020, 10:05:02 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Form Create Page</title>
    </head>
    <body>
        <input type="hidden" name="BACK" value="leader.jsp"/>
        <jsp:include page="navBarAfLogin.jsp"/>  
        <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Registration Form</h3>                 
        <form style="margin-left: 20%;" action="MainController" method="POST">
            <div class="form-group">
                <label for="exampleInputEmail1">Username:</label>
                <input type="text" name="txtUserID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${param.txtUserID}" required style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.userIDError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Password:</label>
                <input type="password" name="txtPassword" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.passwordError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Fullname:</label>
                <input type="text" name="txtFullname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Fullname" required value="${param.txtFullname}" style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.fullnameError}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Email address:</label>
                <input type="email" name="txtEmail" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email Address" required value="${param.txtEmail}"style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.emailError}</font>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Role</label>
                <select class="form-control" id="exampleFormControlSelect1" name="cboRole" style="width: 70%;">       
                    <option <c:if test="${param.cboRole eq 'Admin'}"> selected="true"</c:if>>Admin</option>
                    <option <c:if test="${param.cboRole eq 'Leader'}"> selected="true"</c:if>>Leader</option>
                    <option <c:if test="${param.cboRole eq 'Member'}"> selected="true"</c:if>>Member</option>
                    </select>
                </div>
                <div class="form-group form-check">
                <c:if test="${requestScope.USER.getNotification}" var="check">
                </c:if>
                <c:if test="${check}">
                    <input type="checkbox" name="checkNotifi" class="form-check-input" id="exampleCheck1" checked />
                </c:if>
                <c:if test="${!check}">
                    <input type="checkbox" name="checkNotifi" class="form-check-input" id="exampleCheck1" />
                </c:if>
                <label class="form-check-label" for="exampleCheck1">Get Notification</label>
            </div>
            <font color="red">${requestScope.ERROR}</font>
            <input type="hidden" name="txtAvartar" value="${requestScope.USER.avatar}"/>
            <button type="submit" name="action" value="Create Account" class="btn btn-primary">Create Account</button>
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
