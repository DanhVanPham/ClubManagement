<%-- 
    Document   : login
    Created on : Oct 16, 2020, 12:48:24 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <title>Login Page</title>
    </head>
    <body>
        <header>
            <h1 style="text-align: center;font-weight: bold;font-size: 500%;margin-top: 2%;margin-bottom: 2%;">Login Account</h1>
        </header>
        <div class="card" style="width: 70%;margin-left: 15%;">
            <div class="card-body">
                <div class="body">
                    <form action="MainController" method="POST" style="padding-left: 2%;padding-right: 2%;">

                        <div class="form-group mt-3" style="margin-left: 25%;">
                            <label for="exampleInputEmail1" style="font-weight: bold;text-transform: uppercase;">Username:</label>
                            <input type="text" name="txtUsername" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Username:" style="width: 70%;" value="${param.txtUsername}">
                            <font color="red">${requestScope.INVALID.usernameError}</font>
                        </div>
                        <div class="form-group mt-3" style="margin-left: 25%;">
                            <label for="exampleInputPassword1" style="font-weight: bold;text-transform: uppercase;">Password:</label>
                            <input type="password" name="txtPassword" class="form-control" id="exampleInputPassword1" style="width: 70%;" placeholder="Enter Password:">
                            <font color="red">${requestScope.INVALID.passwordError}</font>
                        </div>
                        <input type="hidden" name="BACK" value="login.jsp"/>
                        <button type="submit" name="action" value="Login" class="btn btn-primary mt-3" style="width: 12%;margin-left: 35%;font-weight: bold;font-size: 150%;">Submit</button>
                        <button type="reset" class="btn btn-primary mt-3" style="width: 12%;font-weight: bold;font-size: 150%;margin-left: 5%;">Reset</button><br/>
                        <button type="submit" name="action" value="Search Events" class="btn btn-success mt-3" style="width: 15%;font-weight: bold;font-size: 150%;margin-left: 42%;">Home</button>
                    </form>
                    <h1 style="color: red;font-weight: bold;font-size: 150%;text-align: center;">${requestScope.ERROR}</h1>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
