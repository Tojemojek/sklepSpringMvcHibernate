<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ page isELIgnored="false" %>--%>

<html>
<body>

<p>Tu będą liczniki</p>

<br>
<br>

Session counter: <c:out value="${sessionCounter}"></c:out><br>
Cookie counter: <c:out value="${cookieCounter}"></c:out>

</body>
</html>