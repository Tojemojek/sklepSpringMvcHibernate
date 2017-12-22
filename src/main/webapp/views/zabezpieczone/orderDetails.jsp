<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="../userHeader.jsp"/>
<br>


<p>Zamówienie o <b> ID : <c:out value="${orderNumber}"/> </b></p>

<br>
<table border="1">
    <tr>
        <th>Kategoria produktu</th>
        <th>Nazwa produktu</th>
        <th>Ilość</th>
        <th>Cena produktu</th>
        <th>Wartość</th>
    </tr>
    <c:forEach items="${orderDetail}" var="details">
        <tr>
            <td><c:out value="${details.product.category}"/></td>
            <td><c:out value="${details.product.name}"/></td>
            <td><c:out value="${details.quantity}"/></td>
            <td align="right"><c:out value="${details.product.price}"/></td>
            <td align="right"><c:out value="${details.product.price * details.quantity}"/></td>
        </tr>
    </c:forEach>
    <tr align="right">
        <td colspan="4">Suma</td>
        <td><c:out value="${totalCost}"/></td>
    </tr>

</table>


</body>
</html>
