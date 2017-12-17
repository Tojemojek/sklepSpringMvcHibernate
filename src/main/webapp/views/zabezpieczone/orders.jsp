<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="../userHeader.jsp"/>
<jsp:include page="userMenu.jsp"/>


Moje zamówienia

<table>
//todo lista zamówień użytkownika
    1. Dane użytkownika z sesji
    2. Na dole url do szczegółów (strona orderDetails)

    Strona order details
    Wyświetlić widok z produktami oraz wyświetli sumaryczną wartość zamówienia

    Strona basket (koszyk) która wyświetli wszystkie obiekty w koszyku jest w sesji anonymous user controler.

    <c:forEach items="${topProduct}" var="products" varStatus="productStatus">
        <tr>
            <td><c:out value="${productStatus.step}"/></td>
            <td><c:out value="${products.category}"/></td>
            <td><c:out value="${products.name}"/></td>
            <td><c:out value="${products.price}"/></td>
            <td>
                <c:url value="addToBasker" var="basketUrl">
                    <c:param name="productId" value="${products.id}"/>
                </c:url>
                <a href="<c:out value="${basketUrl}"/>">Do koszyka</a>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
