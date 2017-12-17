<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ page isELIgnored="false" %>--%>

<html>
<body>
<form method="get">
    Select days: <input type="number" value="<c:out value="${param.days}"/>" name="days"><br/>
    <input type="submit" value="Show">
</form>

<br/>

<c:choose>
    <c:when test="${daysList == null}">
        List is empty
    </c:when>
    <c:otherwise>
        <c:forEach items="${daysList}" var="day">
            Date after: <c:out value="${day.daysAfter}"/> days: <c:out value="${day.dateAfter}"/><br/>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>