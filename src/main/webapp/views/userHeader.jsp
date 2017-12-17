<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<h2>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            Witaj <c:out value="${sessionScope.user.name}" escapeXml="true"/>
        </c:when>
        <c:otherwise>
            <a href="login">Zaloguj się</a>
        </c:otherwise>
</c:choose>
</h2>
<br>
