<%-- 
    Document   : formCreateGroup
    Created on : Oct 24, 2020, 3:41:40 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Form Create Group Page</title>
    </head>
    <body>
        <input type="hidden" name="BACK" value="leader.jsp"/>
        <jsp:include page="navBarAfLoginLeader.jsp"/>  
        <h3 style="text-align: center;font-size: 250%;text-transform: uppercase;font-weight: bold;margin-top: 3%;">Form Create New Group</h3>                 
        <form style="margin-left: 20%;" action="MainController" method="POST">
            <div class="form-group">
                <label for="exampleInputEmail1">GroupID: </label>
                <input type="text" name="txtGroupID" class="form-control" id="exampleInputEmail1" placeholder="Enter Group ID:" aria-describedby="emailHelp" required value="${param.txtGroupID}" style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.groupID}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Group Name:</label>
                <input type="text" name="txtGroupName" class="form-control" id="exampleInputEmail1" placeholder="Enter Group Name:" aria-describedby="emailHelp" required value="${param.txtGroupName}" style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.groupName}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Group Description:</label>
                <input type="text" name="txtGroupDesc" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Group Description" required value="${param.txtGroupDesc}" style="width: 70%;"/>
                <font color="red">${requestScope.INVALID.groupDesc}</font>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">LeaderID:</label>
                <input type="text" name="txtleaderID" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Group Description" required value="${sessionScope.USER.userID}" readonly="true" style="width: 70%;"/>
            </div>
            <button type="submit" name="action" value="CreateNewGroup" class="btn btn-primary">Create Group</button>
            <font color="red">${requestScope.ERROR}</font>
        </form>  
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
