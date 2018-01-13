<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="userHeader.jsp"/>

<c:if test="${not empty sessionScope.user}">
    <jsp:include page="zabezpieczone/pureOrders.jsp"/>

    <br><br>
</c:if>


<div style="width: 50%; float: left">
    <jsp:include page="topProducts.jsp"/>
</div>

<div style="width: 50%; float: left">
    <jsp:include page="productsInCategories.jsp"/>
</div>


</body>
</html>
