<%-- 
    Document   : index
    Created on : Oct 15, 2020, 9:14:15 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="CSS/styleIndex.css" rel="stylesheet" type="text/css">
        <title>Index Page</title>
    </head>
    <body>
        <header style="display: block;background: aqua;color: black;margin-top: 2%;">
            <h1 style="padding-top: 2%;padding-bottom: 2%;margin-bottom: 4%;font-weight: bold;text-align: center;font-size: 400%;font-family: sans-serif;">Application Events</h1>
        </header>
        <div class="contains" style="margin-left: 30%;">
            <form action="MainController" method="POST">
                <input type="submit" class="btn btn-primary" style="width: 30%;font-size: 200%;font-weight: bold;" name="action" value="Search Events"/>
                <input type="submit" class="btn btn-primary" style="width: 30%;font-size: 200%;font-weight: bold;" name="action" value="Login Account"/>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
