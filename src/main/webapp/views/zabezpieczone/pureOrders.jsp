<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<p>Moje zamówienia</p>

<table border="1">
    <tr>
        <th>Id zamówienia</th>
        <th>Status zamówienia</th>
        <th>Data zamówienia</th>
        <th>Kliknij po szczegóły</th>
    </tr>
    <c:forEach items="${customersOrdersFromDatabase}" var="orders" varStatus="ordersStatus">
        <tr>
            <td><c:out value="${orders.id}"/></td>
            <td><c:out value="${orders.status}"/></td>
            <td><c:out value="${orders.orderedDate}"/></td>
            <td>
                <c:url value="/zabezpieczone/orderDetails" var="orderUrl">
                    <c:param name="orderId" value="${orders.id}"/>
                </c:url>
                <a href="<c:out value="${orderUrl}"/>">Zamówienie <c:out value="${orders.id}"/></a>
            </td>
        </tr>
    </c:forEach>
</table>