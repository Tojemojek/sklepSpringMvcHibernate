<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="../userHeader.jsp"/>
<br>

<br>
<table border="1">
    <tr>
        <th>Kategoria produktu</th>
        <th>Nazwa produktu</th>
        <th>Ilość</th>
        <th>Cena produktu</th>
        <th>Wartość</th>
    </tr>
    <c:forEach items="${productList}" var="products">
        <tr>
            <td><c:out value="${products.product.category}"/></td>
            <td><c:out value="${products.product.name}"/></td>
            <td><c:out value="${products.quantity}"/></td>
            <td align="right"><c:out value="${products.product.price}"/></td>
            <td align="right"><c:out value="${products.product.price * products.quantity}"/></td>
        </tr>
    </c:forEach>
    <tr align="right">
        <td colspan="4">Suma</td>
        <td><c:out value="${totalCost}"/></td>
    </tr>
</table>

<form action="/zabezpieczone/basket/buy" name="buySomething">
    <input type="submit" value="Złóż zamówienie">
</form>

</body>
</html>
