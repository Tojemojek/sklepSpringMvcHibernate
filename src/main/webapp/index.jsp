<%@ page import="java.time.LocalDateTime" %>
<html>
<body>
<h2>Hello World!</h2>
<%=System.currentTimeMillis()%>
<br>

<%--http://127.0.0.1:8081/?days=8--%>

<%
    Integer days = Integer.valueOf(request.getParameter("days"));
    LocalDateTime dt = LocalDateTime.now();
    dt = dt.plusDays(days);
//    out.print(dt.toString());
%>

Date after <%=days%> days: <%=dt.toString()%>

<br>
<br>

<% for (int i = 1; i < days; i++) {
    LocalDateTime after = dt.plusDays(i);
%>

Date after <%=i%> days: <%=after.toString()%>
<br>

<%
    }
%>

</body>
</html>
