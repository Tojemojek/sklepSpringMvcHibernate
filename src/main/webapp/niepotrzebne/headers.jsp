<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ page isELIgnored="false" %>--%>

<html>
<body>

<p>Tu powinna być lista nagłówków</p>

<br>
<br>

<c:forEach items="${headersMap}" var="hdr">
    Key: <c:out value="${hdr.key}"/> value: <c:out value="${hdr.value}"/><br/>
</c:forEach>

</body>
</html>