<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

        <form action="" method="get">
            <select name="showCategory">
            <c:forEach items="${productCategories}" var="categories">
                           <option value="${categories}">${categories}</option>
            </c:forEach>
            </select>
            <input type="submit" value="Wyświetl produkty z tej kategorii">
       </form>


<c:if test="${not empty productInCategory}">
    <table border="1">
        <tr>
            <th>Nazwa produktu</th>
            <th>Cena produktu</th>
            <th>Ilość</th>
            <th>Dorzuć do koszyka</th>
        </tr>
        <c:forEach items="${productInCategory}" var="products">
            <tr>
                <td><c:out value="${products.name}"/></td>
                <td><c:out value="${products.price}"/></td>
                <form action="/addToBasket" id="addToBasket" method="get">
                    <input type="hidden" name="productId" value="<c:out value="${products.id}"/>">
                    <td><input type="number" name="quantity"></td>
                    <td><input type="submit" value="Zamów teraz!">
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>




