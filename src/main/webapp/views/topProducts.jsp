<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

 Top <c:out value="${topProduct.size()}"></c:out> produktów
    <table border="1">

        <tr>
            <th>Kategoria produktów</th>
            <th>Nazwa produktu</th>
            <th>Cena produktu</th>
            <th>Ilość</th>
            <th>Dorzuć do koszyka</th>
        </tr>
        <c:forEach items="${topProduct}" var="products" varStatus="productStatus">
            <tr>
                <td><c:out value="${products.category}"/></td>
                <td><c:out value="${products.name}"/></td>
                <td><c:out value="${products.price}"/></td>
                <form action="/addToBasket" id="addToBasket" method="get">
                    <input type="hidden" name="productId" value="<c:out value="${products.id}"/>">
                    <td><input type="number" name="quantity"></td>
                    <td><input type="submit" value="Zamów teraz!">
                            <%--<c:url value="addToBasket" var="basketUrl">--%>
                            <%--<c:param name="productId" value="${products.id}"/>--%>
                            <%--<c:param name="count" value="${topProduct.size()}"/>--%>
                            <%--</c:url>--%>
                            <%--<a href="<c:out value="${basketUrl}"/>">Do koszyka</a>--%>
                </form>
                </td>
            </tr>
        </c:forEach>

    </table>
