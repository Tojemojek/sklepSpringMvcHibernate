<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="../views/userHeader.jsp"/>

<jsp:include page="../views/zabezpieczone/pureOrders.jsp"/>

<br>

<div style="width: 50%; float: left">
    <jsp:include page="../views/topProducts.jsp"/>
</div>

<div style="width: 50%; float: left">
    <jsp:include page="../views/productsInCategories.jsp"/>
</div>

</body>
</html>
