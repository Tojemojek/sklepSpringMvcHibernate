<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<body>
<h2>Formatka Logowania</h2>

<p>
    <c:choose>
        <c:when test="${error eq 'INCORRECT_CREDENTIALS'}">
            <span style="color: red">Niepoprawny user / hasło</span>
        </c:when>
        <c:otherwise>Witaj, zaloguj się!</c:otherwise>
    </c:choose>
</p>

<br>

<form id="logowanie" method="post">
    <table>
        <tr>
            <td>Email</td>
            <td><input type="text" name="userEmail"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="userPassword"></td>
        </tr>
    </table>
    <input type="submit" value="Loguj!">
</form>

<%--&nbsp - spacja--%>
<%--&lt; &gt; nawiasy do escape html tag--%>

</body>
</html>
