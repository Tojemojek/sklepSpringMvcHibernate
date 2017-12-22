<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<jsp:include page="../userHeader.jsp"/>
<%--przeniosłem to warunkowo do userHeader, by nawet na głównej się wyświetlało--%>
<%--<jsp:include page="userMenu.jsp"/>--%>

<p>Moje zamówienia</p>

Strona basket (koszyk) która wyświetli wszystkie obiekty w koszyku jest w sesji anonymous user controler.
<br>
<br>
<table>
    <c:forEach items="${customersOrdersFromDatabase}" var="orders" varStatus="ordersStatus">
        <tr>
            <td><c:out value="${orders.id}"/></td>
            <td><c:out value="${orders.status}"/></td>
            <td><c:out value="${orders.orderedDate}"/></td>
            <td>
                <c:url value="/zabezpieczone/orderDetails" var="orderUrl">
                    <c:param name="orderId" value="${orders.id}"/>
                </c:url>
                <a href="<c:out value="${orderUrl}"/>">Szczegóły zamówienia</a>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
