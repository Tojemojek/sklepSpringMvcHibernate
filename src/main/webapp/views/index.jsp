<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="userHeader.jsp"/>

<br>
<c:if test="${error eq 'INCORRECT_CREDENTIALS'}">
    <span style="color: red">Niepoprawny user / hasło</span>
</c:if>
<br>



Top <c:out value="${topProduct.size()}"></c:out>  produktów

<table>

    <c:forEach items="${topProduct}" var="products" varStatus="productStatus">
        <tr>
            <td><c:out value="${productStatus.step}"/></td>
            <td><c:out value="${products.category}"/></td>
            <td><c:out value="${products.name}"/></td>
            <td><c:out value="${products.price}"/></td>
            <td>
                <c:url value="addToBasket" var="basketUrl">
                    <c:param name="productId" value="${products.id}"/>
                    <c:param name="count" value="${topProduct.size()}"/>
                </c:url>
                <a href="<c:out value="${basketUrl}"/>">Do koszyka</a>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
