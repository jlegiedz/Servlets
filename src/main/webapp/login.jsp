<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Logowanie </title>
</head>
<body>

<div style="width:100%; height:20%;border:black;margin:auto">
</div>

<div style="float:left; width:20%">
    <%--dyrektywa--%>
</div>

<div style="float:right; width:80%">
    <img src="http://www.piszanin.pl/imgfiles2/ksizaka3(1).jpg"></a

    <%
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<p>Nieprawidlowe dane logowania</p>");
        }
    %>
    <p> Podaj login i haslo: </p>

    <form name="login" action="login" method="post">
        <label> Nazwa uzytkownika </label> <input type="text" name="login"/>
        <label> Haslo </label> <input type="text" name="password"/>
        <input type="submit" name="Zatwierdz dane">
    </form>
</div>

</body>
</html>
